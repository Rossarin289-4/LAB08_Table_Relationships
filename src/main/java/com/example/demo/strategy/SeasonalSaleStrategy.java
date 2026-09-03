package com.example.demo.strategy;

public class SeasonalSaleStrategy implements DiscountStrategy{
    @Override
    public double calculateDiscount(double price){
        return price * 0.8; // ลด 20%
    }
}
