package com.olx.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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
import com.olx.model.User;

@Controller
@RequestMapping("/cart/*")
public class CartController extends DAO{
	
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
	
	/*@RequestMapping(value = "/cart/view", method = RequestMethod.GET)
	public ModelAndView addCategories(HttpServletRequest request) throws Exception {

		try {			
			List<Item> items = itemDao.list();
			return new ModelAndView("buyer-item-list", "items", items);
			
		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "Cart items cannot be viewed");
		}
	}*/
	
	@RequestMapping(value = "/home.jsp", method = RequestMethod.GET)
	public ModelAndView goHome(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User)session.getAttribute("user");
		ModelAndView mdlView = new ModelAndView("home");
		mdlView.addObject("user", user);
		
		return mdlView;
	}
	
	
	@RequestMapping(value = "/cart/view", method = RequestMethod.GET)
	public ModelAndView addCategories(@RequestParam("id") String buyerId) throws Exception {

		List<Cart> items = cartDao.getBuyerCart(buyerId);
		return new ModelAndView("buyer-item-list", "items", items);
	}
	
	@RequestMapping(value = "/cart/checkOut", method = RequestMethod.GET)
	public ModelAndView checkOutItem(@RequestParam("item_id") Integer item_id) {
		try {			
			Order order = new Order();
			
			Item buyItem = itemDao.getItem(item_id);
			
			order.setItem(buyItem);
			ModelAndView mdlView = new ModelAndView("checkout");
			mdlView.addObject("buyItem", buyItem);
			
			return mdlView;
		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while checkout");
		}
	}
	
	@RequestMapping(value = "/cart/delete", method = RequestMethod.GET)
	public ModelAndView deleteCartItem(@RequestParam("item_id") Integer item_id) {
		try {			
			Item buyItem = itemDao.getItem(item_id);
			itemDao.setItemStatus(item_id, "No");
			
			//itemDao.delete(buyItem);
			
			ModelAndView mdlView = new ModelAndView("cart");
			
			return mdlView;
		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while checkout");
		}
	}
	
	
	/*@RequestMapping(value = "/cart/insert", method = RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute("cart") Cart cart, BindingResult result, HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User u = (User)session.getAttribute("user");
		cart.setUser(u);
		u.setCart(cart);
		Cart cd=null;
//		Cart c = cartDao.insert(cart);
//		User user1 = cartDao.update(u);
//		 getSession().update(u);
		 List<Cart> c=cartDao.list();
		 int i=0;
		 Cart cw = null;
		 for(Cart cc:c){
			 if(u.getPersonID()==cc.getId()){
				 cw = cartDao.updateCart(cc);
				 i=1;
				 return new ModelAndView("user-cart","c",cw);
			 }
		 }
	 if (i==0){
		  cd = cartDao.insert(cart);
	 }
	
		return new ModelAndView("user-cart","c",cd);
	}*/
}
