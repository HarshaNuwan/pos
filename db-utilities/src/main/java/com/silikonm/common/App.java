package com.silikonm.common;

import org.hibernate.Session;

import com.silikonm.common.entities.ItemCategory;
import com.silikonm.common.persistence.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       Session session = HibernateUtil.getSessionFactory().openSession();
       
       session.beginTransaction();
       
//       Employee employee = new Employee();
//       employee.setEmployeeId(1155);
//       employee.setFirstName("Roshika");
//       employee.setLastName("Gunarathna");
//       employee.setAddress("No 4, Kambiyawaththa.");
//       
//       session.save(employee);
       
       ItemCategory category = new ItemCategory();
       category.setCategory_id(2);
       category.setCategory_code("PRE002");
       category.setCategory_name("Nevea");
       session.save(category);
       
       session.getTransaction().commit();
    }
}
