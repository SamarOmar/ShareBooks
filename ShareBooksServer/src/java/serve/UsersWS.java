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
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author TOSHIBA
 */
@WebService(serviceName = "UsersWS")
public class UsersWS {

    /**
     * This is a sample web service operation
     * @param EmailID
     * @param Password
     * @return 
     */
    @WebMethod(operationName = "Login")
    public boolean Login(@WebParam(name = "UserName") String UserName, @WebParam(name = "Password") String Password) {
        boolean result=false;   
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/ShareBooks", "KSU", "KSU")) {
                Statement st=con.createStatement();
                String query = "select * from USERS where USERNAME='"+UserName+"' and PASS='"+Password+"'";
                ResultSet rs = st.executeQuery(query);
              while (rs.next()){result = true;}
  rs.close ();
  st.close ();   
            }  
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
         return result;  
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Reguster")
    public boolean Reguster(@WebParam(name = "UserName") String UserName, @WebParam(name = "FullName") String FullName, @WebParam(name = "EmailID") String EmailID, @WebParam(name = "Password") String Password, @WebParam(name = "MobileNum") int MobileNum) {
         boolean result=false;   
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
          
            try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/ShareBooks", "KSU", "KSU")) {
             
                /*Statement st=con.createStatement();
                
                String query = "select * from USERS where EMAILID='"+EmailID+"' and PASS='"+Password+"'";
                
                ResultSet rs = st.executeQuery(query);
              
              while (rs.next()){result = true;}*/
              
              PreparedStatement st = con.prepareStatement("insert into USERS values(?,?,?,?,?)");
                st.setString(1, UserName);  
                st.setString(2, FullName);
                st.setString(3, EmailID);
                st.setString(4, Password);
                st.setInt(5,MobileNum);
                st.executeUpdate();
                st.close ();  
                System.out.println("insert new user");
                result=true;
            }  
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
         return result;  
        
    }
    
    
         
}
