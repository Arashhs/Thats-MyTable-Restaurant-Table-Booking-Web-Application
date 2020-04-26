package com.ekta.myapp.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "reservation_tbl")
public class Reservation {

    //resID is primary key which is generated automatically
    @Id
    @GeneratedValue
    @Column(name="reserveID",unique=true,nullable=false)
    private int reserveID;

    //Foreign key to user
    @ManyToOne
    @JoinColumn(name="personID")
    private User user;

    //Foreign key to restaurant
    @ManyToOne
    @JoinColumn(name="restID")
    private Restaurant restaurant;

    //Foreign key to table
    @ManyToOne
    @JoinColumn(name="tableID")
    private RestaurantTable table;

    //forein key to food
    @ManyToMany
    List <Food> foodList = new ArrayList<Food>();

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "startTime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "endTime", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    public Reservation() {
    }

    /* *********** Getter and setters START *********** */

    public int getReserveID() {
        return reserveID;
    }

    public void setReserveID(int reserveID) {
        this.reserveID = reserveID;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public RestaurantTable getTable() {
        return table;
    }

    public void setTable(RestaurantTable table) {
        this.table = table;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /* *********** Getter and setters END *********** */
}
