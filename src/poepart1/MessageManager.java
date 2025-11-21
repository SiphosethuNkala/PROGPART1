package poepart1;

import poepart1.Message;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Manages all messages and provides reporting functionality with JSON support
 */
public class MessageManager {
    private List<Message> sentMessages;
    private List<Message> disregardedMessages;
    private List<Message> storedMessages;
    private List<String> messageHashes;
    private List<String> messageIDs;
    
    public MessageManager() {
        this.sentMessages = new ArrayList<>();
        this.disregardedMessages = new ArrayList<>();
        this.storedMessages = new ArrayList<>();
        this.messageHashes = new ArrayList<>();
        this.messageIDs = new ArrayList<>();
    }
    
    /**
     * Add a message to the appropriate arrays based on its status
     */
    public void addMessage(Message message) {
        // Always add to ID and Hash arrays
        if (message.getMessageID() != null) {
            messageIDs.add(message.getMessageID());
        }
        if (message.getMessageHash() != null) {
            messageHashes.add(message.getMessageHash());
        }
        
        // Add to status-specific arrays
        if (message.isSent()) {
            sentMessages.add(message);
        } else if (message.isDisregarded()) {
            disregardedMessages.add(message);
        } else if (message.isStored()) {
            storedMessages.add(message);
        }
    }
    
    /**
     * Load stored messages from JSON file
     */
    public String loadStoredMessagesFromJSON() {
        List<Message> loadedMessages = Message.loadStoredMessagesFromJSON();
        int loadedCount = 0;
        
        for (Message message : loadedMessages) {
            // Check if message already exists
            if (!messageIDs.contains(message.getMessageID())) {
                storedMessages.add(message);
                messageIDs.add(message.getMessageID());
                messageHashes.add(message.getMessageHash());
                loadedCount++;
            }
        }
        
        if (loadedCount > 0) {
            return "✅ Successfully loaded " + loadedCount + " stored messages from JSON file.";
        } else {
            return "ℹ️ No new stored messages found in JSON file.";
        }
    }
    
    /**
     * a. Display the sender and recipient of all sent messages
     */
    public String displaySentMessagesSendersRecipients() {
        if (sentMessages.isEmpty()) {
            return "No sent messages found.";
        }
        
        StringBuilder result = new StringBuilder();
        result.append("=== SENT MESSAGES - SENDERS & RECIPIENTS ===\n\n");
        
        for (int i = 0; i < sentMessages.size(); i++) {
            Message msg = sentMessages.get(i);
            result.append("Message ").append(i + 1).append(":\n")
                  .append("  Recipient: ").append(msg.getRecipient()).append("\n")
                  .append("  Message: ").append(truncateMessage(msg.getMessageText(), 50)).append("\n\n");
        }
        
        return result.toString();
    }
    
    /**
     * b. Display the longest sent message
     */
    public String displayLongestSentMessage() {
        if (sentMessages.isEmpty()) {
            return "No sent messages found.";
        }
        
        Message longestMessage = sentMessages.get(0);
        for (Message msg : sentMessages) {
            if (msg.getMessageText().length() > longestMessage.getMessageText().length()) {
                longestMessage = msg;
            }
        }
        
        return "=== LONGEST SENT MESSAGE ===\n\n" +
               "Length: " + longestMessage.getMessageText().length() + " characters\n" +
               "Recipient: " + longestMessage.getRecipient() + "\n" +
               "Message: " + longestMessage.getMessageText() + "\n" +
               "Message ID: " + longestMessage.getMessageID();
    }
    
    /**
     * c. Search for a message ID and display the corresponding recipient and message
     */
    public String searchMessageByID(String messageID) {
        for (Message msg : getAllMessages()) {
            if (msg.getMessageID().equals(messageID)) {
                return "=== MESSAGE FOUND ===\n\n" +
                       "Message ID: " + msg.getMessageID() + "\n" +
                       "Recipient: " + msg.getRecipient() + "\n" +
                       "Message: " + msg.getMessageText() + "\n" +
                       "Status: " + getMessageStatus(msg);
            }
        }
        return "Message with ID '" + messageID + "' not found.";
    }
    
    /**
     * d. Search for all messages sent to a particular recipient
     */
    public String searchMessagesByRecipient(String recipient) {
        List<Message> matchingMessages = new ArrayList<>();
        
        for (Message msg : getAllMessages()) {
            if (msg.getRecipient().equals(recipient)) {
                matchingMessages.add(msg);
            }
        }
        
        if (matchingMessages.isEmpty()) {
            return "No messages found for recipient: " + recipient;
        }
        
        StringBuilder result = new StringBuilder();
        result.append("=== MESSAGES FOR RECIPIENT: ").append(recipient).append(" ===\n\n");
        
        for (int i = 0; i < matchingMessages.size(); i++) {
            Message msg = matchingMessages.get(i);
            result.append("Message ").append(i + 1).append(":\n")
                  .append("  Message ID: ").append(msg.getMessageID()).append("\n")
                  .append("  Message: ").append(msg.getMessageText()).append("\n")
                  .append("  Status: ").append(getMessageStatus(msg)).append("\n\n");
        }
        
        return result.toString();
    }
    
    /**
     * e. Delete a message using the message hash
     */
    public String deleteMessageByHash(String messageHash) {
        for (Message msg : getAllMessages()) {
            if (msg.getMessageHash().equals(messageHash)) {
                String messageText = msg.getMessageText();
                
                // Remove from all arrays
                sentMessages.remove(msg);
                disregardedMessages.remove(msg);
                storedMessages.remove(msg);
                messageHashes.remove(messageHash);
                messageIDs.remove(msg.getMessageID());
                
                return "Message '" + truncateMessage(messageText, 30) + "' successfully deleted.";
            }
        }
        return "Message with hash '" + messageHash + "' not found.";
    }
    
    /**
     * f. Display a report that lists the full details of all sent messages
     */
    public String displaySentMessagesReport() {
        if (sentMessages.isEmpty()) {
            return "No sent messages found.";
        }
        
        StringBuilder result = new StringBuilder();
        result.append("=== COMPLETE SENT MESSAGES REPORT ===\n\n");
        
        for (int i = 0; i < sentMessages.size(); i++) {
            Message msg = sentMessages.get(i);
            result.append("MESSAGE ").append(i + 1).append(":\n")
                  .append("  Message Hash: ").append(msg.getMessageHash()).append("\n")
                  .append("  Message ID: ").append(msg.getMessageID()).append("\n")
                  .append("  Recipient: ").append(msg.getRecipient()).append("\n")
                  .append("  Message: ").append(msg.getMessageText()).append("\n")
                  .append("  Length: ").append(msg.getMessageText().length()).append(" characters\n")
                  .append("  Message Number: ").append(msg.getMessageNumber()).append("\n\n");
        }
        
        result.append("=== SUMMARY ===\n")
              .append("Total Sent Messages: ").append(sentMessages.size()).append("\n");
        
        return result.toString();
    }
    
    /**
     * Display all messages with their status
     */
    public String displayAllMessages() {
        List<Message> allMessages = getAllMessages();
        if (allMessages.isEmpty()) {
            return "No messages found.";
        }
        
        StringBuilder result = new StringBuilder();
        result.append("=== ALL MESSAGES ===\n\n");
        
        for (int i = 0; i < allMessages.size(); i++) {
            Message msg = allMessages.get(i);
            result.append("Message ").append(i + 1).append(":\n")
                  .append("  Status: ").append(getMessageStatus(msg)).append("\n")
                  .append("  Recipient: ").append(msg.getRecipient()).append("\n")
                  .append("  Message: ").append(truncateMessage(msg.getMessageText(), 40)).append("\n")
                  .append("  Message ID: ").append(msg.getMessageID()).append("\n\n");
        }
        
        result.append("=== TOTALS ===\n")
              .append("Sent: ").append(sentMessages.size()).append(" | ")
              .append("Stored: ").append(storedMessages.size()).append(" | ")
              .append("Disregarded: ").append(disregardedMessages.size()).append("\n");
        
        return result.toString();
    }
    
    /**
     * Populate with test data as specified in requirements
     */
    public void populateTestData() {
        // Test Data Message 1
        Message msg1 = new Message("+27834557896", "Did you get the cake?");
        msg1.setSent(true);
        addMessage(msg1);
        
        // Test Data Message 2
        Message msg2 = new Message("+27338884567", "Where are you? You are late! I have asked you to be on time.");
        msg2.setStored(true);
        addMessage(msg2);
        
        // Test Data Message 3
        Message msg3 = new Message("+27334484567", "Yohoooo, I am at your gate.");
        msg3.setDisregarded(true);
        addMessage(msg3);
        
        // Test Data Message 4
        Message msg4 = new Message("0838884567", "It is dinner time !");
        msg4.setSent(true);
        addMessage(msg4);
        
        // Test Data Message 5
        Message msg5 = new Message("+27338884567", "Ok, I am leaving without you.");
        msg5.setStored(true);
        addMessage(msg5);
    }
    
    // Helper methods
    private List<Message> getAllMessages() {
        List<Message> allMessages = new ArrayList<>();
        allMessages.addAll(sentMessages);
        allMessages.addAll(disregardedMessages);
        allMessages.addAll(storedMessages);
        return allMessages;
    }
    
    private String getMessageStatus(Message msg) {
        if (msg.isSent()) return "✅ SENT";
        if (msg.isStored()) return "💾 STORED";
        if (msg.isDisregarded()) return "🗑️ DISREGARDED";
        return "⏳ UNKNOWN";
    }
    
    private String truncateMessage(String message, int maxLength) {
        if (message.length() <= maxLength) {
            return message;
        }
        return message.substring(0, maxLength) + "...";
    }
    
    // Getters for unit testing
    public List<Message> getSentMessages() { return sentMessages; }
    public List<Message> getDisregardedMessages() { return disregardedMessages; }
    public List<Message> getStoredMessages() { return storedMessages; }
    public List<String> getMessageHashes() { return messageHashes; }
    public List<String> getMessageIDs() { return messageIDs; }
    
    public String[] getSentMessagesText() {
        List<String> texts = new ArrayList<>();
        for (Message msg : sentMessages) {
            texts.add(msg.getMessageText());
        }
        return texts.toArray(new String[0]);
    }
}
