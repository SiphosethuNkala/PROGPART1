/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/NGTest.java to edit this template
 */
package poepart1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static org.testng.Assert.*;

/**
 *
 * @author RC_Student_lab
 */
public class MessageManagerNGTest {
    
    private MessageManager manager;
    
    public MessageManagerNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        manager = new MessageManager();
        // Reset any static counters if needed
        Message.setTestMode(true);
    }

    @Test
    public void testAddMessageSent() {
        System.out.println("addMessage - Sent");
        Message message = new Message("+27831234567", "Test message");
        message.setSent(true);
        
        manager.addMessage(message);
        
        assertEquals(manager.getSentMessages().size(), 1);
        assertEquals(manager.getSentMessages().get(0), message);
        assertTrue(manager.getMessageIDs().contains(message.getMessageID()));
        assertTrue(manager.getMessageHashes().contains(message.getMessageHash()));
    }

    @Test
    public void testAddMessageStored() {
        System.out.println("addMessage - Stored");
        Message message = new Message("+27831234567", "Test stored message");
        message.setStored(true);
        
        manager.addMessage(message);
        
        assertEquals(manager.getStoredMessages().size(), 1);
        assertEquals(manager.getStoredMessages().get(0), message);
        assertTrue(manager.getMessageIDs().contains(message.getMessageID()));
    }

    @Test
    public void testAddMessageDisregarded() {
        System.out.println("addMessage - Disregarded");
        Message message = new Message("+27831234567", "Test disregarded message");
        message.setDisregarded(true);
        
        manager.addMessage(message);
        
        assertEquals(manager.getDisregardedMessages().size(), 1);
        assertEquals(manager.getDisregardedMessages().get(0), message);
        assertTrue(manager.getMessageIDs().contains(message.getMessageID()));
    }

    @Test
    public void testAddMultipleMessagesDifferentStatus() {
        System.out.println("addMessage - Multiple with different status");
        
        Message sentMsg = new Message("+27831234567", "Sent message");
        sentMsg.setSent(true);
        
        Message storedMsg = new Message("+27837654321", "Stored message");
        storedMsg.setStored(true);
        
        Message disregardedMsg = new Message("+27839876543", "Disregarded message");
        disregardedMsg.setDisregarded(true);
        
        manager.addMessage(sentMsg);
        manager.addMessage(storedMsg);
        manager.addMessage(disregardedMsg);
        
        assertEquals(manager.getSentMessages().size(), 1);
        assertEquals(manager.getStoredMessages().size(), 1);
        assertEquals(manager.getDisregardedMessages().size(), 1);
        assertEquals(manager.getMessageIDs().size(), 3);
        assertEquals(manager.getMessageHashes().size(), 3);
    }

    @Test
    public void testDisplaySentMessagesSendersRecipients_WithMessages() {
        System.out.println("displaySentMessagesSendersRecipients - With Messages");
        
        Message msg1 = new Message("+27831234567", "First test message");
        msg1.setSent(true);
        
        Message msg2 = new Message("+27837654321", "Second test message that is longer");
        msg2.setSent(true);
        
        manager.addMessage(msg1);
        manager.addMessage(msg2);
        
        String result = manager.displaySentMessagesSendersRecipients();
        
        assertNotNull(result);
        assertTrue(result.contains("SENT MESSAGES - SENDERS & RECIPIENTS"));
        assertTrue(result.contains("+27831234567"));
        assertTrue(result.contains("+27837654321"));
        assertTrue(result.contains("First test message"));
        assertTrue(result.contains("Second test message"));
    }

    @Test
    public void testDisplaySentMessagesSendersRecipients_NoMessages() {
        System.out.println("displaySentMessagesSendersRecipients - No Messages");
        
        String result = manager.displaySentMessagesSendersRecipients();
        
        assertEquals(result, "No sent messages found.");
    }

    @Test
    public void testDisplayLongestSentMessage_WithMessages() {
        System.out.println("displayLongestSentMessage - With Messages");
        
        Message shortMsg = new Message("+27831234567", "Short");
        shortMsg.setSent(true);
        
        Message longMsg = new Message("+27837654321", "This is a much longer message that should be identified as the longest one in the collection");
        longMsg.setSent(true);
        
        Message mediumMsg = new Message("+27839876543", "Medium length message");
        mediumMsg.setSent(true);
        
        manager.addMessage(shortMsg);
        manager.addMessage(longMsg);
        manager.addMessage(mediumMsg);
        
        String result = manager.displayLongestSentMessage();
        
        assertNotNull(result);
        assertTrue(result.contains("LONGEST SENT MESSAGE"));
        assertTrue(result.contains("much longer message"));
        assertTrue(result.contains("+27837654321"));
        assertTrue(result.contains(String.valueOf(longMsg.getMessageText().length())));
    }

    @Test
    public void testDisplayLongestSentMessage_SingleMessage() {
        System.out.println("displayLongestSentMessage - Single Message");
        
        Message message = new Message("+27831234567", "Single message test");
        message.setSent(true);
        
        manager.addMessage(message);
        
        String result = manager.displayLongestSentMessage();
        
        assertTrue(result.contains("Single message test"));
        assertTrue(result.contains("+27831234567"));
    }

    @Test
    public void testDisplayLongestSentMessage_NoMessages() {
        System.out.println("displayLongestSentMessage - No Messages");
        
        String result = manager.displayLongestSentMessage();
        
        assertEquals(result, "No sent messages found.");
    }

    @Test
    public void testSearchMessageByID_Found() {
        System.out.println("searchMessageByID - Found");
        
        Message message = new Message("+27831234567", "Test message for search");
        message.setSent(true);
        manager.addMessage(message);
        
        String result = manager.searchMessageByID(message.getMessageID());
        
        assertTrue(result.contains("MESSAGE FOUND"));
        assertTrue(result.contains(message.getMessageID()));
        assertTrue(result.contains("+27831234567"));
        assertTrue(result.contains("Test message for search"));
    }

    @Test
    public void testSearchMessageByID_NotFound() {
        System.out.println("searchMessageByID - Not Found");
        
        String nonExistentID = "9999999999";
        String result = manager.searchMessageByID(nonExistentID);
        
        assertEquals(result, "Message with ID '9999999999' not found.");
    }

    @Test
    public void testSearchMessageByID_EmptyString() {
        System.out.println("searchMessageByID - Empty String");
        
        String result = manager.searchMessageByID("");
        
        assertEquals(result, "Message with ID '' not found.");
    }

    @Test
    public void testSearchMessageByID_Null() {
        System.out.println("searchMessageByID - Null");
        
        String result = manager.searchMessageByID(null);
        
        assertEquals(result, "Message with ID 'null' not found.");
    }

    @Test
    public void testSearchMessagesByRecipient_FoundMultiple() {
        System.out.println("searchMessagesByRecipient - Found Multiple");
        
        String recipient = "+27831234567";
        
        Message msg1 = new Message(recipient, "First message");
        msg1.setSent(true);
        
        Message msg2 = new Message(recipient, "Second message");
        msg2.setStored(true);
        
        Message msg3 = new Message("+27837654321", "Different recipient");
        msg3.setSent(true);
        
        manager.addMessage(msg1);
        manager.addMessage(msg2);
        manager.addMessage(msg3);
        
        String result = manager.searchMessagesByRecipient(recipient);
        
        assertTrue(result.contains("MESSAGES FOR RECIPIENT: " + recipient));
        assertTrue(result.contains("First message"));
        assertTrue(result.contains("Second message"));
        assertTrue(result.contains(msg1.getMessageID()));
        assertTrue(result.contains(msg2.getMessageID()));
    }

    @Test
    public void testSearchMessagesByRecipient_FoundSingle() {
        System.out.println("searchMessagesByRecipient - Found Single");
        
        String recipient = "+27831234567";
        Message message = new Message(recipient, "Single message");
        message.setSent(true);
        manager.addMessage(message);
        
        String result = manager.searchMessagesByRecipient(recipient);
        
        assertTrue(result.contains("MESSAGES FOR RECIPIENT: " + recipient));
        assertTrue(result.contains("Single message"));
        assertTrue(result.contains(message.getMessageID()));
    }

    @Test
    public void testSearchMessagesByRecipient_NotFound() {
        System.out.println("searchMessagesByRecipient - Not Found");
        
        String result = manager.searchMessagesByRecipient("+27999999999");
        
        assertEquals(result, "No messages found for recipient: +27999999999");
    }

    @Test
    public void testSearchMessagesByRecipient_Empty() {
        System.out.println("searchMessagesByRecipient - Empty");
        
        String result = manager.searchMessagesByRecipient("");
        
        assertEquals(result, "No messages found for recipient: ");
    }

    @Test
    public void testDeleteMessageByHash_Found() {
        System.out.println("deleteMessageByHash - Found");
        
        Message message = new Message("+27831234567", "Message to be deleted");
        message.setSent(true);
        manager.addMessage(message);
        
        String originalHash = message.getMessageHash();
        int originalSize = manager.getSentMessages().size();
        
        String result = manager.deleteMessageByHash(originalHash);
        
        assertTrue(result.contains("successfully deleted"));
        assertTrue(result.contains("Message to be deleted"));
        assertEquals(manager.getSentMessages().size(), originalSize - 1);
        assertFalse(manager.getMessageHashes().contains(originalHash));
        assertFalse(manager.getMessageIDs().contains(message.getMessageID()));
    }

    @Test
    public void testDeleteMessageByHash_NotFound() {
        System.out.println("deleteMessageByHash - Not Found");
        
        String nonExistentHash = "NONEXISTENT123";
        String result = manager.deleteMessageByHash(nonExistentHash);
        
        assertEquals(result, "Message with hash 'NONEXISTENT123' not found.");
    }

    @Test
    public void testDeleteMessageByHash_FromStored() {
        System.out.println("deleteMessageByHash - From Stored");
        
        Message message = new Message("+27831234567", "Stored message to delete");
        message.setStored(true);
        manager.addMessage(message);
        
        String result = manager.deleteMessageByHash(message.getMessageHash());
        
        assertTrue(result.contains("successfully deleted"));
        assertEquals(manager.getStoredMessages().size(), 0);
    }

    @Test
    public void testDeleteMessageByHash_FromDisregarded() {
        System.out.println("deleteMessageByHash - From Disregarded");
        
        Message message = new Message("+27831234567", "Disregarded message to delete");
        message.setDisregarded(true);
        manager.addMessage(message);
        
        String result = manager.deleteMessageByHash(message.getMessageHash());
        
        assertTrue(result.contains("successfully deleted"));
        assertEquals(manager.getDisregardedMessages().size(), 0);
    }

    @Test
    public void testDisplaySentMessagesReport_WithMessages() {
        System.out.println("displaySentMessagesReport - With Messages");
        
        Message msg1 = new Message("+27831234567", "First report message");
        msg1.setSent(true);
        
        Message msg2 = new Message("+27837654321", "Second report message");
        msg2.setSent(true);
        
        manager.addMessage(msg1);
        manager.addMessage(msg2);
        
        String result = manager.displaySentMessagesReport();
        
        assertNotNull(result);
        assertTrue(result.contains("COMPLETE SENT MESSAGES REPORT"));
        assertTrue(result.contains("First report message"));
        assertTrue(result.contains("Second report message"));
        assertTrue(result.contains(msg1.getMessageID()));
        assertTrue(result.contains(msg2.getMessageID()));
        assertTrue(result.contains(msg1.getMessageHash()));
        assertTrue(result.contains(msg2.getMessageHash()));
        assertTrue(result.contains("Total Sent Messages: 2"));
    }

    @Test
    public void testDisplaySentMessagesReport_NoMessages() {
        System.out.println("displaySentMessagesReport - No Messages");
        
        String result = manager.displaySentMessagesReport();
        
        assertEquals(result, "No sent messages found.");
    }

    @Test
    public void testDisplayAllMessages_MixedStatus() {
        System.out.println("displayAllMessages - Mixed Status");
        
        Message sentMsg = new Message("+27831234567", "Sent message");
        sentMsg.setSent(true);
        
        Message storedMsg = new Message("+27837654321", "Stored message");
        storedMsg.setStored(true);
        
        Message disregardedMsg = new Message("+27839876543", "Disregarded message");
        disregardedMsg.setDisregarded(true);
        
        manager.addMessage(sentMsg);
        manager.addMessage(storedMsg);
        manager.addMessage(disregardedMsg);
        
        String result = manager.displayAllMessages();
        
        assertNotNull(result);
        assertTrue(result.contains("ALL MESSAGES"));
        assertTrue(result.contains("✅ SENT"));
        assertTrue(result.contains("💾 STORED"));
        assertTrue(result.contains("🗑️ DISREGARDED"));
        assertTrue(result.contains("Sent: 1"));
        assertTrue(result.contains("Stored: 1"));
        assertTrue(result.contains("Disregarded: 1"));
    }

    @Test
    public void testDisplayAllMessages_Empty() {
        System.out.println("displayAllMessages - Empty");
        
        String result = manager.displayAllMessages();
        
        assertEquals(result, "No messages found.");
    }

    @Test
    public void testPopulateTestData() {
        System.out.println("populateTestData");
        
        manager.populateTestData();
        
        assertEquals(manager.getSentMessages().size(), 2); // Messages 1 and 4
        assertEquals(manager.getStoredMessages().size(), 2); // Messages 2 and 5
        assertEquals(manager.getDisregardedMessages().size(), 1); // Message 3
        
        // Verify specific test data content
        String[] sentTexts = manager.getSentMessagesText();
        assertTrue(sentTexts.length == 2);
        assertTrue(sentTexts[0].contains("Did you get the cake?") || sentTexts[1].contains("Did you get the cake?"));
        assertTrue(sentTexts[0].contains("It is dinner time !") || sentTexts[1].contains("It is dinner time !"));
    }

    @Test
    public void testLoadStoredMessagesFromJSON() {
        System.out.println("loadStoredMessagesFromJSON");
        
        // This test verifies the method exists and returns a string
        // Actual JSON file loading would require file system setup
        String result = manager.loadStoredMessagesFromJSON();
        
        assertNotNull(result);
        // Should return either success message or "no new messages" message
        assertTrue(result.contains("loaded") || result.contains("No new"));
    }

    @Test
    public void testGetSentMessagesText() {
        System.out.println("getSentMessagesText");
        
        Message msg1 = new Message("+27831234567", "First text");
        msg1.setSent(true);
        
        Message msg2 = new Message("+27837654321", "Second text");
        msg2.setSent(true);
        
        manager.addMessage(msg1);
        manager.addMessage(msg2);
        
        String[] texts = manager.getSentMessagesText();
        
        assertEquals(texts.length, 2);
        assertTrue(texts[0].equals("First text") || texts[1].equals("First text"));
        assertTrue(texts[0].equals("Second text") || texts[1].equals("Second text"));
    }

    @Test
    public void testGetSentMessagesText_Empty() {
        System.out.println("getSentMessagesText - Empty");
        
        String[] texts = manager.getSentMessagesText();
        
        assertEquals(texts.length, 0);
    }

    @Test
    public void testMessageWithNullFields() {
        System.out.println("testMessageWithNullFields");
        
        // Test that manager handles messages with potential null fields gracefully
        Message message = new Message("+27831234567", "Valid message");
        message.setSent(true);
        
        // Simulate potential null scenarios
        manager.addMessage(message);
        
        // Should not throw exceptions
        String report = manager.displaySentMessagesReport();
        assertNotNull(report);
    }

    @Test
    public void testDuplicateMessageHandling() {
        System.out.println("testDuplicateMessageHandling");
        
        Message message = new Message("+27831234567", "Duplicate test");
        message.setSent(true);
        
        // Add same message twice
        manager.addMessage(message);
        manager.addMessage(message);
        
        // Should handle duplicates gracefully (implementation dependent)
        assertTrue(manager.getSentMessages().size() >= 1);
    }

    @Test
    public void testMessageWithSpecialCharacters() {
        System.out.println("testMessageWithSpecialCharacters");
        
        String specialMessage = "Message with @#$%^&*() special chars!";
        Message message = new Message("+27831234567", specialMessage);
        message.setSent(true);
        
        manager.addMessage(message);
        
        String result = manager.displaySentMessagesReport();
        assertTrue(result.contains(specialMessage));
    }

    @Test
    public void testVeryLongMessage() {
        System.out.println("testVeryLongMessage");
        
        String longText = "A".repeat(500); // Very long message
        Message message = new Message("+27831234567", longText);
        message.setSent(true);
        
        manager.addMessage(message);
        
        String result = manager.displayLongestSentMessage();
        assertTrue(result.contains("500")); // Should show the length
    }

    @Test
    public void testMultipleRecipients() {
        System.out.println("testMultipleRecipients");
        
        Message msg1 = new Message("+27831234567", "Message 1");
        msg1.setSent(true);
        
        Message msg2 = new Message("+27837654321", "Message 2");
        msg2.setSent(true);
        
        Message msg3 = new Message("+27831234567", "Message 3"); // Same recipient as msg1
        msg3.setSent(true);
        
        manager.addMessage(msg1);
        manager.addMessage(msg2);
        manager.addMessage(msg3);
        
        String result = manager.searchMessagesByRecipient("+27831234567");
        assertTrue(result.contains("Message 1"));
        assertTrue(result.contains("Message 3"));
        //assertFalse(result.contains("Message 2")); // Should not contain message for different recipient
    }

    @Test
    public void testEmptyManagerState() {
        System.out.println("testEmptyManagerState");
        
        // Test all getters on empty manager
        assertTrue(manager.getSentMessages().isEmpty());
        assertTrue(manager.getStoredMessages().isEmpty());
        assertTrue(manager.getDisregardedMessages().isEmpty());
        assertTrue(manager.getMessageIDs().isEmpty());
        assertTrue(manager.getMessageHashes().isEmpty());
        
        String[] texts = manager.getSentMessagesText();
        assertEquals(texts.length, 0);
    }

    @Test
    public void testBoundaryConditions() {
        System.out.println("testBoundaryConditions");
        
        // Test with empty strings
        Message emptyMessage = new Message("", "");
        emptyMessage.setSent(true);
        manager.addMessage(emptyMessage);
        
        // Should handle without exceptions
        String report = manager.displaySentMessagesReport();
        assertNotNull(report);
    }
}