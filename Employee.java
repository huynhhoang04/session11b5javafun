package ra.entity;

import java.util.Scanner;

public class Employee {
    private String employeeId;
    private String employeeName;
    private Role role;
    private double salary;

    public Employee() {
    }

    public Employee(String employeeId, String employeeName, Role role, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.role = role;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void inputData(Scanner scanner, Employee[] arrEmp, int index) {
        while (true) {
            System.out.print("Nhập mã nhân viên (E0001): ");
            String idInput = scanner.nextLine();
            if (idInput.matches("^E.{4}$")) {
                boolean isExist = false;
                for (int i = 0; i < index; i++) {
                    if (arrEmp[i].getEmployeeId().equals(idInput)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    this.employeeId = idInput;
                    break;
                } else {
                    System.err.println("Mã nhân viên đã tồn tại!");
                }
            } else {
                System.err.println("Mã nhân viên phải bắt đầu bằng E và có 5 ký tự!");
            }
        }

        while (true) {
            System.out.print("Nhập tên nhân viên (6-30 ký tự): ");
            String nameInput = scanner.nextLine();
            if (nameInput.length() >= 6 && nameInput.length() <= 30) {
                this.employeeName = nameInput;
                break;
            } else {
                System.err.println("Tên phải từ 6 đến 30 ký tự!");
            }
        }

        while (true) {
            System.out.println("Chọn chức vụ: 1.DEV, 2.TESTER, 3.PM, 4.BA");
            System.out.print("Lựa chọn: ");
            try {
                int roleChoice = Integer.parseInt(scanner.nextLine());
                switch (roleChoice) {
                    case 1: this.role = Role.DEV; break;
                    case 2: this.role = Role.TESTER; break;
                    case 3: this.role = Role.PM; break;
                    case 4: this.role = Role.BA; break;
                    default: System.err.println("Lựa chọn không hợp lệ!"); continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số!");
            }
        }

        while (true) {
            System.out.print("Nhập lương (>0): ");
            try {
                double salInput = Double.parseDouble(scanner.nextLine());
                if (salInput > 0) {
                    this.salary = salInput;
                    break;
                } else {
                    System.err.println("Lương phải lớn hơn 0!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lương phải là số!");
            }
        }
    }

    public void displayData() {
        System.out.printf("ID: %-5s | Name: %-20s | Role: %-6s | Salary: %,.0f\n", 
            employeeId, employeeName, role, salary);
    }
}
