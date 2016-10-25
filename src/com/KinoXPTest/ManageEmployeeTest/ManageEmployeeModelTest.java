package com.KinoXPTest.ManageEmployeeTest;

import com.KinoXP.model.EmployeeModel;
import com.KinoXP.model.LoginViewModel;
import com.KinoXP.model.ManageEmployeeModel;
import javafx.collections.ObservableList;
import org.junit.Test;

import javax.security.auth.spi.LoginModule;

import static org.junit.Assert.*;

/**
 * Created by Palko.
 */
public class ManageEmployeeModelTest {

    @Test
    public void testInsertLogIn() throws Exception {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.connectToDatabase();

        ManageEmployeeModel manageEmployeeModel = new ManageEmployeeModel();

        manageEmployeeModel.insertLogIn("SFS", "lucia");


        assertNotNull(manageEmployeeModel.checkUserName("SFS"));

    }



        @Test
    public void testInsertEmployee() throws Exception {

        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.connectToDatabase();

        ManageEmployeeModel manageEmployeeModel = new ManageEmployeeModel();
        manageEmployeeModel.insertEmployee("rr","name","surname","email",423423,"jobTitle");

        ObservableList<EmployeeModel> employeesInfo = manageEmployeeModel.getEmployeeInformation();


        String userName ="";
        for (int i = 0; i < employeesInfo.size(); i++) {
            if(employeesInfo.get(i).getUserName().equals("rr")) {
            userName = employeesInfo.get(i).getUserName();
                assertEquals("rr",userName);

            }

        }

    }

    public void testEditEmployee() throws Exception {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.connectToDatabase();

        ManageEmployeeModel manageEmployeeModel = new ManageEmployeeModel();

        manageEmployeeModel.editEmployee("lucia","ahoj","sdfsdf","email",423423,"jobTitle","lucia");

        ObservableList<EmployeeModel>employeesInfo = manageEmployeeModel.getEmployeeInformation();

        String name ="";
        for (int i = 0; i < employeesInfo.size(); i++) {
            if(employeesInfo.get(i).getName().equals("ahoj")) {

                name = employeesInfo.get(i).getName();
                assertEquals("ahoj",name);

            }

        }


    }

    @Test
    public void testEditEmployeeWithoutUserName() throws Exception {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.connectToDatabase();

        ManageEmployeeModel manageEmployeeModel = new ManageEmployeeModel();

        manageEmployeeModel.editEmployeeWithoutUserName("lucia","strom","sdfsdf",4454,"mail","jobTitle");

        ObservableList<EmployeeModel>employeesInfo = manageEmployeeModel.getEmployeeInformation();

        String name ="";
        for (int i = 0; i < employeesInfo.size(); i++) {
            if(employeesInfo.get(i).getName().equals("strom")) {

                name = employeesInfo.get(i).getName();
                assertEquals("strom",name);

            }

        }


    }
    @Test
    public void testEditLogIn() throws Exception {

        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.connectToDatabase();

        ManageEmployeeModel manageEmployeeModel = new ManageEmployeeModel();

        manageEmployeeModel.editLogIn("L","4343","Lucia");



            assertEquals("L", manageEmployeeModel.checkUserName("L"));

    }

    @Test
    public void testDeleteEmployee() throws Exception {

        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.connectToDatabase();

        ManageEmployeeModel manageEmployeeModel = new ManageEmployeeModel();

        manageEmployeeModel.deleteEmployee("ahoj");

        assertEquals(3,manageEmployeeModel.getEmployeeInformation().size());

    }

    @Test
    public void testDeleteLogInCredentials() throws Exception {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.connectToDatabase();

        ManageEmployeeModel manageEmployeeModel = new ManageEmployeeModel();

        manageEmployeeModel.deleteLogInCredentials("L");

        assertEquals(3,manageEmployeeModel.getLoginInformation().size());

    }


    @Test
    public void testCheckUserName() throws Exception {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.connectToDatabase();

        ManageEmployeeModel manageEmployeeModel = new ManageEmployeeModel();
        assertEquals("ahoj",manageEmployeeModel.checkUserName("ahoj"));

    }

    @Test
    public void testGetEmployeeInformation() throws Exception {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.connectToDatabase();

        ManageEmployeeModel manageEmployeeModel = new ManageEmployeeModel();
        ObservableList<EmployeeModel>employeeInformation = manageEmployeeModel.getEmployeeInformation();

        String name ="";
        for (int i = 0; i < employeeInformation.size(); i++) {
            if(employeeInformation.get(i).getName().equals("fsdfsfs")) {

                name= employeeInformation.get(i).getName();

                assertEquals("fsdfsfs",name);

            }

        }


    }

    @Test
    public void testGetLoginInformation() throws Exception {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.connectToDatabase();

        ManageEmployeeModel manageEmployeeModel = new ManageEmployeeModel();

        ObservableList<LoginViewModel>loginInformation = manageEmployeeModel.getLoginInformation();

        String userName="";
        for(int i=0;i<loginInformation.size();i++){
            if(loginInformation.get(i).getUserName().equals("ahoj")){
                userName=loginInformation.get(i).getUserName();
                assertEquals("ahoj",manageEmployeeModel.checkUserName("ahoj"));
            }

        }
    }

    @Test
    public void testGetPassword() throws Exception {

        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.connectToDatabase();

        ManageEmployeeModel manageEmployeeModel = new ManageEmployeeModel();

        ObservableList<LoginViewModel> loginInformation = manageEmployeeModel.getLoginInformation();

        String password = "";
        for (int i = 0; i < loginInformation.size(); i++) {
            if (loginInformation.get(i).getPassword().equals("lucia")) {
                password = loginInformation.get(i).getPassword();
                assertEquals("lucia", loginInformation.get(i).getPassword());

            }
        }
    }
}