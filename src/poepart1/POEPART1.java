/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart1;
import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class POEPART1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        // Get user's first and last name
        System.out.println("=== USER INFORMATION ===");
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        
        // Create a Login instance with the user's actual nameset
        Login userLogin = new Login(firstName, lastName);
        
           // Display registration header
        System.out.println("=== REGISTRATION ===");
        
           // Get username from user
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
          // Get password from user
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
          // Get cell number from user
        System.out.print("Enter cell number (e.g., +27839284756): ");
        String cellNumber = scanner.nextLine();
        
                // Attempt to register the user
        String result = userLogin.registerUser(username, password, cellNumber);
        System.out.println("\n" + result);
        
         // If registration was successful, proceed to login
        if (result.contains("successfully")) {
            System.out.println("\n=== LOGIN ===");
             // Get login credentials
            System.out.print("Enter username: ");
            String inputUser = scanner.nextLine();
            
            System.out.print("Enter password: ");
            String inputPass = scanner.nextLine();
            
               // Attempt to login
            boolean loginResult = userLogin.loginUser(inputUser, inputPass);
            System.out.println(userLogin.returnLoginStatus(loginResult));
        }
        // Close the scanner to prevent resource leak
        scanner.close();
    }
} 