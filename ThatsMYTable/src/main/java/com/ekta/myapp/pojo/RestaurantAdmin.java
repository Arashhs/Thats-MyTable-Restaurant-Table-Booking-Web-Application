package com.ekta.myapp.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/*
	Restaurant Admin entity and respective table
	Primary key for this table is also a foreign key to personID in person_tbl
	This entity has a specialization relation to person class
	That means this table extends person class
 */
@Entity
@Table(name="rest_admin_tbl")
@PrimaryKeyJoinColumn(name="personID")
public class RestaurantAdmin extends Person {

	//restaurant in which this person is admin
	@OneToOne(fetch=FetchType.EAGER, mappedBy="restAdmin", cascade=CascadeType.ALL)
	private Restaurant restaurant;
	

	public RestaurantAdmin(){
		
	}

	/* ******** Getter and Setter functions START ******** */

	public Restaurant getRestaurant() {		return restaurant;	}

	public void setRestaurant(Restaurant restaurant) {		this.restaurant = restaurant;	}

	/* ******** Getter and Setter functions END ******** */


}
