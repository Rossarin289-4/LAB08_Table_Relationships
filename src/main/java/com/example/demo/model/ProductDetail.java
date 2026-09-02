package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_details")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String warranty;
    private Double weight;
    private String dismension;
    private String manufacturerdCountry;

    @OneToOne(mappedBy = "detail")
    private Product product;

    public ProductDetail() {}
    public ProductDetail(Long id, String description, String warranty,
                            Double weight, String dismension,
                            String manufacturerdCountry, Product product) {
        this.id = id;
        this.description = description;
        this.warranty = warranty;
        this.weight = weight;
        this.dismension = dismension;
        this.manufacturerdCountry = manufacturerdCountry;
        this.product = product;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getWarranty() {
        return warranty;
    }
    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDismension() {
        return dismension;
    }
    public void setDismension(String dismension) {
        this.dismension = dismension;
    }

    public String getManufacturerdCountry() {
        return manufacturerdCountry;
    }
    public void setManufacturerdCountry(String manufacturerdCountry) {
        this.manufacturerdCountry = manufacturerdCountry;
    }
}
