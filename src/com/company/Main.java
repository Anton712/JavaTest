package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // you don't have mto specify type on the right side of assignment, it is automatically assumed by JVM
    static List<Employee> employeeList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello user!");
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Please enter command: ");
            String command = scanner.nextLine();

            if (command.equals("add m") || command.equals("add w")) {
                System.out.println("Employee entry started.");
                employeeInput(scanner, command);

            } else if (command.equals("show")) {
                showEmployees();
                // you don't need '?' in regex, it will match previous character 0 or more times, you can enter "remov 1" and it will pass
            } else if (command.matches("^remove\\s\\d+")) {
                int id = Integer.parseInt(command.split(" ")[1]);
                removeEmployee(id);
                // you don't need '?' in regex, it will match previous character 0 or more times, you can enter "searc worker" and it will pass
            } else if (command.matches("^search\\s[A-Za-z]+$")) {
                String name = command.split(" ")[1];
                searchEmployee(name);
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("Unknown command, try again.");
            }
        }
        while (true);
    }

    public static void employeeInput(Scanner scanner, String command) {
        String name;
        do {
            System.out.println("\nPlease enter employee name: ");
            name = scanner.nextLine();
            if (name.equals("end")) {
                break;
            } else {
                if (name.matches(".*\\d.*")) {
                    System.out.println("Name should not contain numbers.");
                } else {
                    String workingHours;
                    do {
                        System.out.println("Please enter employee workingHours: ");
                        workingHours = scanner.nextLine();
                        // && instead of || because every input of workingHours will be valid because it is greater than 0
                        if (workingHours.chars().allMatch(Character::isDigit) && (Integer.parseInt(workingHours) > 0 && Integer.parseInt(workingHours) <= 40)) {
                            break;
                        } else {
                            System.out.println("workingHours must be a number between 0 and 40.");
                        }
                    } while (true);
                    if (command.split(" ")[1].equals("m")) {
                        employeeList.add(new Manager(name, Integer.parseInt(workingHours)));
                    } else {
                        employeeList.add(new Worker(name, Integer.parseInt(workingHours)));
                    }
                }
            }
        } while (true);
    }

    public static void showEmployees() {
        System.out.printf("%-5s %-12s %-14s %-7s\n", "ID", "Name", "Hours_worked", "Salary");
        // you forgot to sort the employees
//         for (Employee employee : employeeList) {
//             System.out.printf("%-5s %-12s %-14s %-7s\n", employee.getId(), employee.getName(), employee.getWorkingHours(), employee.calculateSalary());
//         }
        employeeList.stream().sorted(Comparator.comparing(Employee::getName)).forEach(employee -> System.out.printf("%-5s %-12s %-14s %-7s\n", employee.getId(), employee.getName(), employee.getWorkingHours(), employee.calculateSalary()));
        System.out.println();
    }

    public static void removeEmployee(int id) {
        // use base class type instead of var
        Employee employee = employeeList.stream().filter(empl -> empl.getId() == id).findAny().orElse(null);

        if (employeeList.remove(employee)) {
            System.out.printf("%s has been removed successfully.\n\n", employee.getName());
        } else {
            System.out.println("Id does not exist.\n");
        }
    }


    public static void searchEmployee(String name) {
        // use base class type instead of var
        Employee employee = employeeList.stream().filter(empl -> empl.getName().equals(name)).findAny().orElse(null);

        if (employee != null) {
            System.out.printf("%s worked %d hours this week.\n\n", employee.getName(), employee.getWorkingHours());
        } else {
            System.out.printf("%s does not exist.\n\n", name);
        }
    }
}


