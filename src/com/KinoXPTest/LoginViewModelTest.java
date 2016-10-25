package com.KinoXPTest;

import com.KinoXP.model.EmployeeModel;
import com.KinoXP.model.LoginViewModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class LoginViewModelTest{
    LoginViewModel loginViewModel = new LoginViewModel();
    @Before
    public void connectToDatabase() {
        loginViewModel.connectToDatabase();
    }
    @Test // TESTING THE LOG IN METHODS!
    public void testLogIn() {
        EmployeeModel test = new EmployeeModel("1", "1");
        assertEquals(true, loginViewModel.checkLoginAndPassword(test));
    }

}
