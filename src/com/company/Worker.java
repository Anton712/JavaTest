package com.company;

public class Worker extends Employee {
    public Worker(String name, int workingHours) {
        super(name, workingHours);
    }

    @Override
    public int calculateSalary() {
        return super.getWorkingHours() * 10;
    }
}
