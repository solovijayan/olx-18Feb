package com.olx.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.olx.model.User;

@Entity
@Table(name="Cart")
public class Cart{

	@OneToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@Id
	@Column(name = "cart_id", unique = true, nullable = false)
	private int cart_id;
	
	@Column(name="item_name")
	private String item_name;
	
	@Column(name="item_id")
	private int item_id;
	
	@Column(name="item_description")
	private String item_description;
	
	@Column(name="seller")
	private String seller;
	
	@Column(name="buyer")
	private String buyer;
	
	@Column(name="item_price")
	private double item_price;
	
	@ElementCollection(targetClass=Item.class)
	private Set<Item> items = new HashSet<Item>();

	public Cart(){
		
	}

	public Cart(int cart_id, int item_id, String item_name, String item_description, String seller, String buyer, double item_price) {
		super();
		this.cart_id = cart_id;
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_description = item_description;
		this.seller = seller;
		this.buyer = buyer;
		this.item_price = item_price;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public Cart(int cart_id) {
		super();
		this.cart_id = cart_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
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

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

}
