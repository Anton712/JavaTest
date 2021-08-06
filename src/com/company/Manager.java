package com.company;

public class Manager extends Employee {
    public Manager(String name, int workingHours) {
        super(name, workingHours);
    }

    @Override
    public int calculateSalary() {
        return super.getWorkingHours() * 20;
    }
}
