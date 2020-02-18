package com.olx.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.olx.exception.ItemException;
import com.olx.model.Item;

public class ItemDAO extends DAO {

    public Item create(Item item)
            throws ItemException {
        try {
            begin();            
            getSession().save(item);     
            commit();
            return item;
        } catch (HibernateException e) {
            rollback();
            throw new ItemException("Exception while creating Item: " + e.getMessage());
        }
    }

    public void delete(Item item)
            throws ItemException {
        try {
            begin();
            getSession().delete(item);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new ItemException("Could not delete item", e);
        }
    }
    
    public void setItemStatus(int item_id, String status)
            throws ItemException {
        try {
           begin();
           System.out.println("here"); 
           String hql = "UPDATE items set item_sold = "+ "'" + status + "'" +" WHERE item_id ="+ item_id;
           System.out.println("Query is - "+hql);
	       Query query = getSession().createQuery(hql);
	       
	       int update = query.executeUpdate();
	       System.out.println("Total rows updated - "+update);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new ItemException("Could not update item status", e);
        }
    }
    
    public Item getItem(int item_id)
            throws ItemException {
        try {
            begin();
            String hql = "from Item where item_id ="+item_id;
            Query q = getSession().createQuery(hql);
            //q.setParameter(0, item_id);
            Item item = (Item)q.uniqueResult();
            return item;
        } catch (HibernateException e) {
            rollback();
            System.out.println(e.getMessage());
            throw new ItemException("Could not fetch the item", e);
        }
    }
    
    public List<Item> list() throws ItemException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Item");
            List<Item> items = q.list();
            commit();
            return items;
        } catch (HibernateException e) {
            rollback();
            throw new ItemException("Could not list items", e);
        }
    	
    }
}