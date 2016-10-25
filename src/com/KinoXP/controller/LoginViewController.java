package com.KinoXP.controller;


import com.KinoXP.model.EmployeeModel;
import com.KinoXP.model.LoginViewModel;
import com.KinoXP.view.LoginView;
import com.KinoXP.view.MenuView;


public class LoginViewController {

    MenuView menuView;
    //CREATED OBJECT
    LoginViewModel loginViewModel = new LoginViewModel();
    LoginView loginView = new LoginView();
    ManageEmployeeController manageEmployeeController = new ManageEmployeeController();


    //CHECK IF THE INSERTED USERNAME AND PASSWORD ARE AS THE ONES ESTABLISHED IN THE DATABASE//////////////////////////
    public String checkLogIn(EmployeeModel employeeModel){
        String textForLabel = "";

        textForLabel = "The username and/or password do not match!";

        //calling the connect to db method
        loginViewModel.connectToDatabase();
        //Compares if the returned result is true or false.
        if ((loginViewModel.checkLoginAndPassword(employeeModel).equals(true))) {
            //IF YES THEN :
            if(!checkIfManager(employeeModel)){
                menuView = new MenuView();
                manageEmployeeController.changeButtonVisibility(menuView);

            }else {
                menuView = new MenuView();
                menuView.start();
            }


        } else {
            textForLabel = "The username and/or password do not match!";
        }
        return textForLabel;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////USED IN RUN CLASS MAIN/////////////////////////////////////////////////////
    public void startLoginWindow(){
        LoginView loginView = new LoginView();
        loginView.start();
    }
    ////////////////////////////////////////////////////////////////////////////////////
    public void checkForInternetConnection(){
        try {
            if(loginViewModel.isInternetAvailable()){
                startLoginWindow();
            }else{
                loginView.networkAlertMethod();
                System.exit(1);
            }
        }catch (java.io.IOException IoE){
            System.out.println("Could not check for internet connection! There is an error with the method! See SRC!");
        }
    }
    public boolean checkIfManager(EmployeeModel employeeModel){

        if(employeeModel.getUserName().equals("1")&& employeeModel.getPassword().equals("1")){
            return true;
        }

        return false;
    }

}

