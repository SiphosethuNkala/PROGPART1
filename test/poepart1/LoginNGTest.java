/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package poepart1;


import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author RC_Student_lab
 */
public class LoginNGTest {
    
private Login login;
    
    public LoginNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        login = new Login("Kyle", "Smith");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test
    public void testCheckUserNameValid() {
        System.out.println("checkUserName - Valid");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.checkUserName();
        assertTrue(result, "Username should be valid - contains underscore and is 5 characters long");
    }

    @Test
    public void testCheckUserNameInvalidNoUnderscore() {
        System.out.println("checkUserName - Invalid (no underscore)");
        login.registerUser("kyle1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.checkUserName();
        assertFalse(result, "Username should be invalid - no underscore");
    }

    @Test
    public void testCheckUserNameInvalidTooLong() {
        System.out.println("checkUserName - Invalid (too long)");
        login.registerUser("kyle_123", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.checkUserName();
        assertFalse(result, "Username should be invalid - more than 5 characters");
    }

    @Test
    public void testCheckPasswordComplexityValid() {
        System.out.println("checkPasswordComplexity - Valid");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.checkPasswordComplexity();
        assertTrue(result, "Password should be valid - meets all complexity requirements");
    }

    @Test
    public void testCheckPasswordComplexityInvalid() {
        System.out.println("checkPasswordComplexity - Invalid");
        login.registerUser("kyl_1", "password", "+27838968976");
        boolean result = login.checkPasswordComplexity();
        assertFalse(result, "Password should be invalid - missing capital letter, number, and special character");
    }

    @Test
    public void testCheckCellPhoneNumberValid() {
        System.out.println("checkCellPhoneNumber - Valid");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.checkCellPhoneNumber();
        assertTrue(result, "Cell phone number should be valid - starts with +27 and has correct length");
    }

    @Test
    public void testCheckCellPhoneNumberInvalid() {
        System.out.println("checkCellPhoneNumber - Invalid");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553");
        boolean result = login.checkCellPhoneNumber();
        assertFalse(result, "Cell phone number should be invalid - missing international code");
    }

    @Test
    public void testRegisterUserAllValid() {
        System.out.println("registerUser - All Valid");
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("successfully"), "Registration should be successful with valid details");
    }

    @Test
    public void testRegisterUserInvalidUsername() {
        System.out.println("registerUser - Invalid Username");
        String result = login.registerUser("kyle!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Username is not correctly formatted"), "Should return username error message");
        assertFalse(result.contains("Password is not correctly formatted"), "Should not return password error");
        assertFalse(result.contains("Cell phone number is incorrectly formatted"), "Should not return cell number error");
    }

    @Test
    public void testRegisterUserInvalidPassword() {
        System.out.println("registerUser - Invalid Password");
        String result = login.registerUser("kyl_1", "password", "+27838968976");
        assertTrue(result.contains("Password is not correctly formatted"), "Should return password error message");
        assertFalse(result.contains("Username is not correctly formatted"), "Should not return username error");
        assertFalse(result.contains("Cell phone number is incorrectly formatted"), "Should not return cell number error");
    }

    @Test
    public void testRegisterUserInvalidCellNumber() {
        System.out.println("registerUser - Invalid Cell Number");
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553");
        assertTrue(result.contains("Cell phone number is incorrectly formatted"), "Should return cell number error message");
        assertFalse(result.contains("Username is not correctly formatted"), "Should not return username error");
        assertFalse(result.contains("Password is not correctly formatted"), "Should not return password error");
    }

    @Test
    public void testRegisterUserAllInvalid() {
        System.out.println("registerUser - All Invalid");
        String result = login.registerUser("kyle!!!!!!", "password", "08966553");
        
        assertTrue(result.contains("Username is not correctly formatted"), "Should return username error message");
        assertTrue(result.contains("Password is not correctly formatted"), "Should return password error message");
        assertTrue(result.contains("Cell phone number is incorrectly formatted"), "Should return cell number error message");
        
        int errorCount = 0;
        if (result.contains("Username is not correctly formatted")) errorCount++;
        if (result.contains("Password is not correctly formatted")) errorCount++;
        if (result.contains("Cell phone number is incorrectly formatted")) errorCount++;
        
        assertEquals(errorCount, 3, "Should return all three error messages");
    }

    @Test
    public void testLoginUserSuccessful() {
        System.out.println("loginUser - Successful");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue(result, "Login should be successful with correct credentials");
    }

    @Test
    public void testLoginUserFailed() {
        System.out.println("loginUser - Failed");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.loginUser("wronguser", "wrongpass");
        assertFalse(result, "Login should fail with incorrect credentials");
    }

    @Test
    public void testReturnLoginStatusSuccessful() {
        System.out.println("returnLoginStatus - Successful");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean loginResult = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        String result = login.returnLoginStatus(loginResult);
        assertEquals(result, "Welcome Kyle, Smith it is great to see you again.");
    }

    @Test
    public void testReturnLoginStatusFailed() {
        System.out.println("returnLoginStatus - Failed");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean loginResult = login.loginUser("wronguser", "wrongpass");
        String result = login.returnLoginStatus(loginResult);
        assertEquals(result, "Username or password incorrect, please try again.");
    }

    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String result = login.getUsername();
        assertEquals(result, "kyl_1");
    }

    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String result = login.getPassword();
        assertEquals(result, "Ch&&sec@ke99!");
    }

    @Test
    public void testGetCellNumber() {
        System.out.println("getCellNumber");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String result = login.getCellNumber();
        assertEquals(result, "+27838968976");
    }

    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        String result = login.getFirstName();
        assertEquals(result, "Kyle");
    }

    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        String result = login.getLastName();
        assertEquals(result, "Smith");
    }
}