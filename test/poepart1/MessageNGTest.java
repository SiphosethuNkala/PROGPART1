/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package poepart1;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author RC_Student_Lab
 */
public class MessageNGTest {
  private Message message1;
    private Message message2;
    
    public MessageNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("🔧 Starting Message tests...");
        Message.setTestMode(true); // Enable test mode to prevent dialogs
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("✅ Finished Message tests.");
        Message.setTestMode(false); // Disable test mode
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        Message.resetCounters();
        message1 = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight");
        message2 = new Message("08575975889", "Hi Keegan, did you receive the payment?");
    }

    @Test
    public void testCheckMessageID() {
        assertTrue(message1.checkMessageID(), "Valid message ID should pass");
        assertEquals(message1.getMessageID().length(), 10, "Message ID should be exactly 10 characters");
    }

    @Test
    public void testCheckRecipientCellValid() {
        assertEquals(message1.checkRecipientCell(), 1, "Valid recipient cell should return 1");
    }

    @Test
    public void testCheckRecipientCellInvalid() {
        assertEquals(message2.checkRecipientCell(), 0, "Invalid recipient cell should return 0");
    }

    @Test
    public void testCreateMessageHash() {
        String hash = message1.createMessageHash();
        assertNotNull(hash, "Message hash should not be null");
        assertTrue(hash.contains(":"), "Message hash should contain colons");
        assertEquals(hash, hash.toUpperCase(), "Message hash should be in uppercase");
        
        String[] parts = hash.split(":");
        assertEquals(parts.length, 3, "Message hash should have 3 parts separated by colons");
    }

    @Test
    public void testCheckMessageLengthValid() {
        assertTrue(message1.checkMessageLength(), "Message within 250 characters should pass");
    }

    @Test
    public void testCheckMessageLengthInvalid() {
        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            longMessage.append("a");
        }
        Message longMsg = new Message("+27841234567", longMessage.toString());
        assertFalse(longMsg.checkMessageLength(), "Message exceeding 250 characters should fail");
    }

    @Test
    public void testGetMessageLengthStatusSuccess() {
        String status = message1.getMessageLengthStatus();
        assertEquals(status, "Message ready to send.", "Valid message length should return success message");
    }

    @Test
    public void testGetMessageLengthStatusFailure() {
        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            longMessage.append("a");
        }
        Message longMsg = new Message("+27841234567", longMessage.toString());
        String status = longMsg.getMessageLengthStatus();
        assertTrue(status.contains("Message exceeds 250 characters by"), "Long message should return error message");
        assertTrue(status.contains("50"), "Should show excess character count");
    }

    @Test
    public void testGetRecipientStatusValid() {
        String status = message1.getRecipientStatus();
        assertEquals(status, "Cell phone number successfully captured.", "Valid recipient should return success message");
    }

    @Test
    public void testGetRecipientStatusInvalid() {
        String status = message2.getRecipientStatus();
        assertEquals(status, "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", "Invalid recipient should return error message");
    }

    @Test
    public void testPrintMessage() {
        String printedMessage = message1.printMessage();
        assertTrue(printedMessage.contains("MessageID"), "Printed message should contain MessageID");
        assertTrue(printedMessage.contains("Message Hash"), "Printed message should contain Message Hash");
        assertTrue(printedMessage.contains("Recipient"), "Printed message should contain Recipient");
        assertTrue(printedMessage.contains("Message"), "Printed message should contain Message");
    }

    @Test
    public void testDisplayMessageDetails() {
        String details = message1.displayMessageDetails();
        assertTrue(details.contains("MessageID"), "Details should contain MessageID");
        assertTrue(details.contains("Message Hash"), "Details should contain Message Hash");
        assertTrue(details.contains("Recipient"), "Details should contain Recipient");
        assertTrue(details.contains("Message"), "Details should contain Message");
    }

    @Test
    public void testReturnTotalMessages() {
        assertEquals(Message.returnTotalMessages(), 0, "Initial total messages should be 0");
    }

    @Test
    public void testMessageConstructor() {
        assertNotNull(message1.getMessageID(), "Message ID should be generated");
        assertNotNull(message1.getMessageHash(), "Message hash should be generated");
        assertEquals(message1.getRecipient(), "+27718693002", "Recipient should be set correctly");
        assertEquals(message1.getMessageText(), "Hi Mike, can you join us for dinner tonight", "Message text should be set correctly");
    }

    @Test
    public void testMessageHashFormat() {
        String hash = message1.createMessageHash();
        String[] parts = hash.split(":");
        assertEquals(parts.length, 3, "Message hash should have 3 parts separated by colons");
        assertTrue(parts[0].length() <= 2, "First part should be max 2 characters from message ID");
    }

    @Test
    public void testMessageIDGeneration() {
        Message msg1 = new Message("+27841234567", "Test message");
        Message msg2 = new Message("+27841234568", "Another test message");
        
        assertNotEquals(msg1.getMessageID(), msg2.getMessageID(), "Message IDs should be unique");
        assertTrue(msg1.getMessageID().matches("\\d{10}"), "Message ID should be 10 digits");
    }

    @Test
    public void testMessageNumberIncrement() {
        Message.resetCounters();
        Message msg1 = new Message("+27841234567", "First message");
        Message msg2 = new Message("+27841234568", "Second message");
        
        assertEquals(msg1.getMessageNumber(), 1, "First message should have number 1");
        assertEquals(msg2.getMessageNumber(), 2, "Second message should have number 2");
    }

    @Test
    public void testMessageStatusFlags() {
        assertFalse(message1.isSent(), "New message should not be sent");
        assertFalse(message1.isStored(), "New message should not be stored");
        assertFalse(message1.isDisregarded(), "New message should not be disregarded");
    }

    @Test
    public void testCounterReset() {
        Message.resetCounters();
        assertEquals(Message.returnTotalMessages(), 0, "After reset, total messages should be 0");
        
        Message msg = new Message("+27841234567", "Test message");
        assertEquals(msg.getMessageNumber(), 1, "After reset, first message should be number 1");
    }

    @Test
    public void testEmptyMessage() {
        Message emptyMsg = new Message("+27841234567", "");
        assertTrue(emptyMsg.checkMessageLength(), "Empty message should pass length check");
        assertEquals(emptyMsg.getMessageText(), "", "Message text should be empty");
    }

    @Test
    public void testSentMessageSendSuccess() {
        Message testMsg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight");
        String result = testMsg.sentMessage(0); // Send option
        assertEquals(result, "Message successfully sent.", "Should return success message when sending");
        assertTrue(testMsg.isSent(), "Message should be marked as sent");
    }

    @Test
    public void testSentMessageDiscard() {
        Message testMsg = new Message("08575975889", "Hi Keegan, did you receive the payment?");
        String result = testMsg.sentMessage(1); // Discard option
        assertEquals(result, "Press 0 to delete message.", "Should return discard message");
        assertTrue(testMsg.isDisregarded(), "Message should be marked as disregarded");
    }

    @Test
    public void testSentMessageStore() {
        Message testMsg = new Message("+27831234567", "Test message");
        String result = testMsg.sentMessage(2); // Store option
        assertEquals(result, "Message successfully stored.", "Should return store message");
        assertTrue(testMsg.isStored(), "Message should be marked as stored");
    }

    @Test
    public void testTotalMessagesIncrement() {
        int initialTotal = Message.returnTotalMessages();
        Message testMsg = new Message("+27718693002", "Test message");
        testMsg.sentMessage(0); // Send the message
        assertEquals(Message.returnTotalMessages(), initialTotal + 1, "Total messages should increment when message is sent");
    }

    @Test
    public void testSentMessageAutoBehavior() {
        // Test the automatic behavior in test mode
        Message autoMsg1 = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight");
        String result1 = autoMsg1.sentMessage();
        assertEquals(result1, "Message successfully sent.", "Should auto-send valid test message 1");
        assertTrue(autoMsg1.isSent(), "Message 1 should be marked as sent");

        Message autoMsg2 = new Message("08575975889", "Hi Keegan, did you receive the payment?");
        String result2 = autoMsg2.sentMessage();
        assertEquals(result2, "Press 0 to delete message.", "Should auto-discard invalid test message 2");
        assertTrue(autoMsg2.isDisregarded(), "Message 2 should be marked as disregarded");
    }
}