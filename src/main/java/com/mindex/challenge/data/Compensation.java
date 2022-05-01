package com.mindex.challenge.data;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Compensation{

    @Id
    private Employee employee;
    private int salary;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate effectiveDate;

    public Employee getEmployee(){
        return employee;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public int getSalary(){
        return salary;
    }

    public void setSalary(int salary){
        this.salary = salary;
    }

    public LocalDate getEffectiveDate(){
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate){
        this.effectiveDate = effectiveDate;
    }

}
