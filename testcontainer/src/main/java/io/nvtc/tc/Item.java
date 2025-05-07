package io.nvtc.tc;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    
    private Integer quantity;
    
    // Default constructor
    public Item() {
    }
    
    // Constructor with params
    public Item(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    
    // Getters and setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    // Increase quantity method
    public void increaseQuantity(Integer additionalQuantity) {
        this.quantity += additionalQuantity;
    }
}