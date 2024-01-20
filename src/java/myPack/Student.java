/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myPack;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Student {
    protected int Id;
    protected String name;
    protected boolean gender;
    protected Date DOB;

    public Student() {
    }

    public Student(int Id, String name, boolean gender, Date DOB) {
        this.Id = Id;
        this.name = name;
        this.gender = gender;
        this.DOB = DOB;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }
    
    
    

}
