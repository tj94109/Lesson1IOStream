package com.terrance.Lesson1IOStream;
import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable{

    private String name;
    private double salary;
    private LocalDate hireDate;

    public Employee(String name, double salary, LocalDate hireDate) {
        this.name = name;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * @param salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * @return return hire date
     */
    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     * @param hireDate
     */
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return  name + "|" + salary +
                "|" + hireDate + "\n";
    }
}
