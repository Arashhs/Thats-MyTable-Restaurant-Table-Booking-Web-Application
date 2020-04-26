package com.ekta.myapp.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Table entity for restaurant
@Entity
@Table(name="restaurant_table_tbl")

public class RestaurantTable {

	//tableID is primary key which is generated automatically
	@Id
	@GeneratedValue
	@Column(name="tableID",unique=true,nullable=false)
	private int tableID;

	//Table number for the restaurant
	@Column(name="tableNo")
	private int tableNo;

	//Table status (Can be "vacant" or "reserved"
	@Column(name="tableStatus")
	private String tableStatus;

	//Foreign key to restaurant table
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="restID")
	private Restaurant restaurant;


	
	public RestaurantTable(){
		
	}

	/* ******** Getter and Setter functions START ******** */

	public int getTableNo() {
		return tableNo;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public String getTableStatus() {
		return tableStatus;
	}

	public void setTableStatus(String tableStatus) {
		this.tableStatus = tableStatus;
	}

	/* ******** Getter and Setter functions END ******** */

	
}
