package com.company;

public abstract class Employee {
    private static Integer oldId = 0;
    private int id;
    private String name;
    private int workingHours;


    public Employee(String name, int workingHours) {
        this.id = oldId++;
        this.name = name;
        this.workingHours = workingHours;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public abstract int calculateSalary();
}
