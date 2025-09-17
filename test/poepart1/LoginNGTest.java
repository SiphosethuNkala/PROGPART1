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

    /**
     * Test of checkUserName method, of class Login.
     * Test Case: Username is correctly formatted (contains underscore and is no more than five characters long)
     */
    @Test
    public void testCheckUserNameValid() {
        System.out.println("checkUserName - Valid");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.checkUserName();
        assertTrue(result, "Username should be valid - contains underscore and is 5 characters long");
    }

    /**
     * Test of checkUserName method, of class Login.
     * Test Case: Username is incorrectly formatted (does not contain underscore)
     */
    @Test
    public void testCheckUserNameInvalidNoUnderscore() {
        System.out.println("checkUserName - Invalid (no underscore)");
        login.registerUser("kyle1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.checkUserName();
        assertFalse(result, "Username should be invalid - no underscore");
    }

    /**
     * Test of checkUserName method, of class Login.
     * Test Case: Username is incorrectly formatted (more than five characters long)
     */
    @Test
    public void testCheckUserNameInvalidTooLong() {
        System.out.println("checkUserName - Invalid (too long)");
        login.registerUser("kyle_123", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.checkUserName();
        assertFalse(result, "Username should be invalid - more than 5 characters");
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     * Test Case: Password meets complexity requirements
     */
    @Test
    public void testCheckPasswordComplexityValid() {
        System.out.println("checkPasswordComplexity - Valid");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.checkPasswordComplexity();
        assertTrue(result, "Password should be valid - meets all complexity requirements");
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     * Test Case: Password does not meet complexity requirements
     */
    @Test
    public void testCheckPasswordComplexityInvalid() {
        System.out.println("checkPasswordComplexity - Invalid");
        login.registerUser("kyl_1", "password", "+27838968976");
        boolean result = login.checkPasswordComplexity();
        assertFalse(result, "Password should be invalid - missing capital letter, number, and special character");
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login.
     * Test Case: Cell phone is correctly formatted
     */
    @Test
    public void testCheckCellPhoneNumberValid() {
        System.out.println("checkCellPhoneNumber - Valid");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.checkCellPhoneNumber();
        assertTrue(result, "Cell phone number should be valid - starts with +27 and has correct length");
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login.
     * Test Case: Cell phone number is incorrectly formatted
     */
    @Test
    public void testCheckCellPhoneNumberInvalid() {
        System.out.println("checkCellPhoneNumber - Invalid");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553");
        boolean result = login.checkCellPhoneNumber();
        assertFalse(result, "Cell phone number should be invalid - missing international code");
    }

    /**
     * Test of registerUser method, of class Login.
     * Test Case: All registration details are valid
     */
    @Test
    public void testRegisterUserAllValid() {
        System.out.println("registerUser - All Valid");
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("successfully"), "Registration should be successful with valid details");
    }

    /**
     * Test of registerUser method, of class Login.
     * Test Case: Username is invalid
     */
    @Test
    public void testRegisterUserInvalidUsername() {
        System.out.println("registerUser - Invalid Username");
        String result = login.registerUser("kyle!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(result, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
    }

    /**
     * Test of registerUser method, of class Login.
     * Test Case: Password is invalid
     */
    @Test
    public void testRegisterUserInvalidPassword() {
        System.out.println("registerUser - Invalid Password");
        String result = login.registerUser("kyl_1", "password", "+27838968976");
        assertEquals(result, "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
    }

    /**
     * Test of registerUser method, of class Login.
     * Test Case: Cell phone number is invalid
     */
    @Test
    public void testRegisterUserInvalidCellNumber() {
        System.out.println("registerUser - Invalid Cell Number");
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals(result, "Cell phone number is incorrectly formatted or does not contain international code.");
    }

    /**
     * Test of loginUser method, of class Login.
     * Test Case: Login is successful with correct credentials
     */
    @Test
    public void testLoginUserSuccessful() {
        System.out.println("loginUser - Successful");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertTrue(result, "Login should be successful with correct credentials");
    }

    /**
     * Test of loginUser method, of class Login.
     * Test Case: Login fails with incorrect credentials
     */
    @Test
    public void testLoginUserFailed() {
        System.out.println("loginUser - Failed");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean result = login.loginUser("wronguser", "wrongpass");
        assertFalse(result, "Login should fail with incorrect credentials");
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     * Test Case: Successful login status message
     */
    @Test
    public void testReturnLoginStatusSuccessful() {
        System.out.println("returnLoginStatus - Successful");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean loginResult = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        String result = login.returnLoginStatus(loginResult);
        assertEquals(result, "Welcome Kyle, Smith it is great to see you again.");
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     * Test Case: Failed login status message
     */
    @Test
    public void testReturnLoginStatusFailed() {
        System.out.println("returnLoginStatus - Failed");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean loginResult = login.loginUser("wronguser", "wrongpass");
        String result = login.returnLoginStatus(loginResult);
        assertEquals(result, "Username or password incorrect, please try again.");
    }

    /**
     * Test of getUsername method, of class Login.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String result = login.getUsername();
        assertEquals(result, "kyl_1");
    }

    /**
     * Test of getPassword method, of class Login.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String result = login.getPassword();
        assertEquals(result, "Ch&&sec@ke99!");
    }

    /**
     * Test of getCellNumber method, of class Login.
     */
    @Test
    public void testGetCellNumber() {
        System.out.println("getCellNumber");
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String result = login.getCellNumber();
        assertEquals(result, "+27838968976");
    }

    /**
     * Test of getFirstName method, of class Login.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        String result = login.getFirstName();
        assertEquals(result, "Kyle");
    }

    /**
     * Test of getLastName method, of class Login.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        String result = login.getLastName();
        assertEquals(result, "Smith");
    }
}
