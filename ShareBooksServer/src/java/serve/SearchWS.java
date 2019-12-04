/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serve;

import java.sql.Connection;
import java.sql.DriverManager;
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
@WebService(serviceName = "SearchWS")
public class SearchWS {

          @WebMethod(operationName = "SearchBy")
    public List SearchBy(@WebParam(name = "searchatt") String searchatt,@WebParam(name = "searchvalue") String searchvalue) {
      List dataList = new ArrayList();
        int num=0;
    try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/ShareBooks", "KSU", "KSU")) {

                Statement st=con.createStatement();
                String query = "SELECT * FROM BOOKS WHERE "+searchatt+"='"+searchvalue+"'";
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
}
