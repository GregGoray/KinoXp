package com.KinoXP.model;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;


public class LoginViewModel {
    public static Connection conn = null;
    private String userName;
    private String password;
    EmployeeModel employeeModel;



    //////////////////////////////WHY DO WE HAVE THESE HERE???? ARE THEY USED SOMEWHERE??? //////////////////////////
    public LoginViewModel(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public LoginViewModel(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //CHECK FOR INTERNET CONNECTIVITY////////////////////////////////////////////////////////////////////////////////////

    private boolean isHostAvailable(String hostName) throws IOException {
        try(Socket socket = new Socket())
        {
            int port = 80;
            InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
            socket.connect(socketAddress, 3000);

            return true;
        }
        catch(UnknownHostException unknownHost)
        {
            return false;
        }
    }
    public boolean isInternetAvailable() throws IOException {
        return isHostAvailable("google.com") || isHostAvailable("amazon.com")
                || isHostAvailable("facebook.com")|| isHostAvailable("apple.com");
    }

    //DATABASE CONNECTING METHOD
    //PLEASE APPLY YOUR LOCAL MODIFICATIONS IN ORDER FOR THESE TO WORK!!!
    public void connectToDatabase(){
        System.out.println("***********Welcome to connections**************");
        try {

            String DB_URL = "jdbc:mysql://sql2.freesqldatabase.com:3306/sql2108018";
            String USER = "sql2108018";
            String PASS = "nP5%zC6%";
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("conn obj created" + conn + " message: ");
        }
        catch (SQLException e)
        {
            System.out.println("db error" + e.getMessage());
        }
    }
    //CHECK LOG IN AND PASSWORD WITH SQL - DATABASE (PLEASE MODIFY HERE AND UP FOR YOUR LOCAL DATABASES!!!!!)
    public Boolean checkLoginAndPassword(EmployeeModel employeeModel){

            try {
                String sql = "SELECT * FROM LogIn WHERE userName=? AND password =?";

                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, employeeModel.getUserName());
                preparedStatement.setString(2, employeeModel.getPassword());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    employeeModel = new EmployeeModel(resultSet.getString(1), resultSet.getString(2));
                    return true;
                } else {
                    employeeModel = null;
                    return false;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        return false;
    }
}
