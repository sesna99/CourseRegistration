package com.example.admin.courseregistration.model;

/**
 * Created by trycatch on 2017. 3. 10..
 */

public class SubjectModel {
    String code;
    String divideClass;
    String name;
    String professor;
    String grade;
    String credit;
    String division;
    String amount;
    String note;

    public SubjectModel(String code, String divideClass, String name, String professor, String grade, String credit, String division, String amount, String note) {
        this.code = code;
        this.divideClass = divideClass;
        this.name = name;
        this.professor = professor;
        this.grade = grade;
        this.credit = credit;
        this.division = division;
        this.amount = amount;
        this.note = note;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDivideClass() {
        return divideClass;
    }

    public void setDivideClass(String divideClass) {
        this.divideClass = divideClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
