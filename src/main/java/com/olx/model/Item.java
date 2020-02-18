package com.olx.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name="items")
public class Item {

	@Id
	@Column(name="item_id", unique = true, nullable = false)
    private int item_id;
	
	@Column(name="item_name")
    private String item_name;
	
	@Column(name="item_description")
    private String item_description;
	
	@Column(name="years_of_usage")
    private int years_of_usage;
	
	@Column(name = "item_sold")
	private String item_sold; 
	
	@Column(name = "item_price")
	private double item_price;
	
	@Column(name = "seller")
	private String seller;


	@ManyToOne
	private User user;
	
	@ManyToMany(mappedBy="items")
	private Set<Cart> cart = new HashSet<Cart>();
	

	public CommonsMultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}

	@Transient
	private CommonsMultipartFile photo;

    public Item() {
    }

	public Item(int item_id, String item_name, String item_description,  int years_of_usage, String item_sold,
			double item_price, String seller, User user, Set<Cart> cart, CommonsMultipartFile photo) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_description = item_description;
		this.years_of_usage = years_of_usage;
		this.item_sold = item_sold;
		this.item_price = item_price;
		this.seller = seller;
		this.user = user;
		this.cart = cart;
		this.photo = photo;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public int getYears_of_usage() {
		return years_of_usage;
	}

	public void setYears_of_usage(int years_of_usage) {
		this.years_of_usage = years_of_usage;
	}

	public String getItem_sold() {
		return item_sold;
	}

	public void setItem_sold(String item_sold) {
		this.item_sold = item_sold;
	}

	public double getItem_price() {
		return item_price;
	}

	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Cart> getCart() {
		return cart;
	}

	public void setCart(Set<Cart> cart) {
		this.cart = cart;
	}
   
}