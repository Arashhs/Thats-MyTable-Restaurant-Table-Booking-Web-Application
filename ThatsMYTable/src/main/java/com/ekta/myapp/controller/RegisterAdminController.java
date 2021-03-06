package com.ekta.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ekta.myapp.dao.RestAdminDAO;
import com.ekta.myapp.dao.UserDAO;
import com.ekta.myapp.exception.ProjException;
import com.ekta.myapp.pojo.Person;
import com.ekta.myapp.pojo.RestaurantAdmin;
import com.ekta.myapp.pojo.User;

/**
 * This class used for controlling the register process
 * of Admin (chief) of the restaurant
 * @version 1.0
 */
@Controller
@RequestMapping("registerAdmin.htm")
public class RegisterAdminController {

	/*
	 * This method get admin information from View
	 * And set them into Model
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("regAdmin") RestaurantAdmin restAdmin) throws Exception {
//		validator.validate(user, result);
//		if (result.hasErrors()) {
//			return "addUserForm";
//		}

		/*
		 * Set Admin specification into dataBase
		 */
		try {
			System.out.print("test");
			RestAdminDAO restDAO = new RestAdminDAO();
			System.out.print("test1");
			
			restDAO.create(restAdmin.getFirstName(),restAdmin.getLastName(),restAdmin.getCity(),restAdmin.getUsername(),restAdmin.getPassword(),restAdmin.getRoleType());
			
			// DAO.close();
		} catch (ProjException e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "home";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("regUser") RestaurantAdmin restAdmin) {

		return "home";
	}

}
