package com.olx.controller;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.olx.dao.CartDAO;
import com.olx.dao.ItemDAO;
import com.olx.dao.UserDAO;
import com.olx.exception.ItemException;
import com.olx.model.Cart;
import com.olx.model.Item;
import com.olx.model.User;

@Controller
public class ItemController {
		
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

		@RequestMapping(value = "/item/list", method = RequestMethod.GET)
		public ModelAndView addCategory(HttpServletRequest request) throws Exception {

			ModelAndView mav = new ModelAndView("buyer-item-list");
			List<Item> items = itemDao.list();
			mav.addObject("items", items);
	        mav.addObject("cart", new Cart());
	        return mav;
		}
		
		@RequestMapping(value = "/item/sellerlist", method = RequestMethod.GET)
		public ModelAndView addCategories(HttpServletRequest request) throws Exception {

			try {			
				
				List<Item> items = itemDao.list();
				return new ModelAndView("seller-items-list", "items", items);
				
			} catch (ItemException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
		}

		/*@RequestMapping(value="/item/add", method = RequestMethod.GET)
		public ModelAndView initializeForm(HttpServletRequest request) throws Exception {		
			ModelAndView mv = new ModelAndView();
			mv.addObject("item", new Item());
			mv.setViewName("item");
			return mv;
		}*/
}