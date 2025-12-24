package ra.entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Project {
    private String projectId;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Employee[] employees;
    private int empCount;
    private Status status;

    public Project() {
        this.employees = new Employee[100]; 
        this.empCount = 0;
    }

    public Project(String projectId, String projectName, LocalDate startDate, LocalDate endDate, Status status) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.employees = new Employee[100];
        this.empCount = 0;
    }

    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public Employee[] getEmployees() { return employees; }
    public int getEmpCount() { return empCount; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public void addEmployee(Employee emp) {
        if (empCount < 100) {
            employees[empCount++] = emp;
        } else {
            System.err.println("Dự án đã đầy nhân viên!");
        }
    }

    public void inputData(Scanner scanner, Project[] arrProject, int index, Employee[] arrEmp, int empIndex) {
        while (true) {
            System.out.print("Nhập mã dự án (P0001): ");
            String idInput = scanner.nextLine();
            if (idInput.matches("^P.{4}$")) {
                boolean isExist = false;
                for (int i = 0; i < index; i++) {
                    if (arrProject[i].getProjectId().equals(idInput)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    this.projectId = idInput;
                    break;
                } else {
                    System.err.println("Mã dự án đã tồn tại!");
                }
            } else {
                System.err.println("Mã dự án phải bắt đầu bằng P và có 5 ký tự!");
            }
        }

        while (true) {
            System.out.print("Nhập tên dự án (10-50 ký tự): ");
            String nameInput = scanner.nextLine();
            if (nameInput.length() >= 10 && nameInput.length() <= 50) {
                boolean isExist = false;
                for (int i = 0; i < index; i++) {
                    if (arrProject[i].getProjectName().equals(nameInput)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    this.projectName = nameInput;
                    break;
                } else {
                    System.err.println("Tên dự án đã tồn tại!");
                }
            } else {
                System.err.println("Tên phải từ 10 đến 50 ký tự!");
            }
        }

        while (true) {
            System.out.print("Nhập ngày bắt đầu (yyyy-MM-dd): ");
            try {
                this.startDate = LocalDate.parse(scanner.nextLine());
                break;
            } catch (DateTimeParseException e) {
                System.err.println("Định dạng ngày không hợp lệ!");
            }
        }

        while (true) {
            System.out.print("Nhập ngày kết thúc (yyyy-MM-dd): ");
            try {
                LocalDate end = LocalDate.parse(scanner.nextLine());
                if (end.isAfter(this.startDate) || end.isEqual(this.startDate)) {
                    this.endDate = end;
                    break;
                } else {
                    System.err.println("Ngày kết thúc phải sau hoặc bằng ngày bắt đầu!");
                }
            } catch (DateTimeParseException e) {
                System.err.println("Định dạng ngày không hợp lệ!");
            }
        }

        while (true) {
            System.out.println("Chọn trạng thái: 1.PLANNING, 2.RUNNING, 3.FINISHED");
            System.out.print("Lựa chọn: ");
            try {
                int stChoice = Integer.parseInt(scanner.nextLine());
                switch (stChoice) {
                    case 1: this.status = Status.PLANNING; break;
                    case 2: this.status = Status.RUNNING; break;
                    case 3: this.status = Status.FINISHED; break;
                    default: System.err.println("Không hợp lệ!"); continue;
                }
                break;
            } catch (Exception e) {
                System.err.println("Nhập số!");
            }
        }
    }

    public void displayData() {
        System.out.printf("PID: %-5s | Name: %-20s | Start: %s | End: %s | Status: %s | Employees: %d\n", 
            projectId, projectName, startDate, endDate, status, empCount);
        if (empCount > 0) {
            System.out.println("   -> Danh sách nhân viên trong dự án:");
            for (int i = 0; i < empCount; i++) {
                System.out.print("      - ");
                employees[i].displayData();
            }
        }
    }
}
