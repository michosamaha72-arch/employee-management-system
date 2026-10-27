package com.example.employeemisapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EmployeeMISApp extends Application {

    private Stage mainStage;
    private Scene loginScene;
    private Scene dashboardScene;
    private Scene directoryScene;

    private final ObservableList<Employee> employeeData = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.mainStage = primaryStage;
        this.mainStage.setTitle("Corporate MIS - Employee Portal");

        loadInitialData();

        this.loginScene = buildLoginScene();
        this.dashboardScene = buildDashboardScene();
        this.directoryScene = buildDirectoryScene();

        mainStage.setScene(loginScene);
        mainStage.show();
    }

    public static class Employee {
        private final String id;
        private final String fullName;
        private final String department;
        private final double baseSalary;
        private final String employmentType;
        private final LocalDate hireDate;
        private final boolean active;

        public Employee(String id, String fullName, String department, double baseSalary, 
                        String employmentType, LocalDate hireDate, boolean active) {
            this.id = id;
            this.fullName = fullName;
            this.department = department;
            this.baseSalary = baseSalary;
            this.employmentType = employmentType;
            this.hireDate = hireDate;
            this.active = active;
        }

        public String getId() { return id; }
        public String getFullName() { return fullName; }
        public String getDepartment() { return department; }
        public double getBaseSalary() { return baseSalary; }
        public String getEmploymentType() { return employmentType; }
        public LocalDate getHireDate() { return hireDate; }
        public boolean isActive() { return active; }
    }

    private Scene buildLoginScene() {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        Label header = new Label("Employee MIS Sign-In");
        header.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Label userLabel = new Label("Username:");
        TextField userField = new TextField();
        userField.setPromptText("admin");

        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();
        passField.setPromptText("1234");

        grid.add(userLabel, 0, 0);
        grid.add(userField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passField, 1, 1);

        Label errorLabel = new Label();

        Button loginBtn = new Button("Sign In");
        loginBtn.setDefaultButton(true);
        
        loginBtn.setOnAction(e -> {
            if (userField.getText().equalsIgnoreCase("admin") && passField.getText().equals("1234")) {
                errorLabel.setText("");
                userField.clear();
                passField.clear();
                mainStage.setScene(dashboardScene);
            } else {
                errorLabel.setText("Invalid credentials (Use: admin / 1234)");
            }
        });

        root.getChildren().addAll(header, grid, loginBtn, errorLabel);
        return new Scene(root, 700, 500);
    }

    private Scene buildDashboardScene() {
        BorderPane root = new BorderPane();
        root.setTop(createHeader("Dashboard"));

        VBox content = new VBox(20);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(40));

        Label welcomeLabel = new Label("Welcome to the Employee Management System");
        welcomeLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 18));

        Button viewDirectoryBtn = new Button("Open Employee Directory");
        viewDirectoryBtn.setOnAction(e -> mainStage.setScene(directoryScene));

        Button logoutBtn = new Button("Sign Out");
        logoutBtn.setOnAction(e -> mainStage.setScene(loginScene));

        content.getChildren().addAll(welcomeLabel, viewDirectoryBtn, logoutBtn);
        root.setCenter(content);

        return new Scene(root, 800, 600);
    }

    private Scene buildDirectoryScene() {
        BorderPane root = new BorderPane();
        root.setTop(createHeader("Employee Directory"));

        TableView<Employee> table = new TableView<>();
        table.setItems(employeeData);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        TableColumn<Employee, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Employee, String> nameCol = new TableColumn<>("Full Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        TableColumn<Employee, String> deptCol = new TableColumn<>("Department");
        deptCol.setCellValueFactory(new PropertyValueFactory<>("department"));

        TableColumn<Employee, Double> salaryCol = new TableColumn<>("Base Salary ($)");
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("baseSalary"));

        TableColumn<Employee, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("employmentType"));

        TableColumn<Employee, LocalDate> dateCol = new TableColumn<>("Hire Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        TableColumn<Employee, Boolean> activeCol = new TableColumn<>("Active");
        activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));

        table.getColumns().addAll(idCol, nameCol, deptCol, salaryCol, typeCol, dateCol, activeCol);

        HBox toolbar = new HBox(15);
        toolbar.setPadding(new Insets(15));
        toolbar.setAlignment(Pos.CENTER_LEFT);

        Button addEmployeeBtn = new Button("+ Add New Employee");
        addEmployeeBtn.setOnAction(e -> openAddEmployeeStage());

        Button backBtn = new Button("Back to Dashboard");
        backBtn.setOnAction(e -> mainStage.setScene(dashboardScene));

        toolbar.getChildren().addAll(addEmployeeBtn, backBtn);

        root.setCenter(table);
        root.setBottom(toolbar);

        return new Scene(root, 850, 600);
    }

    private void openAddEmployeeStage() {
        Stage addStage = new Stage();
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.setTitle("Add New Employee");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setAlignment(Pos.CENTER);

        TextField idField = new TextField();
        grid.add(new Label("Employee ID:"), 0, 0);
        grid.add(idField, 1, 0);

        TextField nameField = new TextField();
        grid.add(new Label("Full Name:"), 0, 1);
        grid.add(nameField, 1, 1);

        ComboBox<String> deptCombo = new ComboBox<>();
        deptCombo.getItems().addAll("Engineering", "Human Resources", "Finance", "Marketing", "Sales");
        deptCombo.getSelectionModel().selectFirst();
        grid.add(new Label("Department:"), 0, 2);
        grid.add(deptCombo, 1, 2);

        TextField salaryField = new TextField();
        grid.add(new Label("Base Salary ($):"), 0, 3);
        grid.add(salaryField, 1, 3);

        ToggleGroup typeGroup = new ToggleGroup();
        RadioButton fullTimeRadio = new RadioButton("Full-Time");
        fullTimeRadio.setToggleGroup(typeGroup);
        fullTimeRadio.setSelected(true);
        RadioButton partTimeRadio = new RadioButton("Part-Time");
        partTimeRadio.setToggleGroup(typeGroup);

        HBox typeBox = new HBox(10, fullTimeRadio, partTimeRadio);
        grid.add(new Label("Employment Type:"), 0, 4);
        grid.add(typeBox, 1, 4);

        DatePicker datePicker = new DatePicker(LocalDate.now());
        grid.add(new Label("Hire Date:"), 0, 5);
        grid.add(datePicker, 1, 5);

        CheckBox activeCheckBox = new CheckBox("Is Currently Active");
        activeCheckBox.setSelected(true);
        grid.add(new Label("Status:"), 0, 6);
        grid.add(activeCheckBox, 1, 6);

        Button submitBtn = new Button("Submit");
        Label statusLabel = new Label();

        submitBtn.setOnAction(e -> {
            try {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String dept = deptCombo.getValue();
                double salary = Double.parseDouble(salaryField.getText().trim());
                String type = ((RadioButton) typeGroup.getSelectedToggle()).getText();
                LocalDate date = datePicker.getValue();
                boolean isActive = activeCheckBox.isSelected();

                if (id.isEmpty() || name.isEmpty() || date == null) {
                    statusLabel.setText("Please fill out all required fields.");
                    return;
                }

                Employee newEmp = new Employee(id, name, dept, salary, type, date, isActive);
                employeeData.add(newEmp);

                addStage.close();
            } catch (NumberFormatException ex) {
                statusLabel.setText("Invalid Salary format. Enter a valid number.");
            }
        });

        VBox layout = new VBox(10, grid, statusLabel, submitBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));

        addStage.setScene(new Scene(layout, 420, 420));
        addStage.showAndWait();
    }

    private HBox createHeader(String titleText) {
        HBox header = new HBox();
        header.setPadding(new Insets(15));

        Label title = new Label(titleText);
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));

        header.getChildren().add(title);
        return header;
    }

    private void loadInitialData() {
        employeeData.add(new Employee("E-101", "Jane Doe", "Engineering", 85000.0, "Full-Time", LocalDate.of(2022, 3, 15), true));
        employeeData.add(new Employee("E-102", "John Smith", "Marketing", 62000.0, "Part-Time", LocalDate.of(2023, 6, 1), true));
    }
}