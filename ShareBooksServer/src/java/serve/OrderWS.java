/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serve;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author TOSHIBA
 */
@WebService(serviceName = "OrderWS")
public class OrderWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "CreatNewOrder")
    public boolean CreatNewOrder(@WebParam(name = "ISBN") int ISBN, @WebParam(name = "UserName") String UserName) {
         boolean result=false;  
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
          
            try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/ShareBooks", "KSU", "KSU")) {
             
                PreparedStatement st = con.prepareStatement("insert into  ORDERS (USERNAME_FK,ISBN_FK,ORDERDATE,ORDERTIME,ORDERSTATUS) values (?,?,?,?,?)");
                st.setString(1, UserName);  
                st.setInt(2, ISBN);
                java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
                st.setDate(3, sqlDate);
                java.sql.Time sqlTime= new java.sql.Time(new java.util.Date().getTime());
                st.setTime(4, sqlTime);
                st.setString(5,"Created");
                st.executeUpdate();
                st.close ();  
                System.out.println("Create new Order");
                
                st = con.prepareStatement("UPDATE BOOKS SET BOOKSTATUSE = 'Ordered' where ISBN="+ISBN+"");
                st.executeUpdate();
                st.close ();  
                result= true;
            }  
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
         return result;  
        
    }
             @WebMethod(operationName = "viewOrders")
    public List viewOrders(@WebParam(name = "UserName") String UserName) {
      List dataList = new ArrayList();
        int num=0;
    try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/ShareBooks", "KSU", "KSU")) {

                Statement st=con.createStatement();
                String query = "SELECT * FROM ORDERS JOIN USERS ON ORDERS.USERNAME_FK = USERS.USERNAME JOIN BOOKS ON ORDERS.ISBN_FK = BOOKS.ISBN where ORDERS.ORDERSTATUS='Created' and BOOKS.BOOKASSIGNTO='"+UserName+"'";
                ResultSet rs = st.executeQuery(query);
              
         while (rs.next ()){
num++;
  //Add records into data list

dataList.add(rs.getString(1));
dataList.add(rs.getString(2));
dataList.add(rs.getString(3));
dataList.add(rs.getString(4));
dataList.add(rs.getString(5));
dataList.add(rs.getString(6));
dataList.add(rs.getString(7));
dataList.add(rs.getString(8));
dataList.add(rs.getString(9));
dataList.add(rs.getString(10));
dataList.add(rs.getString(11));
dataList.add(rs.getString(12));
dataList.add(rs.getString(13));
dataList.add(rs.getString(14));
dataList.add(rs.getString(15));
dataList.add(rs.getString(16));
dataList.add(rs.getString(17));
dataList.add(rs.getString(18));
dataList.add(rs.getString(19));
dataList.add(rs.getString(20));
dataList.add(rs.getString(21));
        

  }
  rs.close ();
  st.close ();   
            }  
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return dataList;
    }
        @WebMethod(operationName = "UpdateOrderStatus")
        public boolean UpdateOrderStatus(@WebParam(name = "OrderId") int OrderId,@WebParam(name = "ISBN") int ISBN, @WebParam(name = "UserName") String UserName) {
         boolean result=false;  
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
          
            try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/ShareBooks", "KSU", "KSU")) {
                PreparedStatement st = con.prepareStatement("UPDATE ORDERS SET ORDERSTATUS = 'OrderComplete' where ORDERID="+OrderId+"");
                st.executeUpdate();
                st.close ();  
                
                st = con.prepareStatement("UPDATE BOOKS SET BOOKASSIGNTO = '"+UserName+"' where ISBN="+ISBN+"");
                st.executeUpdate();
                st.close ();  
                result = true;
                

            }  
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
         return result;  
        
    }
}
