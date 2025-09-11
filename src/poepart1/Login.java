
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart1;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author RC_Student_lab
 */
class Login {
    // Class attributes to store user registration details
 private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String cellNumber;
    
    public Login(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public boolean checkUserName() {
           // Check if username is not null, length <= 5, and contains underscore
        return username != null && username.length() <= 5 && username.contains("_");
    }
    
    public boolean checkPasswordComplexity() {
               // Check if password is null or too short
        if (password == null || password.length() < 8) return false;
        
            // Check if password contains at least one capital letter
        boolean hasCapital = !password.equals(password.toLowerCase());
        // Check if password contains at least one number using regex
        boolean hasNumber = password.matches(".*\\d.*");
        // Check if password contains at least one special character
        boolean hasSpecial = !password.matches("[A-Za-z0-9 ]*");
           // Return true only if all conditions are met
        return hasCapital && hasNumber && hasSpecial;
    }
    
    public boolean checkCellPhoneNumber() {
        if (cellNumber == null) return false;
        // Regex created with ChatGPT assistance
        String regex = "^\\+27\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellNumber);
        return matcher.matches();
    }
    
    public String registerUser(String username, String password, String cellNumber) {
          // Store the input values
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
        
          // Validate username and return appropriate error message if invalid
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
            // Validate password and return appropriate error message if invalid
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
         // Validate cell number and return appropriate error message if invalid
        if (!checkCellPhoneNumber()) {
            return "Cell phone number is incorrectly formatted or does not contain international code.";
        }
           // All validations passed - registration successful
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }
    
    public boolean loginUser(String inputUsername, String inputPassword) {
            return username != null && username.equals(inputUsername) && password != null && password.equals(inputPassword);
    }
    
    public String returnLoginStatus(boolean isLoginSuccessful) {
        if (isLoginSuccessful) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    // Getters for testing
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getCellNumber() { return cellNumber; }
       public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
} 


