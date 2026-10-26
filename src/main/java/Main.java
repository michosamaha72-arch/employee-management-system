package com.example.work;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {

    // ============ Data Model (same idea as Student in Example 5) ============
    public static class Employee {
        private final int id;
        private final String name;
        private final String email;
        private final String department;
        private final double salary;
        private final String hireDate;
        private final String gender;
        private final boolean fullTime;

        public Employee(int id, String name, String email, String department,
                        double salary, String hireDate, String gender, boolean fullTime) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.department = department;
            this.salary = salary;
            this.hireDate = hireDate;
            this.gender = gender;
            this.fullTime = fullTime;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        public String getHireDate() {
            return hireDate;
        }

        public String getGender() {
            return gender;
        }

        public boolean getFullTime() {
            return fullTime;
        }
    }

    @Override
    public void start(Stage primaryStage) {

        // The form adds employees to this list, the table shows this list
        ObservableList<Employee> data = FXCollections.observableArrayList();

        // ================= STAGE 2 - SCENE 1: the form =================
        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(25, 25, 25, 25));

        Text formTitle = new Text("Add New Employee");
        formTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        formGrid.add(formTitle, 0, 0, 2, 1);

        Label idLbl = new Label("ID:");
        formGrid.add(idLbl, 0, 1);
        TextField idFld = new TextField();
        idFld.setPromptText("Enter a number");
        formGrid.add(idFld, 1, 1);

        Label nameLbl = new Label("Name:");
        formGrid.add(nameLbl, 0, 2);
        TextField nameFld = new TextField();
        nameFld.setPromptText("Enter the name");
        formGrid.add(nameFld, 1, 2);

        Label emailLbl = new Label("Email:");
        formGrid.add(emailLbl, 0, 3);
        TextField emailFld = new TextField();
        emailFld.setPromptText("Enter the email");
        formGrid.add(emailFld, 1, 3);

        Label deptLbl = new Label("Department:");
        formGrid.add(deptLbl, 0, 4);
        ChoiceBox<String> deptBox = new ChoiceBox<>();
        deptBox.getItems().addAll("HR", "IT", "Finance", "Marketing", "Sales");
        deptBox.getSelectionModel().selectFirst();
        formGrid.add(deptBox, 1, 4);

        Label salaryLbl = new Label("Salary:");
        formGrid.add(salaryLbl, 0, 5);
        TextField salaryFld = new TextField();
        salaryFld.setPromptText("Enter a number");
        formGrid.add(salaryFld, 1, 5);

        Label dateLbl = new Label("Hire Date:");
        formGrid.add(dateLbl, 0, 6);
        DatePicker hireDatePck = new DatePicker();
        hireDatePck.setValue(LocalDate.now());
        formGrid.add(hireDatePck, 1, 6);

        Label genderLbl = new Label("Gender:");
        formGrid.add(genderLbl, 0, 7);
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setSelected(true);
        RadioButton femaleRadio = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(maleRadio, femaleRadio);
        genderBox.setSpacing(25);
        formGrid.add(genderBox, 1, 7);

        Label fullTimeLbl = new Label("Status:");
        formGrid.add(fullTimeLbl, 0, 8);
        CheckBox fullTimeCheck = new CheckBox("Full-time");
        formGrid.add(fullTimeCheck, 1, 8);

        Button submitBtn = new Button("Submit");
        Button viewTableBtn = new Button("View Table");
        HBox buttonBox = new HBox(submitBtn, viewTableBtn);
        buttonBox.setSpacing(10);
        formGrid.add(buttonBox, 1, 9);

        Text formMsg = new Text();
        formGrid.add(formMsg, 0, 10, 2, 1);

        Scene formScene = new Scene(formGrid, 420, 560);

        // ================= STAGE 2 - SCENE 2: the table =================
        Text tableTitle = new Text("All Employees");
        tableTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        TableView<Employee> table = new TableView<>();

        TableColumn<Employee, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setMinWidth(50);

        TableColumn<Employee, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setMinWidth(100);

        TableColumn<Employee, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setMinWidth(150);

        TableColumn<Employee, String> deptCol = new TableColumn<>("Department");
        deptCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        deptCol.setMinWidth(100);

        TableColumn<Employee, Double> salaryCol = new TableColumn<>("Salary");
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
        salaryCol.setMinWidth(80);

        TableColumn<Employee, String> dateCol = new TableColumn<>("Hire Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        dateCol.setMinWidth(100);

        TableColumn<Employee, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        genderCol.setMinWidth(70);

        TableColumn<Employee, Boolean> fullTimeCol = new TableColumn<>("Full-time");
        fullTimeCol.setCellValueFactory(new PropertyValueFactory<>("fullTime"));
        fullTimeCol.setMinWidth(80);

        table.getColumns().addAll(idCol, nameCol, emailCol, deptCol,
                salaryCol, dateCol, genderCol, fullTimeCol);
        table.setItems(data);

        Button backBtn = new Button("Back to Form");

        VBox tableBox = new VBox(25);
        tableBox.setPadding(new Insets(25));
        tableBox.getChildren().addAll(tableTitle, table, backBtn);

        Scene tableScene = new Scene(tableBox, 800, 450);

        // ================= STAGE 2: the window that holds both scenes =================
        Stage employeeStage = new Stage();
        employeeStage.setTitle("Employee Management System");
        employeeStage.setScene(formScene);

        // Switching between the two scenes in the SAME stage
        viewTableBtn.setOnAction(evt -> employeeStage.setScene(tableScene));
        backBtn.setOnAction(evt -> employeeStage.setScene(formScene));

        // Submit button: read the form, validate, add to the list
        submitBtn.setOnAction(evt -> {
            String idText = idFld.getText();
            String name = nameFld.getText();
            String email = emailFld.getText();
            String salaryText = salaryFld.getText();

            if (idText.isEmpty() || name.isEmpty() || email.isEmpty()
                    || salaryText.isEmpty() || hireDatePck.getValue() == null) {
                formMsg.setFill(Color.RED);
                formMsg.setText("All fields are required!");
            } else {
                try {
                    int id = Integer.parseInt(idText);
                    double salary = Double.parseDouble(salaryText);
                    String department = deptBox.getValue();
                    String hireDate = hireDatePck.getValue().toString();
                    String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
                    boolean fullTime = fullTimeCheck.isSelected();

                    data.add(new Employee(id, name, email, department,
                            salary, hireDate, gender, fullTime));

                    // Reset the form
                    idFld.setText("");
                    nameFld.setText("");
                    emailFld.setText("");
                    deptBox.getSelectionModel().selectFirst();
                    salaryFld.setText("");
                    hireDatePck.setValue(LocalDate.now());
                    maleRadio.setSelected(true);
                    fullTimeCheck.setSelected(false);

                    formMsg.setFill(Color.GREEN);
                    formMsg.setText("Employee added successfully!");
                } catch (NumberFormatException ex) {
                    formMsg.setFill(Color.RED);
                    formMsg.setText("ID and Salary must be numbers!");
                }
            }
        });

        // ================= STAGE 1: the sign-in form =================
        GridPane loginGrid = new GridPane();
        loginGrid.setAlignment(Pos.CENTER);
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);
        loginGrid.setPadding(new Insets(25, 25, 25, 25));

        Text loginTitle = new Text("Welcome");
        loginTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        loginGrid.add(loginTitle, 0, 0, 2, 1);

        Label userLbl = new Label("User Name:");
        loginGrid.add(userLbl, 0, 1);
        TextField userFld = new TextField();
        loginGrid.add(userFld, 1, 1);

        Label pwLbl = new Label("Password:");
        loginGrid.add(pwLbl, 0, 2);
        PasswordField pwBox = new PasswordField();
        loginGrid.add(pwBox, 1, 2);

        Button signInBtn = new Button("Sign in");
        loginGrid.add(signInBtn, 1, 4);

        Text errorMsg = new Text();
        errorMsg.setFill(Color.RED);
        loginGrid.add(errorMsg, 0, 5, 2, 1);

        signInBtn.setOnAction(evt -> {
            String username = userFld.getText();
            String password = pwBox.getText();
            if (username.equals("") || password.equals("")) {
                errorMsg.setText("Username and password are required!");
            } else if (!username.equals("admin") || !password.equals("123456")) {
                errorMsg.setText("Incorrect username and/or password");
            } else {
                // Correct login: show Stage 2 on top of Stage 1
                employeeStage.setX(primaryStage.getX() + 100);
                employeeStage.setY(primaryStage.getY() + 100);
                employeeStage.show();
            }
        });

        Scene loginScene = new Scene(loginGrid, 300, 275);
        primaryStage.setTitle("Sign In");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}