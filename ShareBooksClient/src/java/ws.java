/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import serve.BookWS_Service;
import serve.OrderWS_Service;
import serve.SearchWS_Service;
import serve.UsersWS_Service;

/**
 *
 * @author HOME
 */
@WebServlet(urlPatterns = {"/ws"})
public class ws extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ShareBooksServer/SearchWS.wsdl")
    private SearchWS_Service service_3;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ShareBooksServer/OrderWS.wsdl")
    private OrderWS_Service service_2;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ShareBooksServer/BookWS.wsdl")
    private BookWS_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ShareBooksServer/UsersWS.wsdl")
    private UsersWS_Service service;

    //vars
    int size;
    String str = "";
    String isbn, bookTitle, bookDesc, publisherName, publisherYear, categury, userFK, bookStatuse, bAssignTo, author;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String type = request.getParameter("type");
            String username;
            String password;
            switch(type)
            {
                case "login":
                      username = request.getParameter("username");
                      password = request.getParameter("password");
            if(login(username , password))
            {
                
           request.setAttribute("username", username);
           request.setAttribute("login", true);
           request.getRequestDispatcher("index.jsp").forward(request, response); 
            }
            else
            {
       
           out.println("<script type=\"text/javascript\">");
           out.println("alert('please enter a correct username and password');");
           out.println("location='index.jsp';");
           out.println("</script>"); 
         
            }
            break;
                case "register":
                     username = request.getParameter("username");
                     String fullname = request.getParameter("name");
                     String email = request.getParameter("email");
                     password = request.getParameter("password");
                     int phone = Integer.parseInt(request.getParameter("phone"));
                    if( reguster(username , fullname, email, password, phone))
                    {
                         request.setAttribute("username", username);
                         request.setAttribute("login", true);
                         request.getRequestDispatcher("index.jsp").forward(request, response); 
                    }
                    else
                    {
                          out.println("<script type=\"text/javascript\">");
                          out.println("alert('please enter a unique username');");
                          out.println("location='index.jsp';");
                          out.println("</script>"); 
                    }
                    break;
                    case"listall":
                         str="";
                         List list = viewAllBookAvilable();
                         size = list.size();
                         String usr = request.getParameter("username");
                 
                         
                         for(int i = 0 ; i<size ; i+=10)
                         {
                           isbn = (String) list.get(i);
                           bookTitle = (String) list.get(i+1);
                           bookDesc = (String) list.get(i+2);
                           publisherName = (String) list.get(i+3);
                           publisherYear = (String) list.get(i+4);
                           categury = (String) list.get(i+5);
                           userFK = (String) list.get(i+6);
                           bookStatuse = (String) list.get(i+7);
                           bAssignTo = (String) list.get(i+8);
                           author = (String) list.get(i+9);
                         
                          str+= " <div class=\"col-md-6 col-lg-4\">"+
		   "<div class=\" d-block mx-auto\" href=\"\">"+
                   "<div class=\"\"> </div>"+
                
             
			   "<center> <div  style=\"height:1005; width:100%; background-color:#CCB2A3;\" ><br>"+
                             "  <h6 style=\"color: #2C3E50\" >ISBN<h6> <p> "+isbn+" </p>"+
			      "  <h6 style=\"color: #2C3E50\" >Title<h6> <p> "+bookTitle+" </p>"+
                              "<h6 style=\"color: #2C3E50\" >Category<h6> <p> "+categury+" </p>"+
                              "<h6 style=\"color: #2C3E50\" >Publish Year<h6> <p> "+publisherYear+" </p>"+
                              "<h6 style=\"color: #2C3E50\" >Publisher Name<h6> <p> "+publisherName+" </p>"+
			      "<h6 style=\"color: #2C3E50\" >Description<h6> <p> "+bookDesc+"  </p>"+
			      "<h6 style=\"color: #2C3E50\" >Author<h6> <p> "+author+" </p><br>";
                          if(!usr.equals(bAssignTo))
			      str+="<button onClick=\"order(this)\" style=\"height:10%; width:40%;\" type=\"submit\" class=\"btn btn-primary \" id=\""+isbn+"\">order</button><br><br>";
			   str+="</div><br><br><br></center> </div> </div>";
                          
                             
                         }
                       
                         out.println(str);
                         break;
                    case "top5":
                         str="";
                         List top = viewTopBooksOrderd();
                         size = top.size();
                          
                         for(int i = 0 ; i<size ; i+=11)
                         {
                           isbn = (String) top.get(i);
                           bookTitle = (String) top.get(i+1);
                           bookDesc = (String) top.get(i+2);
                           publisherName = (String) top.get(i+3);
                           publisherYear = (String) top.get(i+4);
                           categury = (String) top.get(i+5);
                           userFK = (String) top.get(i+6);
                           bookStatuse = (String) top.get(i+7);
                           bAssignTo = (String) top.get(i+8);
                           author = (String) top.get(i+9);
                         
                          str+= " <div class=\"col-md-6 col-lg-4\">"+
		           "<div class=\" d-block mx-auto\" href=\"\">"+
                           "<div class=\"portfolio-item-caption d-flex position-absolute h-100 w-100\"> </div>"+
                           "<center> <div  style=\"height:300px; width:100%; background-color:#CCB2A3;\" ><br>"+
			      "  <h6 style=\"color: #2C3E50\" >Title<h6> <p> "+bookTitle+" </p>"+
                              "<h6 style=\"color: #2C3E50\" >Category<h6> <p> "+categury+" </p>"+
                              "<h6 style=\"color: #2C3E50\" >Description<h6> <p> "+bookDesc+"  </p>"+
			      "<h6 style=\"color: #2C3E50\" >Author<h6> <p> "+author+" </p>"+
			      " </div><br><br><br></center> </div> </div>";
                         }
                        out.println(str);
                        break;
                        case "myList":
                         str="";
                         String assignTo  = request.getParameter("assignto");
                         
                         List myList = viewUserBookList(assignTo);
                         size = myList.size();
                    
                         for(int i = 0 ; i<size ; i+=10)
                         {
                           isbn = (String) myList.get(i);
                           bookTitle = (String) myList.get(i+1);
                           bookDesc = (String) myList.get(i+2);
                           publisherName = (String) myList.get(i+3);
                           publisherYear = (String) myList.get(i+4);
                           categury = (String) myList.get(i+5);
                           userFK = (String) myList.get(i+6);
                           bookStatuse = (String) myList.get(i+7);
                           bAssignTo = (String) myList.get(i+8);
                           author = (String) myList.get(i+9);
                         
                          str+= " <div class=\"col-md-6 col-lg-4\">"+
		           "<div class=\" d-block mx-auto\" href=\"\">"+
                           "<div> </div>"+
                           "<center> <div  style=\"height:400px; width:100%; background-color:#CCB2A3;\" ><br>"+
                             "  <h6 style=\"color: #2C3E50\" >ISBN<h6> <p> "+isbn+" </p>"+
			      "  <h6 style=\"color: #2C3E50\" >Title<h6> <p> "+bookTitle+" </p>"+
                              "<h6 style=\"color: #2C3E50\" >Category<h6> <p> "+categury+" </p>"+
                              "<h6 style=\"color: #2C3E50\" >Description<h6> <p> "+bookDesc+"  </p>"+
			      "<h6 style=\"color: #2C3E50\" >Author<h6> <p> "+author+" </p>"+
                               "<form action=\"ws\" method=\"post\" > "+
                                  "<input type=\"hidden\" name=\"isbn\" value=\""+isbn+"\" >"+
                                    "<input type=\"hidden\" name=\"assignto\" value=\""+assignTo+"\" >"+
                                    "<input type=\"hidden\" name=\"type\" value=\""+"updateBook"+"\" >";
                          if(!bookStatuse.equals("Avilable"))
                              str+="<button style=\"height:10%; width:40%;\" type=\"submit\" class=\"btn btn-primary \" >Make Available</button>"+
			      "</form> </div><br><br><br></center> </div> </div>";
                          else
                               str+="</form> </div><br><br><br></center> </div> </div>";
                       
                         }
                         if(size == 0 )
                            str="You Have No Books !";
                        out.println(str);
                        break;
                        case"updateBook":
                             String Isbn  = request.getParameter("isbn");
                             String assignto  = request.getParameter("assignto");
                             if(updateBook(Integer.parseInt(Isbn), assignto , "Avilable"))
                             {
                                  out.println("<script type=\"text/javascript\">");
                                  out.println("alert('Book is updated successfully');");
                                  out.println("location='account.jsp';");
                                  out.println("</script>"); 
                             }
                             else
                           {
                                  out.println("<script type=\"text/javascript\">");
                                  out.println("alert('Book NOT updated !!');");
                                  out.println("location='account.jsp';");
                                  out.println("</script>"); 
                             }
                            break;
                        case "addbook":
                            int isbn = Integer.parseInt(request.getParameter("isbn"));
                            String bookTitle = request.getParameter("title");
                            String bookDesc = request.getParameter("description");
                            String publisherName = request.getParameter("publisher");
                            int publisherYear = Integer.parseInt(request.getParameter("year"));
                            String categury = request.getParameter("category");
                            String userFK = request.getParameter("username");
                            String author = request.getParameter("author");
                         
                            if(addBook(isbn, bookTitle, bookDesc, publisherName, publisherYear, categury, userFK, "Avilable", userFK, author))
                                   {
                                  out.println("<script type=\"text/javascript\">");
                                  out.println("alert('Book is added successfully');");
                                  out.println("location='account.jsp';");
                                  out.println("</script>"); 
                             }
                             else
                           {
                                  out.println("<script type=\"text/javascript\">");
                                  out.println("alert('Book NOT Added !!');");
                                  out.println("location='account.jsp';");
                                  out.println("</script>"); 
                             }
                            break;
                         case"order":
                            
                          if( creatNewOrder(Integer.parseInt(request.getParameter("isbn")),request.getParameter("username") ))
                           out.println("order successfully"); 
                         
                          else
                            out.println("some error happend !!"); 
                           
                             
                             break;
                             
                         case "search":
                             String str1 = request.getParameter("by");
                             String str2 = request.getParameter("value");
                             String u = request.getParameter("username");
                             str="";
                         List search = searchBy(str1,str2);
                         size = search.size();
                        
                 
                         
                         for(int i = 0 ; i<size ; i+=10)
                         {
                           String isbn2 = (String) search.get(i);
                           bookTitle = (String) search.get(i+1);
                           bookDesc = (String) search.get(i+2);
                           publisherName = (String) search.get(i+3);
                          String publisherYear2 = (String) search.get(i+4);
                           categury = (String) search.get(i+5);
                           userFK = (String) search.get(i+6);
                           bookStatuse = (String) search.get(i+7);
                           bAssignTo = (String) search.get(i+8);
                           author = (String) search.get(i+9);
                         
                          str+= " <div class=\"col-md-6 col-lg-4\">"+
		   "<div class=\" d-block mx-auto\" href=\"\">"+
                   "<div class=\"\"> </div>"+
                
             
			   "<center> <div  style=\"height:1005; width:100%; background-color:#CCB2A3;\" ><br>"+
                             "  <h6 style=\"color: #2C3E50\" >ISBN<h6> <p> "+isbn2+" </p>"+
			      "  <h6 style=\"color: #2C3E50\" >Title<h6> <p> "+bookTitle+" </p>"+
                              "<h6 style=\"color: #2C3E50\" >Category<h6> <p> "+categury+" </p>"+
                              "<h6 style=\"color: #2C3E50\" >Publish Year<h6> <p> "+publisherYear2+" </p>"+
                              "<h6 style=\"color: #2C3E50\" >Publisher Name<h6> <p> "+publisherName+" </p>"+
			      "<h6 style=\"color: #2C3E50\" >Description<h6> <p> "+bookDesc+"  </p>"+
			      "<h6 style=\"color: #2C3E50\" >Author<h6> <p> "+author+" </p><br>";
                          if(!u.equals(bAssignTo) && bookStatuse.equals("Avilable") )
			      str+="<button onClick=\"order(this)\" style=\"height:10%; width:40%;\" type=\"submit\" class=\"btn btn-primary \" id=\""+isbn2+"\">order</button><br><br>";
			   str+="</div><br><br><br></center> </div> </div>";
                          
                             
                         }
                       
                         out.println(str);
                            break;
                             
                        case"myorders":
                          String u2 = request.getParameter("username");
                           List myOrder =  viewOrders(u2);
                           size = myOrder.size();
                           str="";
                         for(int i = 0 ; i<size ; i+=21)
                         {
                             if(!myOrder.get(i+18).equals("Avilable"))
                             {
                             str+= " <div class=\"col-md-6 col-lg-4\">"+
		           "<div class=\" d-block mx-auto\" href=\"\">"+
                           "<div> </div>"+
                           "<center> <div  style=\"height:400px; width:100%; background-color:#CCB2A3;\" ><br>"+
                             "<h6 style=\"color: #2C3E50\" >Book #<h6> <p> "+myOrder.get(i+11)+"  </p>"+
			     "<h6 style=\"color: #2C3E50\" >Book Title<h6> <p> "+myOrder.get(i+12)+" </p>"+
                             "<hr>  <h6 style=\"color: #2C3E50\" >User Name<h6> <p> "+myOrder.get(i+7)+" </p>"+
			     "  <h6 style=\"color: #2C3E50\" >User Email<h6> <p> "+myOrder.get(i+8)+" </p>"+
                             "<h6 style=\"color: #2C3E50\" >User Mobile<h6> <p> "+myOrder.get(i+10)+" </p>";
                             
                             
                          str+="<button onclick=\"updateorder(this)\" style=\"height:10%; width:40%;\" type=\"submit\" class=\"btn btn-primary \" id=\""+myOrder.get(i)+"-"+myOrder.get(i+1)+"-"+myOrder.get(i+2)+"\">Complete</button>"+
			      "</div><br><br><br></center> </div> </div>";
                             }
                         
                         }
                          if(size == 0 )
                             str="You Have No Orders !";
                         out.println(str);
                            break;
                        case "updateorder":
                            if(updateOrderStatus(Integer.parseInt(request.getParameter("orderid")), Integer.parseInt(request.getParameter("isbn")),  request.getParameter("user")  )   )
                                 out.println("Order is completed successfully");
                           
                            else
                           
                                  out.println("Order is NOT completed successfully");
                             
                            break;
          
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean login(java.lang.String userName, java.lang.String password) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serve.UsersWS port = service.getUsersWSPort();
        return port.login(userName, password);
    }

    private boolean reguster(java.lang.String userName, java.lang.String fullName, java.lang.String emailID, java.lang.String password, int mobileNum) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serve.UsersWS port = service.getUsersWSPort();
        return port.reguster(userName, fullName, emailID, password, mobileNum);
    }

    private boolean addBook(int isbn, java.lang.String bookTitle, java.lang.String bookDesc, java.lang.String publisherName, int publisherYear, java.lang.String categury, java.lang.String userFK, java.lang.String bookStatuse, java.lang.String bAssignTo, java.lang.String author) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serve.BookWS port = service_1.getBookWSPort();
        return port.addBook(isbn, bookTitle, bookDesc, publisherName, publisherYear, categury, userFK, bookStatuse, bAssignTo, author);
    }

    private boolean updateBook(int isbn, java.lang.String bAssignTo, java.lang.String bStatus) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serve.BookWS port = service_1.getBookWSPort();
        return port.updateBook(isbn, bAssignTo, bStatus);
    }

    private java.util.List<java.lang.Object> viewAllBookAvilable() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serve.BookWS port = service_1.getBookWSPort();
        return port.viewAllBookAvilable();
    }

    private java.util.List<java.lang.Object> viewUserBookList(java.lang.String bAssignTo) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serve.BookWS port = service_1.getBookWSPort();
        return port.viewUserBookList(bAssignTo);
    }

    private java.util.List<java.lang.Object> viewTopBooksOrderd() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serve.BookWS port = service_1.getBookWSPort();
        return port.viewTopBooksOrderd();
    }

    private java.util.List<java.lang.Object> viewAllBookAvilable_1() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serve.BookWS port = service_1.getBookWSPort();
        return port.viewAllBookAvilable();
    }

    private boolean creatNewOrder(int isbn, java.lang.String userName) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serve.OrderWS port = service_2.getOrderWSPort();
        return port.creatNewOrder(isbn, userName);
    }

    private boolean updateOrderStatus(int orderId, int isbn, java.lang.String userName) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serve.OrderWS port = service_2.getOrderWSPort();
        return port.updateOrderStatus(orderId, isbn, userName);
    }

    private java.util.List<java.lang.Object> viewOrders(java.lang.String userName) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serve.OrderWS port = service_2.getOrderWSPort();
        return port.viewOrders(userName);
    }

    private java.util.List<java.lang.Object> searchBy(java.lang.String searchatt, java.lang.String searchvalue) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        serve.SearchWS port = service_3.getSearchWSPort();
        return port.searchBy(searchatt, searchvalue);
    }

}
