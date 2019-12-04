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
@WebService(serviceName = "BookWS")
public class BookWS {


    /**
     * Web service operation
     */
    @WebMethod(operationName = "AddBook")
    public boolean AddBook(@WebParam(name = "ISBN") int ISBN, @WebParam(name = "BookTitle") String BookTitle, @WebParam(name = "BookDesc") String BookDesc, @WebParam(name = "PublisherName") String PublisherName, @WebParam(name = "PublisherYear") int PublisherYear, @WebParam(name = "Categury") String Categury,
            @WebParam(name = "UserFK") String UserFK,@WebParam(name = "BookStatuse") String BookStatuse,@WebParam(name = "BAssignTo") String BAssignTo,@WebParam(name = "Author") String Author ) {
         boolean result=false;   
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
          
            try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/ShareBooks", "KSU", "KSU")) {
              PreparedStatement st = con.prepareStatement("insert into  BOOKS values(?,?,?,?,?,?,?,?,?,?)");
                st.setInt(1, ISBN);  
                st.setString(2, BookTitle);
                st.setString(3, BookDesc);
                st.setString(4, PublisherName);
                st.setInt(5,PublisherYear);
                st.setString(6, Categury);
                st.setString(7, UserFK);
                st.setString(8, BookStatuse);
                st.setString(9, BAssignTo);
                st.setString(10, Author);
                st.executeUpdate();
                st.close ();  
                System.out.println("insert new Book");
                result=true;
            }  
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
         return result;    
    }
    @WebMethod(operationName = "UpdateBook")
    public boolean UpdateBook(@WebParam(name = "ISBN") int ISBN,@WebParam(name = "BAssignTo") String BAssignTo,@WebParam(name = "BStatus") String BStatus ) {
         boolean result=false;   
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
          
            try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/ShareBooks", "KSU", "KSU")) {
              
                PreparedStatement st = con.prepareStatement("UPDATE BOOKS SET BOOKSTATUSE = '"+BStatus+"' WHERE ISBN="+ISBN+" and BOOKASSIGNTO ='"+BAssignTo+"'");
                st.executeUpdate();
                st.close ();  
                System.out.println("insert new Book");
                result=true;
            }  
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
         return result;  
    }
    
      @WebMethod(operationName = "viewAllBookAvilable")
    public List viewAllBookAvilable() {
      List dataList = new ArrayList();
        int num=0;
    try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/ShareBooks", "KSU", "KSU")) {

                Statement st=con.createStatement();
                String query = "SELECT * FROM BOOKS WHERE BOOKSTATUSE = 'Avilable'";
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

  }
  rs.close ();
  st.close ();   
            }  
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return dataList;
    }
    
          @WebMethod(operationName = "viewUserBookList")
    public List viewUserBookList(@WebParam(name = "BAssignTo") String BAssignTo) {
      List dataList = new ArrayList();
        int num=0;
    try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/ShareBooks", "KSU", "KSU")) {

                Statement st=con.createStatement();
                String query = "SELECT * FROM BOOKS WHERE BOOKASSIGNTO ='"+BAssignTo+"'";
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

  }
  rs.close ();
  st.close ();   
            }  
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return dataList;
    }
             @WebMethod(operationName = "viewTopBooksOrderd")
    public List viewTopBooksOrderd() {
      List dataList = new ArrayList();
        int num=0;
    try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/ShareBooks", "KSU", "KSU")) {

                Statement st=con.createStatement();
                String query = "select FirstSet.NumOrders, SecondSet.*\n" +
"from \n" +
"(SELECT  BOOKS.ISBN,count(ORDERS.ORDERID) AS NumOrders FROM BOOKS JOIN ORDERS ON BOOKS.ISBN = ORDERS.ISBN_FK GROUP BY BOOKS.ISBN) as FirstSet\n" +
"inner join\n" +
"(Select BOOKS.ISBN AS ISBN2,BOOKS.BOOKTITLE, BOOKS.BOOKDESC, BOOKS.AUTHOR, BOOKS.PUBLISHERNAME, BOOKS.BUBLISHERYEAR, BOOKS.CATEGURY, BOOKS.USERNAMEADDBOOK_FK, BOOKS.BOOKASSIGNTO, BOOKS.BOOKSTATUSE from BOOKS) as SecondSet\n" +
"on FirstSet.ISBN = SecondSet.ISBN2\n" +
"Order by FirstSet.NumOrders desc FETCH NEXT 5 ROWS ONLY";
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
  }
  rs.close ();
  st.close ();   
            }  
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return dataList;
    }
}
