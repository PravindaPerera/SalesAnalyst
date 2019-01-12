package com.salesAnalyst.version1.SalesAnalyst.entities;

import javax.persistence.*;

@Entity
@Table(name = "budgeted_cost")
public class BudgetedCost {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "year")
    private int year;
    @Basic
    @Column(name = "month")
    private String month;

    @Basic
    @Column(name = "ammount")
    private Long ammount;

    @Basic
    @Column(name = "budjeted_sales")
    private Long budjeted_sales;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getAmmount() {
        return ammount;
    }

    public void setAmmount(Long ammount) {
        this.ammount = ammount;
    }
}
