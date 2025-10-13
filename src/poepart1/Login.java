
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
        return username != null && username.length() <= 5 && username.contains("_");
    }
    
    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) return false;
        
        boolean hasCapital = !password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecial = !password.matches("[A-Za-z0-9 ]*");
        return hasCapital && hasNumber && hasSpecial;
    }
    
    public boolean checkCellPhoneNumber() {
        if (cellNumber == null) return false;
        String regex = "^\\+27\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellNumber);
        return matcher.matches();
    }
    
    public String registerUser(String username, String password, String cellNumber) {
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
        
        boolean isUsernameValid = checkUserName();
        boolean isPasswordValid = checkPasswordComplexity();
        boolean isCellNumberValid = checkCellPhoneNumber();
        
        if (!isUsernameValid && !isPasswordValid && !isCellNumberValid) {
            return "❌ REGISTRATION FAILED - ALL FIELDS INVALID:\n\n" +
                   "• Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.\n" +
                   "• Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.\n" +
                   "• Cell phone number is incorrectly formatted or does not contain international code.\n\n" +
                   "Please correct all errors and try again.";
        }
        
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("❌ REGISTRATION FAILED:\n\n");
        
        if (!isUsernameValid) {
            errorMessage.append("• Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.\n");
        }
        
        if (!isPasswordValid) {
            errorMessage.append("• Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.\n");
        }
        
        if (!isCellNumberValid) {
            errorMessage.append("• Cell phone number is incorrectly formatted or does not contain international code.\n");
        }
        
        errorMessage.append("\nPlease correct the errors and try again.");
        
        if (errorMessage.length() > 0 && (!isUsernameValid || !isPasswordValid || !isCellNumberValid)) {
            return errorMessage.toString();
        }
        
        return "✅ REGISTRATION SUCCESSFUL!\n\n" +
               "• Username successfully captured.\n" +
               "• Password successfully captured.\n" +
               "• Cell phone number successfully added.";
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
    
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getCellNumber() { return cellNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}