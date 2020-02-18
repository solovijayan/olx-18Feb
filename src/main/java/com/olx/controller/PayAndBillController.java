package com.olx.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.olx.dao.CartDAO;
import com.olx.dao.DAO;
import com.olx.dao.ItemDAO;
import com.olx.dao.UserDAO;
import com.olx.exception.ItemException;
import com.olx.model.Cart;
import com.olx.model.Item;
import com.olx.model.Order;

@Controller
@RequestMapping("/payment/*")
public class PayAndBillController extends DAO{
	
	@Autowired
	@Qualifier("itemDao")
	ItemDAO itemDao;
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("cartDao")
	CartDAO cartDao;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value = {"/payment/credit", "/payment/debit", "/payment/upi" }, method = RequestMethod.GET)
	public ModelAndView buyItem(@RequestParam("item_id") Integer item_id) {
		
		try {
			System.out.println("Item is is - "+item_id);
			Item buyItem = itemDao.getItem(item_id);
			ModelAndView mdlView = new ModelAndView("billing");
			mdlView.addObject("buyItem", buyItem);
			return mdlView;
			
		} catch (ItemException e) {
			e.printStackTrace();
			return new ModelAndView("error", "errorMessage", "Error while Billing");
		}
	}
	
	@RequestMapping(value = "/payment/confirm", method = RequestMethod.GET)
	public ModelAndView confirmBuy(@RequestParam("item_id") Integer item_id) {
		
		try {
			Item buyItem = itemDao.getItem(item_id);
			ModelAndView mdlView = new ModelAndView("thanks");
			mdlView.addObject("buyItem", buyItem);
			return mdlView;
			
		} catch (ItemException e) {
			e.printStackTrace();
			return new ModelAndView("error", "errorMessage", "Error while Billing");
		}
	}
	
}
