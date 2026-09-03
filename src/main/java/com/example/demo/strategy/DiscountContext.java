package com.example.demo.strategy;

public class DiscountContext {
    private DiscountStrategy strategy;
    
    public DiscountContext(DiscountStrategy strategy){
        this.strategy = strategy;
    }

    public double calculateFinalPrice(double price){
        return strategy.calculateDiscount(price);
    }

    public void setStrategy(DiscountStrategy strategy){
        this.strategy = strategy;
    }
}
