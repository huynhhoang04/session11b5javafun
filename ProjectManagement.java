package ra.presentation;

import ra.entity.Employee;
import ra.entity.Project;
import ra.entity.Role;
import ra.entity.Status;

import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ProjectManagement {
    private static Employee[] arrEmp = new Employee[100];
    private static int empCount = 0;
    private static Project[] arrProject = new Project[100];
    private static int projCount = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n================ QUẢN LÝ DỰ ÁN ================");
            System.out.println("1. Quản lý nhân viên");
            System.out.println("2. Quản lý dự án");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: menuEmployee(); break;
                case 2: menuProject(); break;
                case 3: System.exit(0);
                default: System.err.println("Lựa chọn không hợp lệ");
            }
        }
    }

    public static void menuEmployee() {
        boolean back = false;
        while (!back) {
            System.out.println("\n----------- QUẢN LÝ NHÂN VIÊN -----------");
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Hiển thị danh sách nhân viên");
            System.out.println("3. Cập nhật thông tin nhân viên");
            System.out.println("4. Xóa nhân viên");
            System.out.println("5. Tìm kiếm nhân viên theo tên");
            System.out.println("6. Sắp xếp nhân viên theo lương giảm dần");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn: ");
            int choice = 0;
            try {
                 choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) { choice = 0; }

            switch (choice) {
                case 1:
                    if (empCount < 100) {
                        Employee emp = new Employee();
                        emp.inputData(scanner, arrEmp, empCount);
                        arrEmp[empCount++] = emp;
                        System.out.println("Thêm thành công!");
                    }
                    break;
                case 2:
                    if (empCount == 0) System.out.println("Danh sách trống!");
                    for (int i = 0; i < empCount; i++) arrEmp[i].displayData();
                    break;
                case 3:
                    System.out.print("Nhập mã nhân viên cần sửa: ");
                    String editId = scanner.nextLine();
                    int editIndex = -1;
                    for (int i = 0; i < empCount; i++) {
                        if (arrEmp[i].getEmployeeId().equals(editId)) {
                            editIndex = i;
                            break;
                        }
                    }
                    if (editIndex != -1) {
                        System.out.println("Nhập tên mới:"); arrEmp[editIndex].setEmployeeName(scanner.nextLine());
                        System.out.println("Nhập lương mới:"); arrEmp[editIndex].setSalary(Double.parseDouble(scanner.nextLine()));
                        System.out.println("Cập nhật thành công!");
                    } else System.err.println("Không tìm thấy!");
                    break;
                case 4:
                    System.out.print("Nhập mã nhân viên cần xóa: ");
                    String delId = scanner.nextLine();
                    int delIndex = -1;
                    for (int i = 0; i < empCount; i++) {
                        if (arrEmp[i].getEmployeeId().equals(delId)) {
                            delIndex = i;
                            break;
                        }
                    }
                    if (delIndex != -1) {
                        for (int i = delIndex; i < empCount - 1; i++) arrEmp[i] = arrEmp[i+1];
                        arrEmp[empCount - 1] = null;
                        empCount--;
                        System.out.println("Xóa thành công!");
                    } else System.err.println("Không tìm thấy!");
                    break;
                case 5:
                    System.out.print("Nhập tên tìm kiếm: ");
                    String searchName = scanner.nextLine();
                    for (int i = 0; i < empCount; i++) {
                        if (arrEmp[i].getEmployeeName().toLowerCase().contains(searchName.toLowerCase())) {
                            arrEmp[i].displayData();
                        }
                    }
                    break;
                case 6:
                    for (int i = 0; i < empCount - 1; i++) {
                        for (int j = i + 1; j < empCount; j++) {
                            if (arrEmp[i].getSalary() < arrEmp[j].getSalary()) {
                                Employee temp = arrEmp[i];
                                arrEmp[i] = arrEmp[j];
                                arrEmp[j] = temp;
                            }
                        }
                    }
                    System.out.println("Đã sắp xếp!");
                    break;
                case 7: back = true; break;
                default: System.err.println("Sai lựa chọn!");
            }
        }
    }

    public static void menuProject() {
        boolean back = false;
        while (!back) {
            System.out.println("\n----------- QUẢN LÝ DỰ ÁN -----------");
            System.out.println("1. Thêm dự án");
            System.out.println("2. Hiển thị danh sách dự án");
            System.out.println("3. Cập nhật thông tin dự án");
            System.out.println("4. Xóa dự án (chưa có nhân viên)");
            System.out.println("5. Thêm nhân viên vào dự án");
            System.out.println("6. Tìm dự án theo tên");
            System.out.println("7. Thống kê số lượng nhân viên theo vai trò");
            System.out.println("8. Tìm dự án đang chạy và gần kết thúc nhất");
            System.out.println("9. Thoát");
            System.out.print("Lựa chọn: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) { choice = 0; }

            switch (choice) {
                case 1:
                    if (projCount < 100) {
                        Project proj = new Project();
                        proj.inputData(scanner, arrProject, projCount, arrEmp, empCount);
                        arrProject[projCount++] = proj;
                        System.out.println("Thêm dự án thành công!");
                    }
                    break;
                case 2:
                    if (projCount == 0) System.out.println("Danh sách trống!");
                    for (int i = 0; i < projCount; i++) arrProject[i].displayData();
                    break;
                case 3:
                    System.out.print("Nhập mã dự án cần sửa: ");
                    String editId = scanner.nextLine();
                    Project editProj = null;
                    for(int i=0; i<projCount; i++) {
                        if(arrProject[i].getProjectId().equals(editId)) {
                            editProj = arrProject[i];
                            break;
                        }
                    }
                    if(editProj != null) {
                        System.out.print("Nhập tên mới: "); editProj.setProjectName(scanner.nextLine());
                        System.out.println("Cập nhật thành công!");
                    } else System.err.println("Không tìm thấy!");
                    break;
                case 4:
                    System.out.print("Nhập mã dự án xóa: ");
                    String delId = scanner.nextLine();
                    int delIdx = -1;
                    for(int i=0; i<projCount; i++) {
                        if(arrProject[i].getProjectId().equals(delId)) {
                            if(arrProject[i].getEmpCount() == 0) delIdx = i;
                            else System.err.println("Dự án có nhân viên, không thể xóa!");
                            break;
                        }
                    }
                    if(delIdx != -1) {
                        for(int i=delIdx; i<projCount-1; i++) arrProject[i] = arrProject[i+1];
                        arrProject[projCount-1] = null;
                        projCount--;
                        System.out.println("Xóa thành công!");
                    }
                    break;
                case 5:
                    System.out.print("Nhập mã dự án: ");
                    String pId = scanner.nextLine();
                    Project foundP = null;
                    for(int i=0; i<projCount; i++) {
                        if(arrProject[i].getProjectId().equals(pId)) { foundP = arrProject[i]; break; }
                    }
                    if(foundP != null) {
                        System.out.print("Nhập mã nhân viên thêm: ");
                        String eId = scanner.nextLine();
                        Employee foundE = null;
                        for(int i=0; i<empCount; i++) {
                            if(arrEmp[i].getEmployeeId().equals(eId)) { foundE = arrEmp[i]; break; }
                        }
                        if(foundE != null) {
                            foundP.addEmployee(foundE);
                            System.out.println("Thêm nhân viên vào dự án thành công!");
                        } else System.err.println("Không tìm thấy nhân viên!");
                    } else System.err.println("Không tìm thấy dự án!");
                    break;
                case 6:
                    System.out.print("Nhập tên dự án: ");
                    String sName = scanner.nextLine();
                    for(int i=0; i<projCount; i++) {
                        if(arrProject[i].getProjectName().toLowerCase().contains(sName.toLowerCase()))
                            arrProject[i].displayData();
                    }
                    break;
                case 7:
                    System.out.print("Nhập mã dự án: ");
                    String statId = scanner.nextLine();
                    Project statP = null;
                    for(int i=0; i<projCount; i++) {
                        if(arrProject[i].getProjectId().equals(statId)) { statP = arrProject[i]; break; }
                    }
                    if(statP != null) {
                        int dev=0, test=0, pm=0, ba=0;
                        for(Employee e : statP.getEmployees()) {
                            if(e == null) break;
                            if(e.getRole() == Role.DEV) dev++;
                            else if(e.getRole() == Role.TESTER) test++;
                            else if(e.getRole() == Role.PM) pm++;
                            else if(e.getRole() == Role.BA) ba++;
                        }
                        System.out.printf("DEV: %d, TESTER: %d, PM: %d, BA: %d\n", dev, test, pm, ba);
                    } else System.err.println("Không tìm thấy!");
                    break;
                case 8:
                    Project target = null;
                    long minDays = Long.MAX_VALUE;
                    java.time.LocalDate now = java.time.LocalDate.now();
                    for(int i=0; i<projCount; i++) {
                        if(arrProject[i].getStatus() == Status.RUNNING) {
                            long days = ChronoUnit.DAYS.between(now, arrProject[i].getEndDate());
                            if(days >= 0 && days < minDays) {
                                minDays = days;
                                target = arrProject[i];
                            }
                        }
                    }
                    if(target != null) {
                        System.out.println("Dự án sắp kết thúc: ");
                        target.displayData();
                    } else System.out.println("Không có dự án nào thỏa mãn!");
                    break;
                case 9: back = true; break;
                default: System.err.println("Sai lựa chọn!");
            }
        }
    }
}
