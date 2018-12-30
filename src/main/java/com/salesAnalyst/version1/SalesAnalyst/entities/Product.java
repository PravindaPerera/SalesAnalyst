package com.salesAnalyst.version1.SalesAnalyst.entities;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "productId")
    private int productId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "contactNumber")
    private String contactNumber;


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
