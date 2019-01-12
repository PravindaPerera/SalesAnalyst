package com.salesAnalyst.version1.SalesAnalyst.entities;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "customerId")
    private int customerId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "contactNumber")
    private String contactNumber;


    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "category")
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
