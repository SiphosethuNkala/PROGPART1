/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart1;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author RC_Student_lab
 */
public class POEPART1 {

    /**
     * @param args the command line arguments
     */
      private static List<Message> allMessages = new ArrayList<>();
    
    public static void main(String[] args) {
        String firstName = JOptionPane.showInputDialog("=== USER INFORMATION ===\nEnter your first name:");
        if (firstName == null) {
            JOptionPane.showMessageDialog(null, "Registration cancelled by user.");
            return;
        }
        
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        if (lastName == null) {
            JOptionPane.showMessageDialog(null, "Registration cancelled by user.");
            return;
        }
        
        Login userLogin = new Login(firstName, lastName);
        
        boolean registrationSuccessful = false;
        while (!registrationSuccessful) {
            String username = JOptionPane.showInputDialog("=== REGISTRATION ===\nEnter username (must contain '_' and be ≤5 characters):");
            if (username == null) {
                JOptionPane.showMessageDialog(null, "Registration cancelled by user.");
                return;
            }
            
            String password = JOptionPane.showInputDialog("Enter password (must have 8+ chars, capital, number, special char):");
            if (password == null) {
                JOptionPane.showMessageDialog(null, "Registration cancelled by user.");
                return;
            }
            
            String cellNumber = JOptionPane.showInputDialog("Enter cell number (format: +27839284756):");
            if (cellNumber == null) {
                JOptionPane.showMessageDialog(null, "Registration cancelled by user.");
                return;
            }
            
            String registrationResult = userLogin.registerUser(username, password, cellNumber);
            JOptionPane.showMessageDialog(null, registrationResult);
            
            if (registrationResult.contains("✅ REGISTRATION SUCCESSFUL!")) {
                registrationSuccessful = true;
            } else {
                int retry = JOptionPane.showConfirmDialog(null, 
                    "Registration failed. Would you like to try again?", 
                    "Retry Registration", 
                    JOptionPane.YES_NO_OPTION);
                
                if (retry != JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Registration cancelled.");
                    return;
                }
            }
        }
        
        boolean loginSuccessful = false;
        int loginAttempts = 0;
        final int MAX_LOGIN_ATTEMPTS = 3;
        
        while (!loginSuccessful && loginAttempts < MAX_LOGIN_ATTEMPTS) {
            String loginUser = JOptionPane.showInputDialog("=== LOGIN ===\nEnter username:");
            if (loginUser == null) {
                JOptionPane.showMessageDialog(null, "Login cancelled by user.");
                return;
            }
            
            String loginPass = JOptionPane.showInputDialog("Enter password:");
            if (loginPass == null) {
                JOptionPane.showMessageDialog(null, "Login cancelled by user.");
                return;
            }
            
            boolean loginResult = userLogin.loginUser(loginUser, loginPass);
            String loginStatus = userLogin.returnLoginStatus(loginResult);
            JOptionPane.showMessageDialog(null, loginStatus);
            
            if (loginResult) {
                loginSuccessful = true;
            } else {
                loginAttempts++;
                int remainingAttempts = MAX_LOGIN_ATTEMPTS - loginAttempts;
                
                if (remainingAttempts > 0) {
                    int retry = JOptionPane.showConfirmDialog(null, 
                        "Login failed. " + remainingAttempts + " attempt(s) remaining.\nWould you like to try again?", 
                        "Retry Login", 
                        JOptionPane.YES_NO_OPTION);
                    
                    if (retry != JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "Login cancelled.");
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "❌ LOGIN FAILED\n\nMaximum login attempts reached. Please restart the application.");
                    return;
                }
            }
        }
        
        if (loginSuccessful) {
            JOptionPane.showMessageDialog(null, 
                "🎉 LOGIN SUCCESSFUL!\n\nWelcome to QuickChat, " + firstName + " " + lastName + "!");
            displayMessageSystem();
        }
    }
    
    private static void displayMessageSystem() {
        JOptionPane.showMessageDialog(null, 
            "💬 WELCOME TO QUICKCHAT\n\n" +
            "You can now send messages to your contacts!");
        
        while (true) {
            String[] options = {"📤 Send Messages", "📋 Show recently sent messages", "🚪 Quit"};
            int choice = JOptionPane.showOptionDialog(null,
                "=== QUICKCHAT MENU ===\n\n" +
                "Please choose an option:\n" +
                "1. 📤 Send Messages\n" +
                "2. 📋 Show recently sent messages\n" +
                "3. 🚪 Quit",
                "QuickChat Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
            
            switch (choice) {
                case 0:
                    sendMessages();
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, 
                        "🔧 FEATURE IN DEVELOPMENT\n\n" +
                        "This feature is coming soon!\n" +
                        "Check back in the next update.");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, 
                        "=== QUICKCHAT SUMMARY ===\n\n" +
                        "📊 Session Statistics:\n" +
                        "• Total messages sent: " + Message.returnTotalMessages() + "\n" +
                        "• Thank you for using QuickChat! 👋");
                    return;
                case JOptionPane.CLOSED_OPTION:
                    return;
                default:
                    return;
            }
        }
    }
    
    private static void sendMessages() {
        try {
            String numMessagesStr = JOptionPane.showInputDialog(
                "=== SEND MESSAGES ===\n\n" +
                "How many messages do you wish to send?");
            
            if (numMessagesStr == null) {
                JOptionPane.showMessageDialog(null, "Message sending cancelled.");
                return;
            }
            
            int numMessages = Integer.parseInt(numMessagesStr);
            
            if (numMessages <= 0) {
                JOptionPane.showMessageDialog(null, 
                    "❌ INVALID INPUT\n\n" +
                    "Please enter a positive number greater than zero.");
                return;
            }
            
            if (numMessages > 10) {
                JOptionPane.showMessageDialog(null, 
                    "⚠️ LIMIT NOTICE\n\n" +
                    "For demonstration purposes, we'll limit to 10 messages.");
                numMessages = 10;
            }
            
            JOptionPane.showMessageDialog(null, 
                "📝 READY TO CREATE " + numMessages + " MESSAGE(S)\n\n" +
                "You will now be prompted to enter details for each message.");
            
            int successfullySent = 0;
            
            for (int i = 0; i < numMessages; i++) {
                JOptionPane.showMessageDialog(null, 
                    "📨 CREATING MESSAGE " + (i + 1) + " OF " + numMessages);
                
                String recipient = JOptionPane.showInputDialog(
                    "=== MESSAGE " + (i + 1) + " ===\n\n" +
                    "Enter recipient phone number:\n" +
                    "Format: +27XXXXXXXXX (10 digits after +27)");
                
                if (recipient == null) {
                    int continueSending = JOptionPane.showConfirmDialog(null,
                        "Cancel message " + (i + 1) + "?\nContinue with remaining messages?",
                        "Cancel Message",
                        JOptionPane.YES_NO_OPTION);
                    
                    if (continueSending == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "Message sending cancelled.");
                        break;
                    } else {
                        continue;
                    }
                }
                
                String messageText = JOptionPane.showInputDialog(
                    "Enter message text (max 250 characters):\n" +
                    "Characters used: 0/250");
                
                if (messageText == null) {
                    int continueSending = JOptionPane.showConfirmDialog(null,
                        "Cancel message " + (i + 1) + "?\nContinue with remaining messages?",
                        "Cancel Message",
                        JOptionPane.YES_NO_OPTION);
                    
                    if (continueSending == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "Message sending cancelled.");
                        break;
                    } else {
                        continue;
                    }
                }
                
                Message message = new Message(recipient, messageText);
                allMessages.add(message);
                
                JOptionPane.showMessageDialog(null, 
                    "✅ MESSAGE CREATED SUCCESSFULLY\n\n" +
                    "=== MESSAGE DETAILS ===\n" +
                    message.displayMessageDetails());
                
                String result = message.sentMessage();
                
                if (message.isSent()) {
                    successfullySent++;
                    JOptionPane.showMessageDialog(null,
                        "🎉 " + result + "\n\n" +
                        "=== FINAL MESSAGE DETAILS ===\n" +
                        message.displayMessageDetails());
                } else if (message.isStored()) {
                    JOptionPane.showMessageDialog(null, 
                        "💾 " + result + "\n\n" +
                        "Message has been stored for later sending.");
                } else if (message.isDisregarded()) {
                    JOptionPane.showMessageDialog(null, 
                        "🗑️ " + result + "\n\n" +
                        "Message has been disregarded.");
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "❌ MESSAGE NOT SENT\n\n" + result);
                }
            }
            
            JOptionPane.showMessageDialog(null, 
                "=== SESSION COMPLETE ===\n\n" +
                "📊 Message Statistics:\n" +
                "• Attempted to send: " + numMessages + " message(s)\n" +
                "• Successfully sent: " + successfullySent + " message(s)\n" +
                "• Stored for later: " + countStoredMessages() + " message(s)\n" +
                "• Total sent this session: " + successfullySent + "\n" +
                "• Overall total sent: " + Message.returnTotalMessages());
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, 
                "❌ INVALID INPUT\n\n" +
                "Please enter a valid number (e.g., 1, 2, 3).");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "❌ UNEXPECTED ERROR\n\n" +
                "An error occurred: " + e.getMessage() + "\n" +
                "Please try again.");
        }
    }
    
    private static int countStoredMessages() {
        int storedCount = 0;
        for (Message msg : allMessages) {
            if (msg.isStored()) {
                storedCount++;
            }
        }
        return storedCount;
    }
}