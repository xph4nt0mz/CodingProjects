import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSystem {
    private String firstName;
    private String lastName;
    private int id;
    private String position;
    private String department;
    protected double hoursWorked;
    protected double payRate;

    public EmployeeSystem(String firstName, String lastName, int id, String position, String department,
                    double hoursWorked, double payRate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.position = position;
        this.department = department;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
    }

    public double calculateSalary() {
        // Calculate employee's salary
        return hoursWorked * payRate + 50000;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public int getID() {
        return id;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmployeeTimeReportingSystem::new);
    }
    
}

class HourlyEmployee extends EmployeeSystem {
    public HourlyEmployee(String firstName, String lastName, int id, String position, String department,
                          double hoursWorked, double payRate) {
        super(firstName, lastName, id, position, department, hoursWorked, payRate);
    }

    @Override
    public double calculateSalary() {
        // Calculate salary for hourly employee
        return hoursWorked * payRate;
    }
}

class Manager extends EmployeeSystem {
    private double bonusPay;

    public Manager(String firstName, String lastName, int id, String position, String department,
                   double hoursWorked, double payRate, double bonusPay) {
        super(firstName, lastName, id, position, department, hoursWorked, payRate);
        this.bonusPay = bonusPay;
    }

    public double getBonusPay() {
        return bonusPay;
    }

    @Override
    public double calculateSalary() {
        // Calculate manager's salary including bonus
        return super.calculateSalary() + bonusPay;
    }
}

 class EmployeeTimeReportingSystem {
    private JFrame frame;
    private JTextArea textArea;
    private List<EmployeeSystem> employees;

    public EmployeeTimeReportingSystem() {
        frame = new JFrame("Employee Time Reporting System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        employees = new ArrayList<>(); // List to store employees

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(e -> {
            addEmployee();
            displayEmployees(); // Call displayEmployees after adding an employee
        });

        JButton removeButton = new JButton("Remove Employee");
        removeButton.addActionListener(e -> {
            // Prompt user to select employee to remove and update the list
            removeEmployee();
            displayEmployees();
        });

        JButton searchButton = new JButton("Search Employee");
        searchButton.addActionListener(e -> {
            searchEmployee();
            displayEmployees();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addEmployee() {
        try {
            String employeeType = JOptionPane.showInputDialog("Employee Type (Employee/Manager/Hourly):");
            if (employeeType == null || employeeType.trim().isEmpty()) {
                throw new IllegalArgumentException("Please enter a valid employee type.");
            }
    
            employeeType = employeeType.trim().toLowerCase();
    
            
            String firstName = promptForValidString("Enter FirstName: ");
            String lastName = promptForValidString("Enter Last Name:");
            
            int id = promptForPositiveInt("Enter ID Number:");
    
            String position = promptForNonEmptyString("Enter Position:");
            String department = promptForValidDepartment("Enter Department (Max 4 characters):");
    
            double hoursWorked = promptForNonNegativeDouble("Enter Hours Worked:");
            double payRate = promptForNonNegativeDouble("Enter Pay Rate:");
    
            switch (employeeType) {
                case "manager":
                    double bonusPay = promptForNonNegativeDouble("Enter Bonus Pay:");
                    Manager manager = new Manager(firstName, lastName, id, position, department, hoursWorked, payRate, bonusPay);
                    employees.add(manager);
                    break;
                case "hourly":
                    HourlyEmployee hourlyEmployee = new HourlyEmployee(firstName, lastName, id, position, department, hoursWorked, payRate);
                    employees.add(hourlyEmployee);
                    break;
                default:
                    EmployeeSystem employee = new EmployeeSystem(firstName, lastName, id, position, department, hoursWorked, payRate);
                    employees.add(employee);
                    break;
            }
    
            JOptionPane.showMessageDialog(frame, "Employee added successfully.");
            displayEmployees();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error adding employee: " + e.getMessage());
        }
    }
    
    private String promptForNonEmptyString(String message) {
        String input = JOptionPane.showInputDialog(message);
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Please enter a valid value for " + message);
        }
        return input.trim();
    }

    private String promptForValidString(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(message);
            if (input == null || input.trim().isEmpty()) {
                throw new IllegalArgumentException("Please enter a valid value for " + message);
            }
            if (input.matches("^[a-zA-Z]+$")) {
                return input.trim();  // Only accept strings containing alphabetic characters
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid string (letters only).");
            }
        }
    }
    
    private int promptForPositiveInt(String message) throws NumberFormatException {
        while (true) {
            try {
                int value = Integer.parseInt(JOptionPane.showInputDialog(message));
                if (value > 0) {
                    return value;
                } else {
                    throw new IllegalArgumentException("Please enter a valid positive integer.");
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input. " + e.getMessage());
            }
        }
    }
    
    private String promptForValidDepartment(String message) {
        while (true) {
            String department = promptForNonEmptyString(message);
            if (department.length() <= 4) {
                return department;
            } else {
                JOptionPane.showMessageDialog(frame, "Department name must be 4 characters or fewer.");
            }
        }
    }
    
    private double promptForNonNegativeDouble(String message) throws NumberFormatException {
        while (true) {
            try {
                double value = Double.parseDouble(JOptionPane.showInputDialog(message));
                if (value >= 0) {
                    return value;
                } else {
                    throw new IllegalArgumentException("Please enter a valid non-negative number.");
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input. " + e.getMessage());
            }
        }
    }
    
    
    private void removeEmployee() {
        String fullName = JOptionPane.showInputDialog("Enter Employee's Full Name to Remove:");
        employees.removeIf(emp -> emp.getFullName().equalsIgnoreCase(fullName));
    }

    private void displayEmployees() {
        SwingUtilities.invokeLater(() -> {
            textArea.setText(""); // Clear text area
            for (EmployeeSystem emp : employees) {
                textArea.append("Full Name: " + emp.getFullName() + "\n" +
                        "Position: " + emp.getPosition() + "\n" + "ID: " + emp.getID() + "\n " +  
                        "Department: " + emp.getDepartment() +
                        "\n - Salary: $" + emp.calculateSalary() + "\n");
            }
        });
    }

    private void searchEmployee() {
        String searchTerm = JOptionPane.showInputDialog("Enter Employee's Full Name or ID to Search:");
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid search term.");
            return;
        }

        searchTerm = searchTerm.trim().toLowerCase();

        EmployeeSystem foundEmployee = null;
        for (EmployeeSystem emp : employees) {
            if (emp.getFullName().toLowerCase().contains(searchTerm) ||
                String.valueOf(emp.getID()).equals(searchTerm)) {
                foundEmployee = emp;
                break;
            }
        }

        if (foundEmployee != null) {
            JOptionPane.showMessageDialog(frame,
                    "Employee Found:\n" +
                            "Full Name: " + foundEmployee.getFullName() + "\n" +
                            "Position: " + foundEmployee.getPosition() + "\n" +
                            "DepAartment: " + foundEmployee.getDepartment() + "\n" +
                            
                            "Salary: $" + foundEmployee.calculateSalary());
        } else {
            JOptionPane.showMessageDialog(frame, "Employee not found.");
        }
    }
}