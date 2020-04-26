package com.ekta.myapp.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//Restaurant entity and respective table for ODB
@Entity
@Table(name="restaurant_tbl")
public class Restaurant {

	public Restaurant(){
		
	}

	//Restaurant table's ID attribute
	@Id
	@GeneratedValue
	@Column(name="restID")
	private int restID;

	//Restaurant name attribute
	@Column(name="restName")
	private String restName;

	//Restaurant city attribute
	@Column(name="restCity")
	private String restCity;

	//Restaurant zip-code attribute
	@Column(name="zipCode")
	private int zipCode;

	//Restaurant admin
	@OneToOne(fetch=FetchType.EAGER) //Each restaurant can have only one admin, so the relation is one to one
	@JoinColumn(name="restAdminID") //Foreign key to restAdmin table
	private RestaurantAdmin restAdmin;
	
	//Restaurant tables which is a collection
	@OneToMany(fetch=FetchType.EAGER, mappedBy="restaurant",cascade=CascadeType.ALL)
	List <RestaurantTable> restTable = new ArrayList<RestaurantTable>();

	//Restaurant Food which is a collection
	@OneToMany(mappedBy="restaurant",cascade=CascadeType.ALL)
	List <Food> restFood = new ArrayList<Food>();

	//Restaurant Reservations which is a collection
	@OneToMany(mappedBy="restaurant",cascade=CascadeType.ALL)
	List <Reservation> restReservation = new ArrayList<Reservation>();





	/* ******** Getter and Setter functions START ******** */

	public List<RestaurantTable> getRestTable() {
		return restTable;
	}
	public void setRestTable(List<RestaurantTable> restTable) {
		this.restTable = restTable;
	}
	public RestaurantAdmin getRestAdmin() {
		return restAdmin;
	}
	public void setRestAdmin(RestaurantAdmin restAdmin) {
		this.restAdmin = restAdmin;
	}
	public int getRestID() {
		return restID;
	}
	public void setRestID(int restID) {
		this.restID = restID;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public String getRestCity() {
		return restCity;
	}
	public void setRestCity(String restCity) {
		this.restCity = restCity;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public List<Food> getRestFood() {
		return restFood;
	}

	public void setRestFood(List<Food> restFood) {
		this.restFood = restFood;
	}

	public List<Reservation> getRestReservation() {
		return restReservation;
	}

	public void setRestReservation(List<Reservation> restReservation) {
		this.restReservation = restReservation;
	}
	/* ******** Getter and Setter functions END ******** */
	
	
}
