package com.ekta.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import com.ekta.myapp.dao.FoodDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ekta.myapp.dao.RestaurantDAO;

import com.ekta.myapp.dao.TableDAO;
import com.ekta.myapp.exception.ProjException;
import com.ekta.myapp.pojo.Person;
import com.ekta.myapp.pojo.Restaurant;
import com.ekta.myapp.pojo.RestaurantTable;
import com.ekta.myapp.pojo.Food;

/**
 * This class control the process of adding a table to determined
 * Restaurant of admin and have three part
 * first part get information of new table and add the table to restaurant
 * second part occupy tables of a restaurant for user
 * third part vacant a table of a restaurant
 * @version 1.0
 */
@Controller
public class AddTableController {

	/*
	 * This method get number of the new table and name of the restaurant
	 * after that check that whether this table was added to the restaurant later or no
	 * And set appropriate View
	 */
	@RequestMapping(value="addTable.htm",method = RequestMethod.POST)
	protected ModelAndView doSubmitAction(@ModelAttribute("addingTable") RestaurantTable restTable,HttpServletRequest request) throws Exception {
//		validator.validate(user, result);
//		if (result.hasErrors()) {
//			return "addUserForm";
//		}
        ModelAndView mv = new ModelAndView();
		try {

			/*
			 * Get name of the restaurant and the number of the table from request
			 */
			String restName=request.getParameter("restName");
			int tableNumber = Integer.parseInt(request.getParameter("tableNo"));

			RestaurantDAO restDAO=new RestaurantDAO();
			Restaurant rest=restDAO.fetchMyRestaurant(restName);

			//HttpSession session = request.getSession();
			//System.out.print("test");
			/*
			 * Check that whether the table is added to system later or not
			 */
			TableDAO tableDAO = new TableDAO();
			RestaurantTable restTab = tableDAO.fetchMyRestaurantTable(tableNumber);
			//System.out.print("test1");
			//Restaurant rest= (Restaurant)session.getAttribute("restSessionObj");
	        //Restaurant rest = (Restaurant) session.getAttribute("restaurant");

			if(restTab == null){

			/*
			 * Add the table to restaurant tables
			 */
			RestaurantTable avail=tableDAO.create(restTable.getTableNo(),rest);
			System.out.println("Table avialability is"+avail);
			mv.addObject("tableAdded",avail);
			mv.setViewName("successRestaurantAdded");
			return mv;

			// DAO.close();
		}

		else{
		    /*
		    * Add the table to restaurant tables
		    */
			mv.setViewName("successRestaurantAdded");
			System.out.println("You already have this table added to the system");
			return mv;
		}
		}

		catch (ProjException e) {
			System.out.println("Exception: " + e.getMessage());
			mv.setViewName("successRestaurantAdded");
			return mv;
		}

	}

	/*
	 * This method give request from server to occupy one restaurant table for user
	 */
	@RequestMapping(value="/deleteTable.htm",method = RequestMethod.POST)
	protected String occupiedTable(@ModelAttribute("deletingTable") RestaurantTable restTable,HttpServletRequest request) throws Exception {

			/*
			 * update the status of a table of the given restaurant to occupied
			 */
			String restName=request.getParameter("restName");
			RestaurantDAO restDAO=new RestaurantDAO();
			Restaurant rest=restDAO.fetchMyRestaurant(restName);
			System.out.print("ghussa");
			TableDAO tableDAO = new TableDAO();
			int rowsUpdated=tableDAO.update(restTable.getTableNo(), restTable.getTableStatus(), rest);
			System.out.print(rowsUpdated);

//			RestaurantTable avail=tableDAO.delete(restTable);
//			System.out.println("Table avialability is"+avail);

			// DAO.close();

		return "successRestaurantAdded";
	}

	/*
	 * This method give request from server to vacant one restaurant table
	 */
	@RequestMapping(value="/updateVacancy.htm",method = RequestMethod.POST)
	protected String vacantTable(@ModelAttribute("updatingTableVacancy") RestaurantTable restTable,HttpServletRequest request) throws Exception {

			/*
			 * update the status of a table of the given restaurant to vacant
			 */
			String restName=request.getParameter("restName");
			RestaurantDAO restDAO=new RestaurantDAO();
			Restaurant rest=restDAO.fetchMyRestaurant(restName);
			System.out.print("ghussa");
			TableDAO tableDAO = new TableDAO();
			int rowsUpdated=tableDAO.updateVacancy(restTable.getTableNo(), restTable.getTableStatus(), rest);
			System.out.print(rowsUpdated);


//			RestaurantTable avail=tableDAO.delete(restTable);
//			System.out.println("Table avialability is"+avail);

			// DAO.close();

		return "successRestaurantAdded";
	}


	/*
	 * This method add the food with given name and price and number to restaurant menu
	 */
	@RequestMapping(value="addFood.htm",method = RequestMethod.POST)
	protected ModelAndView updateMenu(@ModelAttribute("addingFood") RestaurantTable restTable,HttpServletRequest request) throws Exception {
//		validator.validate(user, result);
//		if (result.hasErrors()) {
//			return "addUserForm";
//		}
		ModelAndView mv = new ModelAndView();
		try {

//			String restName=request.getParameter("restName");
//			int tableNumber = Integer.parseInt(request.getParameter("tableNo"));

			String restName=request.getParameter("restName");
			String foodName = request.getParameter("foodName");

			RestaurantDAO restDAO=new RestaurantDAO();
			Restaurant rest=restDAO.fetchMyRestaurant(restName);

			//HttpSession session = request.getSession();
			//System.out.print("test");

//			TableDAO tableDAO = new TableDAO();
//			RestaurantTable restTab = tableDAO.fetchMyRestaurantTable(tableNumber);

			FoodDAO foodDAO = new FoodDAO();
			Food restFood = foodDAO.fetchMyRestaurantFood(foodName);

			//System.out.print("test1");
			//Restaurant rest= (Restaurant)session.getAttribute("restSessionObj");
			//Restaurant rest = (Restaurant) session.getAttribute("restaurant");

			if(restFood == null){


//				RestaurantTable avail=tableDAO.create(restTable.getTableNo(),rest);
//				System.out.println("Table avialability is"+avail);
//				mv.addObject("tableAdded",avail);
//				mv.setViewName("successRestaurantAdded");
//				return mv;

				Food food = foodDAO.create(request.getParameter("foodName"),Float.valueOf(request.getParameter("FoodPrice")),
						Integer.valueOf(request.getParameter("numberOfFood")),rest);
				mv.addObject("foodAdded",food);
				mv.setViewName("successRestaurantAdded");
				return mv;

				// DAO.close();
			}

			else{

//				mv.setViewName("successRestaurantAdded");
//				System.out.println("You already have this table added to the system");
//				return mv;

				mv.setViewName("successRestaurantAdded");
				return mv;

			}
		}

		catch (ProjException e) {
			System.out.println("Exception: " + e.getMessage());
			mv.setViewName("successRestaurantAdded");
			return mv;
		}

	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("tableAdd") Person user) {

		return "successRestaurantAdded";
	}

}
