<%-- 
    Document   : index
    Created on : Dec 9, 2017, 1:20:54 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Book Share</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- Plugin CSS -->
    <link href="vendor/magnific-popup/magnific-popup.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href="css/freelancer.min.css" rel="stylesheet">

  </head>

  <body  id="page-top">

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg bg-secondary fixed-top text-uppercase" id="mainNav">
      <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#page-top">Book Sharing</a>
        <button class="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
               <li class="nav-item mx-0 mx-lg-1">
              <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger " href="#home">Home</a>
            </li>
            <li class="nav-item mx-0 mx-lg-1">
              <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger " href="#booklist">Book List</a>
            </li>
            <li class="nav-item mx-0 mx-lg-1">
              <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="#about">About</a>
            </li>
            <!-- Top 5 -->

            
            
            
            
    <% 
     String username = (String)request.getAttribute("username");
     Boolean login =  (Boolean)request.getAttribute("login");
     
     if(login != null)
     if(login)
    { session.setAttribute("username", username);
      session.setAttribute("login", login);
     %>
     <script>
         alert("logged into your account auccessfully");
     </script>
     <% }
    if(session.getAttribute( "username" ) == null)
    {
               out.println("<li class='nav-item mx-0 mx-lg-1'> <a class='nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger portfolio-item' href='#portfolio-modal-1'>LogIn </a></li>  "
                    +"<li class='nav-item mx-0 mx-lg-1'><a class='nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger portfolio-item' href='#portfolio-modal-2'>Register </a> </li> "); 
      
    }
  else
    {
        out.println("<li class='nav-item mx-0 mx-lg-1'> <a class='nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger ' href='account.jsp'>My Account </a></li>  "  ); 
        out.println("<li class='nav-item mx-0 mx-lg-1'> <a class='nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger ' href='logout.jsp'>Log out </a></li>  "  ); 
    }
                 %>
			
             
          </ul>
        </div>
      </div>
    </nav>

    <!-- Header -->
    <header id="home" class="masthead bg-primary text-white text-center">
      <div class="container">
        <img class="img-fluid mb-5 d-block mx-auto" width="40%" height="40%"  src="img/profile.png" alt="">
        <h1 class="text-uppercase mb-0">Start Sharing Your Book</h1>
        <hr class="star-light">
        <h2 class="font-weight-light mb-0" style="color:#2C3E50">Add your book - Order others' books - Contact with your providers </h2>
      </div>
    </header>

    <!-- Portfolio Grid Section -->
    <section class="portfolio" id="booklist">
      <div class="container">
        <h2 class="text-center text-uppercase text-secondary mb-0">Book List</h2>
     
        <hr class="star-dark mb-5">
          
        <center>
         
                  
          
                <div class="form-group" style=" display:inline;">
		   <select class="form-control " style="width:180px; display:inline; height:60px;"  id="by" required="required" data-validation-required-message="Please enter your password." >
			<option disabled selected value>Search By </option>
                         <option>All Available</option>
                         <option>ISBN</option>
                         <option>BOOKTITLE</option>
                         <option>PUBLISHERNAME</option>
	           </select>
		</div>
                
                <div class="form-group" style=" display:inline;">
		   <input class="form-control " style="width:200px; height:60px; display:inline;" type="text" id="value1"  placeholder="Word" required="required"  >
		
		</div>
                
               
		<input type="hidden" id="username1" value="<% out.print(session.getAttribute("username") );  %>">	  
		
		
               
              <div class="form-group" style=" display:inline; ">
                  <button type="submit" onclick="search()" class="btn btn-primary btn-xl" style="">Search</button>
              </div>
            </center><br>
        <div id="listrow" class="row">
  
            <script>
                function search()
                {
                    if($("#by").val() == "" )
                    {
                        alert("you have to choose search by field !!"); return;
                    }
                    if($("#by").val() == "All Available")
                        list();
                    else
                        searching();
                    
                }
     function searching()
     {
               $.ajax({
		type:'POST', 
		url: 'ws',
                data: {type: "search", username: $("#username1").val(), by: $("#by").val(), value: $("#value1").val()},
		
		
	  
         	success: function(response){ 
                      $("#listrow").html(response);
		
		
		} //End success function
		
		, error: function(errors) {
		alert("عذرا حصل خطأ, الرجاء التأكد من اتصالك بالانترنت");

		}
		});
     }
                </script>
                
            
		
     
	 </div>
    </section>

    <!-- About Section -->
    <section class="bg-primary text-white mb-0" id="about">
      <div class="container">
        <h2 class="text-center text-uppercase text-white">About</h2>
        <hr class="star-light mb-5">
        <div class="row">
         
              <center> <p style="width: 50%" class="lead">
            Book sharing is a platform for giving away books that you do not need any more. You can add, 
            declare and deliver books to anyone who is interested in owning your precious books.
            </p></center>
     
          
        </div>
    
      </div>
    </section>

    <!-- Contact Section -->
    <!-- Top 5 -->

    <!-- Footer -->
    <footer class="footer text-center">
      <div class="container">
        <div class="row">
         
          <div class="col-md-12 mb-5 mb-lg-0">
            <h4 class="text-uppercase mb-4">Book Sharing</h4>
            <ul class="list-inline mb-0">
              <li class="list-inline-item">
                <a class="btn btn-outline-light btn-social text-center rounded-circle" href="#">
                  <i class="fa fa-fw fa-facebook"></i>
                </a>
              </li>
              <li class="list-inline-item">
                <a class="btn btn-outline-light btn-social text-center rounded-circle" href="#">
                  <i class="fa fa-fw fa-google-plus"></i>
                </a>
              </li>
              <li class="list-inline-item">
                <a class="btn btn-outline-light btn-social text-center rounded-circle" href="#">
                  <i class="fa fa-fw fa-twitter"></i>
                </a>
              </li>
              <li class="list-inline-item">
                <a class="btn btn-outline-light btn-social text-center rounded-circle" href="#">
                  <i class="fa fa-fw fa-linkedin"></i>
                </a>
              </li>
              <li class="list-inline-item">
                <a class="btn btn-outline-light btn-social text-center rounded-circle" href="#">
                  <i class="fa fa-fw fa-dribbble"></i>
                </a>
              </li>
            </ul>
          </div>
         
        </div>
      </div>
    </footer>

    <div class="copyright py-4 text-center text-white">
      <div class="container">
        <small>Copyright &copy; Book Sharing 2019</small>
      </div>
    </div>

    <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
    <div class="scroll-to-top d-lg-none position-fixed ">
      <a class="js-scroll-trigger d-block text-center text-white rounded" href="#page-top">
        <i class="fa fa-chevron-up"></i>
      </a>
    </div>

    <!-- Portfolio Modals -->

    <!-- Portfolio Modal 1 -->
    <div class="portfolio-modal mfp-hide" id="portfolio-modal-1">
      <div class="portfolio-modal-dialog bg-white">
        <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#">
          <i class="fa fa-3x fa-times"></i>
        </a>
        <div class="container text-center">
          <div class="row">
            <div class="col-lg-8 mx-auto">
              <h2 class="text-secondary text-uppercase mb-0">Login</h2>
              <hr class="star-dark mb-5">
              <form  novalidate="novalidate" method="post" action="ws">
              <div class="control-group">
                <div class="form-group floating-label-form-group controls mb-0 pb-2">
                  <label>Userame</label>
                  <input class="form-control" name="username" type="text" placeholder="Username" required="required" data-validation-required-message="Please enter your Username.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
              <div class="control-group">
                <div class="form-group floating-label-form-group controls mb-0 pb-2">
                  <label>Password</label>
                  <input class="form-control" name="password" type="password" placeholder="Password" required="required" data-validation-required-message="Please enter your password.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
            <input name="type" type="hidden"  value="login">

              <br>
              <div id="success"></div>
              <div class="form-group">
                <button type="submit" class="btn btn-primary btn-xl" id="">Login</button>
              </div>
            </form>
          
            </div>
          </div>
        </div>
      </div>
    </div>
   
    <!-- Portfolio Modal 2 -->
    <div class="portfolio-modal mfp-hide" id="portfolio-modal-2">
      <div class="portfolio-modal-dialog bg-white">
        <a class="close-button d-none d-md-block portfolio-modal-dismiss" href="#">
          <i class="fa fa-3x fa-times"></i>
        </a>
        <div class="container text-center">
          <div class="row">
            <div class="col-lg-8 mx-auto">
              <h2 class="text-secondary text-uppercase mb-0">Register</h2>
              <hr class="star-dark mb-5">
              <form name="sentMessage" method="post" action="ws" >
              <div class="control-group">
                <div class="form-group floating-label-form-group controls mb-0 pb-2">
                  <label>Userame</label>
                  <input class="form-control" name="username" type="text" placeholder="Username" required="required" data-validation-required-message="Please enter your Username.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
              <div class="control-group">
                <div class="form-group floating-label-form-group controls mb-0 pb-2">
                  <label>Password</label>
                  <input class="form-control" name="password" type="password" placeholder="Password" required="required" data-validation-required-message="Please enter your password.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
           
		        <div class="control-group">
                <div class="form-group floating-label-form-group controls mb-0 pb-2">
                  <label>Full Name</label>
                  <input class="form-control" name="name" type="text" placeholder="Full Name" required="required" data-validation-required-message="Please enter your full name.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
			  
			     <div class="control-group">
                <div class="form-group floating-label-form-group controls mb-0 pb-2">
                  <label>Email</label>
                  <input class="form-control" name="email" type="email" placeholder="Email" required="required" data-validation-required-message="Please enter your email.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
			  
			     <div class="control-group">
                <div class="form-group floating-label-form-group controls mb-0 pb-2">
                  <label>Phone number</label>
                  <input class="form-control" name="phone" type="number" placeholder="Phone number" required="required" data-validation-required-message="Please enter your phoe number.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
		
                 <input name="type" type="hidden"  value="register">
		   
		   
              <br>
              <div id="success"></div>
              <div class="form-group">
                <button type="submit" class="btn btn-primary btn-xl" >Register</button>
              </div>
            </form>
            </div>
          </div>
        </div>
      </div>
    </div>


    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>
     <script src="js/contact_me.js"></script>

    <!-- Custom scripts for this template -->
    <script src="js/freelancer.min.js"></script>

        <script>
        $(document).ready(function() {
  	list();

 });
 function list()
 {
               
        	$.ajax({
		type:'POST', 
		url: 'ws',
                data: {type: "listall", username: <%=session.getAttribute("username")%>},
		
		
	  
      	success: function(response){ 
	    
	    $("#listrow").html(response);
		$.ajax({
		type:'POST', 
		url: 'ws',
                data: {type: "top5"},
		
		
	  
         	success: function(response){ 
		 $("#orderlist").html(response);
		
		} //End success function
		
		, error: function(errors) {
		alert("عذرا حصل خطأ, الرجاء التأكد من اتصالك بالانترنت");

		}
		});
		} //End success function
		
		, error: function(errors) {
		alert("عذرا حصل خطأ, الرجاء التأكد من اتصالك بالانترنت");

		}
		}); //End AJAX
 }
 function order(element)
 {
      var isbn = $(element).attr("id");
      var login = <%=session.getAttribute("login")%>;
      var username = <%=session.getAttribute("username")%>;
      if(login === null)
      alert("you have to login first !!");
  else
  {
	$.ajax({
		type:'POST', 
		url: 'ws',
                data: {type: "order", username: username, isbn: isbn},
		
		
	  
         	success: function(response){ 
		 alert(response);
                 location='index.jsp';
		
		} //End success function
		
		, error: function(errors) {
		alert("عذرا حصل خطأ, الرجاء التأكد من اتصالك بالانترنت");

		}
		});
  }//else
      
  
 }
        
        </script>
   
  </body>

</html>

