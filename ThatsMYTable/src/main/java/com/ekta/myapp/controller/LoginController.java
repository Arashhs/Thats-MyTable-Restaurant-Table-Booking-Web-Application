package com.ekta.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ekta.myapp.dao.PersonDAO;
import com.ekta.myapp.exception.ProjException;
import com.ekta.myapp.pojo.Person;

/**
 * This class control the Login of the
 * User and restaurant manager and set
 * appropriate View due to the type of user
 * @version 1.0
 */
@Controller
@RequestMapping("/login.htm")
public class LoginController {
//	@Autowired
//	@Qualifier("userValidator")
//	UserValidator validator;
//
//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		binder.setValidator(validator);
//	}

	/*
	 * This method get person username and password and
	 * After checking validity of password
	 * set appropriate View due to type of the person
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("logPerson") Person person,HttpServletRequest request) throws Exception {
//		validator.validate(user, result);
//		if (result.hasErrors()) {
//			return "addUserForm";
//		}


		try {
			System.out.print("test");
			PersonDAO personDAO = new PersonDAO();
			System.out.print("test1");

			/*
			 * Validation username and password
			 */
			HttpSession session = request.getSession();
			Person loggedPerson = personDAO.get(person.getUsername(),person.getPassword());
			session.setAttribute("person", loggedPerson);

			if(loggedPerson!=null){

		 	/*
		 	 * Set related view due to the type of the person
		 	 */
			if(loggedPerson.getRoleType().equals("user")){
				return "userHome";
			}
			
			else {
				return "restAdminHome";
			}
			}
			else{
				
				System.out.println("Username does not exist");
				return"home";
				
			}

		
		} catch (ProjException e) {
			System.out.println("Exception: " + e.getMessage());
			
		
		}
   return "home";

	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("logPerson") Person person) {

		return "userHome";
	}

}
