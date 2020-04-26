package com.ekta.myapp.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "food_tbl")
public class Food {

    //foodID is primary key which is generated automatically
    @Id
    @GeneratedValue
    @Column(name="foodID",unique=true,nullable=false)
    private int foodID;

    //Food name for the food
    @Column(name="foodName")
    private String foodName;

    //price for food
    @Column(name="foodPrice")
    private float foodPrice;

    //Availability for food
    @Column(name="availability")
    private int availablity;

    //Foreign key to restaurant
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="restID")
    private Restaurant restaurant;

    public Food() {
    }

    /* *********** Getter and setters START *********** */

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public float getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(float foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getAvailablity() {
        return availablity;
    }

    public void setAvailablity(int availablity) {
        this.availablity = availablity;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /* *********** Getter and setters END *********** */
}
