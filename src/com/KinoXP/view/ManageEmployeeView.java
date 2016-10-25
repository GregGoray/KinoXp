package com.KinoXP.view;

import com.KinoXP.controller.ManageEmployeeController;
import com.KinoXP.model.EmployeeModel;
import com.KinoXP.model.LoginViewModel;
import com.KinoXP.model.ManageEmployeeModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * Created by Paula/Lucia on 01/03/2016.
 */
public class ManageEmployeeView {

    private ManageEmployeeController manageEmployeeController;
    private ManageEmployeeModel manageEmployeeModel;

    private TableView<EmployeeModel> tableView = new TableView<>();
    private TableView<LoginViewModel> tableView1 = new TableView<>();

    public ManageEmployeeView() {
        manageEmployeeModel = new ManageEmployeeModel();
        manageEmployeeController = new ManageEmployeeController(manageEmployeeModel, this);
    }

    public void start() {

        //CREATING BORDER PANE WITH H-BOX CENTERED/BUTTON AND GRID PANE CENTERED IN THE MIDDLE
        Stage primaryStage = new Stage();
        HBox hBox1 = new HBox();
        hBox1.setPrefSize(600, 80);
        HBox hBox = new HBox(20);
        hBox.setPrefSize(600, 80);
        BorderPane borderPane = new BorderPane();

        //BUTTONS
        Button logOut = new Button("LOG OUT");
        logOut.setId("back");

        Button goBack = new Button("GO BACK");
        goBack.setId("back");

        Button add = new Button("ADD");
        add.setId("back");

        Button delete = new Button("DELETE");
        delete.setId("back");

        Button edit = new Button("EDIT");
        edit.setId("back");

        //TAB PANE FOR HOLDING THE TABLE VIEWS
        TabPane tabPane = new TabPane();
        Tab tab = new Tab();
        Tab tab1 = new Tab();
        tab1.setText("LOGIN INFO");
        tab.setText("EMPLOYEE INFO");
        tab.setContent(new Rectangle(200, 200, Color.LIGHTSTEELBLUE));
        tab.setContent(new Cylinder(200, 200));
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().addAll(tab, tab1);

        //TAB PANE ACTION
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

            //OVERWRITE FUNCTIONALITY OF BUTTON WHEN CHANGING TO DIFFERENT TAP
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
                if (newTab == tab1) {
                    add.setDisable(true);
                    edit.setDisable(true);
                    delete.setDisable(true);
                } else {
                    add.setDisable(false);
                    edit.setDisable(false);
                    delete.setDisable(false);
                }
            }

        });

        //EDIT BOTH EMPLOYEE INFORMATION AND LOG IN INFORMATION
        edit.setOnAction(event -> {
            EmployeeModel employeeModel = tableView.getSelectionModel().getSelectedItem();
            if (employeeModel != null) {

                Stage stage1 = new Stage();
                GridPane gridPane = new GridPane();
                Label name = new Label("Name");
                TextField nameTextField = new TextField();
                Label surname = new Label("Surname");
                TextField surnameText = new TextField();
                Label email = new Label("Email");
                TextField emailText = new TextField();
                Label phoneNumber = new Label("Phone number");
                TextField phoneNumberText = new TextField();
                Label position = new Label("Position");
                TextField positionTextField = new TextField();
                Button confirm = new Button("Confirm");
                confirm.setId("button");
                Label userName = new Label("User name");
                TextField userNameTextField = new TextField();
                Label password = new Label("Password");
                TextField passwordTextField = new TextField();

                GridPane.setConstraints(name, 0, 0);
                GridPane.setConstraints(nameTextField, 1, 0);
                GridPane.setConstraints(surname, 0, 1);
                GridPane.setConstraints(surnameText, 1, 1);
                GridPane.setConstraints(email, 0, 2);
                GridPane.setConstraints(emailText, 1, 2);
                GridPane.setConstraints(phoneNumber, 0, 3);
                GridPane.setConstraints(phoneNumberText, 1, 3);
                GridPane.setConstraints(position, 0, 4);
                GridPane.setConstraints(positionTextField, 1, 4);
                GridPane.setConstraints(userName, 0, 5);
                GridPane.setConstraints(userNameTextField, 1, 5);
                GridPane.setConstraints(password, 0, 6);
                GridPane.setConstraints(passwordTextField, 1, 6);
                GridPane.setConstraints(confirm, 1, 7);

                userNameTextField.setText(employeeModel.getUserName());
                nameTextField.setText(employeeModel.getName());
                surnameText.setText(employeeModel.getSurname());
                emailText.setText(employeeModel.getEmail());
                phoneNumberText.setText(String.valueOf(employeeModel.getPhoneNumber()));
                positionTextField.setText(employeeModel.getJobTitle());
                passwordTextField.setText(manageEmployeeController.getPassword(employeeModel.getUserName()));


                confirm.setStyle("-fx-font-size: 20");


                gridPane.setAlignment(Pos.CENTER);

                gridPane.getChildren().addAll(name, nameTextField, surname, surnameText, email, emailText, phoneNumber, phoneNumberText, position, userName, userNameTextField, password, passwordTextField, positionTextField, confirm);

                gridPane.setHgap(20);
                gridPane.setVgap(30);
                Scene scene = new Scene(gridPane, 1000, 650);
                scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                stage1.setScene(scene);

                stage1.show();

                confirm.setOnAction(event1 -> {
                    manageEmployeeController.editEmployee(userNameTextField.getText(), nameTextField.getText(), surnameText.getText(), emailText.getText(), Integer.parseInt(phoneNumberText.getText()), positionTextField.getText(), employeeModel.getUserName());
                    manageEmployeeController.editLogin(userNameTextField.getText(), passwordTextField.getText(), employeeModel.getUserName());

                    tableView.setItems(manageEmployeeController.returnEmployeeInfo());
                    tableView1.setItems(manageEmployeeController.returnLogInInfo());
                    stage1.close();

                });

            } else {
                updateAlertMessage("You need to select a row in order to edit an employee");
            }
        });

        //DELETE EMPLOYEE AND LOG IN INFORMATION
        delete.setOnAction(event -> {
            EmployeeModel employeeModel = tableView.getSelectionModel().getSelectedItem();
            if (employeeModel != null) {

                //DELETE ROW FROM TABLE VIEW
                manageEmployeeController.returnEmployeeInfo().remove(tableView.getSelectionModel().getSelectedItem());

                //DELETE SELECTED INFORMATION FROM DATABASE
                manageEmployeeController.deleteEmployeeAction(tableView.getSelectionModel().getSelectedItem().getUserName());
                manageEmployeeController.deleteLogInCredentials(tableView.getSelectionModel().getSelectedItem().getUserName());

                //ADD THE NEW INFORMATION TO THE TABLE VIEW
                tableView.setItems(manageEmployeeController.returnEmployeeInfo());
                tableView1.setItems(manageEmployeeModel.getLoginInformation());

            } else {
                updateAlertMessage("You need to select a row in order to delete an employee");
            }

        });

        hBox.getChildren().addAll(goBack, add, edit, delete, logOut);

        borderPane.setBottom(hBox);
        tab.setContent(showBookings());
        tab1.setContent(showLogInInfo());

        borderPane.setTop(tabPane);

        //ADD BOTH EMPLOYEE AND LOGIN INFORMATION TO DATABASE
        add.setOnAction(event -> {
            Stage stage1 = new Stage();
            GridPane gridPane = new GridPane();
            Label name = new Label("Name");
            TextField nameTextField = new TextField();
            Label surname = new Label("Surname");
            TextField surnameText = new TextField();
            Label email = new Label("Email");
            TextField emailText = new TextField();
            Label phoneNumber = new Label("Phone number");
            TextField phoneNumberText = new TextField();
            Label position = new Label("Position");
            TextField positionTextField = new TextField();
            Button confirm = new Button("Confirm");
            confirm.setId("button");
            Label userName = new Label("User name");
            TextField userNameTextField = new TextField();
            Label password = new Label("Password");
            TextField passwordTextField = new TextField();

            //POSITION TEXT AND LABEL ELEMENTS
            GridPane.setConstraints(name, 0, 0);
            GridPane.setConstraints(nameTextField, 1, 0);
            GridPane.setConstraints(surname, 0, 1);
            GridPane.setConstraints(surnameText, 1, 1);
            GridPane.setConstraints(email, 0, 2);
            GridPane.setConstraints(emailText, 1, 2);
            GridPane.setConstraints(phoneNumber, 0, 3);
            GridPane.setConstraints(phoneNumberText, 1, 3);
            GridPane.setConstraints(position, 0, 4);
            GridPane.setConstraints(positionTextField, 1, 4);
            GridPane.setConstraints(userName, 0, 5);
            GridPane.setConstraints(userNameTextField, 1, 5);
            GridPane.setConstraints(password, 0, 6);
            GridPane.setConstraints(passwordTextField, 1, 6);
            GridPane.setConstraints(confirm, 1, 7);


            confirm.setStyle("-fx-font-size: 20");

            gridPane.setAlignment(Pos.CENTER);

            gridPane.getChildren().addAll(name, nameTextField, surname, surnameText, email, emailText, phoneNumber, phoneNumberText, position, userName, userNameTextField, password, passwordTextField, positionTextField, confirm);

            gridPane.setHgap(20);
            gridPane.setVgap(30);
            Scene scene = new Scene(gridPane, 1000, 650);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage1.setScene(scene);
            stage1.show();


            //SAVES THE INFO TO THE DATABASE AND UPDATES THE TABLE VIEWS
            confirm.setOnAction(event1 -> {
                if (userNameTextField.getText().length() == 0 || nameTextField.getText().length() == 0 ||
                        surnameText.getText().length() == 0 || emailText.getText().length() == 0 ||
                        phoneNumberText.getText().length() == 0 || positionTextField.getText().length() == 0) {
                    updateAlertMessage("All text fields must contain information in order to add an employee to database");


                }
                try {
                    String.valueOf(phoneNumberText.getText());

                    manageEmployeeController.addEmployeeAction(userNameTextField.getText(), nameTextField.getText(),
                            surnameText.getText(), emailText.getText(), Integer.parseInt(phoneNumberText.getText()),
                            positionTextField.getText());
                    manageEmployeeController.addLogInCredentials(userNameTextField.getText(), passwordTextField.getText());
                    tableView.setItems(manageEmployeeController.returnEmployeeInfo());
                    tableView1.setItems(manageEmployeeController.returnLogInInfo());

                    stage1.close();

                } catch (NumberFormatException e) {
                    updateAlertMessage("Phone number cannot contain letters!");
                }

            });
        });

        //LOG OUT BUTTON
        logOut.setOnAction(event -> {
            primaryStage.close();
            LoginView loginView = new LoginView();
            loginView.start();

        });

        //GO BACK BUTTON
        goBack.setOnAction(event -> {
            primaryStage.close();
            MenuView menuView = new MenuView();
            menuView.start();
        });


        hBox.setAlignment(Pos.CENTER);
        borderPane.setAlignment(logOut, Pos.TOP_RIGHT);
        borderPane.setAlignment(goBack, Pos.TOP_LEFT);
        borderPane.setId("backgroundImage");

        Scene scene = new Scene(borderPane, 1000, 569);

        //STAGE
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //TABLE VIEW FOR EMPLOYEE INFO
    public TableView<EmployeeModel> showBookings() {


        manageEmployeeController.returnEmployeeInfo();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        javafx.scene.control.TableColumn<EmployeeModel, String> userName = new javafx.scene.control.TableColumn<>("User name");
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userName.setMinWidth(100);
        userName.setId("whitepls");

        javafx.scene.control.TableColumn<EmployeeModel, String> name = new javafx.scene.control.TableColumn<>("Name:");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setMinWidth(200);
        name.setId("whitepls");

        javafx.scene.control.TableColumn<EmployeeModel, String> surname = new javafx.scene.control.TableColumn<>("Surname:");
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surname.setMinWidth(100);
        surname.setId("whitepls");


        javafx.scene.control.TableColumn<EmployeeModel, String> email = new javafx.scene.control.TableColumn<>("Email:");
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        email.setMinWidth(150);
        email.setId("whitepls");

        javafx.scene.control.TableColumn<EmployeeModel, Integer> phoneNumber = new javafx.scene.control.TableColumn<>("Phone nuber:");
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNumber.setMinWidth(150);
        phoneNumber.setId("whitepls");


        javafx.scene.control.TableColumn<EmployeeModel, String> jobTitle = new javafx.scene.control.TableColumn<>("Job title:");
        jobTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        jobTitle.setMinWidth(150);
        jobTitle.setId("whitepls");

        tableView.getColumns().addAll(userName, name, surname, email, phoneNumber, jobTitle);
        tableView.setItems(manageEmployeeController.returnEmployeeInfo());

        return tableView;

    }

    //TABLE VIEW FOR LOG IN INFO
    public TableView<LoginViewModel> showLogInInfo() {

        ManageEmployeeModel manageEmployeeModel = new ManageEmployeeModel();
        tableView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        javafx.scene.control.TableColumn<LoginViewModel, String> userName = new javafx.scene.control.TableColumn<>("User name");
        userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        userName.setMinWidth(50);
        userName.setId("whitepls");

        javafx.scene.control.TableColumn<LoginViewModel, String> password = new javafx.scene.control.TableColumn<>("Password");
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        password.setMinWidth(50);
        password.setId("whitepls");


        tableView1.getColumns().addAll(userName, password);

        tableView1.setItems(manageEmployeeModel.getLoginInformation());

        return tableView1;

    }

    //METHOD FOR THE ALERT MESSAGES SHOWN TO THE USER
    public static void updateAlertMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
