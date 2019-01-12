package com.salesAnalyst.version1.SalesAnalyst.entities;



import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sales {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "year")
    private int year;
    @Basic
    @Column(name = "month")
    private int month;
    @Basic
    @Column(name = "sales_value")
    private double slaeValue;

    @Basic
    @Column(name="product")
    private int Product;

    @Basic
    @Column(name="customer")
    private int customer;





    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setSlaeValue(int slaeValue) {
        this.slaeValue = slaeValue;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getProduct() {
        return Product;
    }

    public void setProduct(int product) {
        Product = product;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getSlaeValue() {
        return slaeValue;
    }

    public void setSlaeValue(Integer slaeValue) {
        this.slaeValue = slaeValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
