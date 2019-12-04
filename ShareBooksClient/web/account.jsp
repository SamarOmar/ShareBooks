<%-- 
    Document   : account
    Created on : Dec 9, 2017, 1:27:28 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <% 
                    if(session.getAttribute("login") == null )
                      response.sendRedirect("index.jsp");
                  %>
  

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
              <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger " href="index.jsp">Home</a>
            </li>
            <li class="nav-item mx-0 mx-lg-1">
              <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger " href="#booklist">My Book List</a>
            </li>
            		<li class="nav-item mx-0 mx-lg-1">
              <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="#add">Add Book</a>
            </li>
            <!-- Order list -->
	    <li class="nav-item mx-0 mx-lg-1">
              <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="#myOrder">Orders' List</a>
            </li>	
          
 
            <li class='nav-item mx-0 mx-lg-1'> <a class='nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger ' href='logout.jsp'>Log out </a></li> 
            
           

			
             
          </ul>
        </div>
      </div>
    </nav>

    <br><br><br>

    <!-- Portfolio Grid Section -->
    <section class="portfolio" id="booklist">
      <div class="container">
        <h2 class="text-center text-uppercase text-secondary mb-0">My Book List</h2>
     
        <hr class="star-dark mb-5">
        <div id="myList" class="row">

			   
		
		
       
     
	 </div>
    </section>
    
    
    
    	
	<!-- About Section -->
    <section style="background-color:#2C3E50 " id="add">
      <div style="background-color:#2C3E50 " class="container">
        <h2  class="text-center text-uppercase text-white">Add New Book</h2>
        <hr class="star-light mb-5">
        <div  class="row">
          
		    <div class="col-lg-8 mx-auto">
            <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
            <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
            <form action="ws" method="post" novalidate="novalidate">
                <input type="hidden" name="type" value="addbook">
                <input type="hidden" name="username" value="<% out.print(session.getAttribute("username") );  %>">
			  <div class="control-group">
                <div class="form-group floating-label-form-group controls mb-0 pb-2">
                  <label>ISBN</label>
                  <input class="form-control" name="isbn" rows="5" type="number" placeholder="ISBN" required="required" data-validation-required-message="Please enter book ISBN .">
                  <p class="help-block text-danger"></p>
                </div>
              </div>

			 <div class="control-group">
                <div class="form-group floating-label-form-group controls mb-0 pb-2">
                  <label>Title</label>
                  <input class="form-control" name="title" type="text" placeholder="Title" required="required" data-validation-required-message="Please enter book Title.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
              <div class="control-group">
                <div class="form-group floating-label-form-group controls mb-0 pb-2">
                  <label>Author</label>
                  <input class="form-control" name="author" type="text" placeholder="Author" required="required" data-validation-required-message="Please enter Book author.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
           
                 <div class="control-group">
                <div class="form-group floating-label-form-group controls mb-0 pb-2">
                  <label>Publisher Name</label>
                  <input class="form-control" name="publisher" type="text" placeholder="Publisher Name" required="required" data-validation-required-message="Please enter Book Publisher Name.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
		   
                  <div class="control-group">
                <div class="form-group floating-label-form-group controls mb-0 pb-2">
                  <label>Publisher Year</label>
                  <input class="form-control" name="year" type="number" placeholder="Publisher Year" required="required" data-validation-required-message="Please enter Book Publisher Year.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
		
                 <div class="control-group">
                <div class="form-group floating-label-form-group controls mb-0 pb-2">
                  <label>Category</label>
                  <input class="form-control" name="category" type="text" placeholder="Category" required="required" data-validation-required-message="Please enter Book Category.">
                  <p class="help-block text-danger"></p>
                </div>
              </div>
              <div class="control-group">
                <div class="form-group floating-label-form-group controls mb-0 pb-2">
                  <label>Description</label>
                  <textarea class="form-control" name="description" rows="5" placeholder="Description" required="required" data-validation-required-message="Please enter book Description ."></textarea>
                  <p class="help-block text-danger"></p>
                </div>
              </div>
			  
			
              <div id="success"></div>
              <div class="form-group"><br>
              <center>  <button type="submit" class="btn btn-primary btn-xl" id="sendMessageButton">Add</button>  </center>
              </div>
            </form>
          </div>
		
		
		
       
        </div>
       
      </div>
    </section>
    
    
	
    <section class="portfolio" id="orders">
      <div class="container">
        <h2 class="text-center text-uppercase text-secondary mb-0">Books' Order List</h2>
     
        <hr class="star-dark mb-5">
        <div id="myOrder" class="row">

		 <div class="col-md-6 col-lg-4">
		   <div class=" d-block mx-auto" href="">
              <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                
              </div>
			   <center> <div  style="height:258px; width:100%; background-color:#CCB2A3;" ><br>
			        <h6 style="color: #2C3E50" >Title<h6> <p> title here </p>
					<h6 style="color: #2C3E50" >Description<h6> <p> description here </p>
					<h6 style="color: #2C3E50" >Author<h6> <p> author here </p>
					<button style="height:10%; width:40%;" type="submit" class="btn btn-primary " id="">Complete</button>
			   </div></center> </div> </div>
			   
		  <div class="col-md-6 col-lg-4">
		   <div class=" d-block mx-auto" href="">
              <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                
              </div>
			   <center> <div  style="height:258px; width:100%; background-color:#CCB2A3;" ><br>
			        <h6 style="color: #2C3E50" >Title<h6> <p> title here </p>
					<h6 style="color: #2C3E50" >Description<h6> <p> description here </p>
					<h6 style="color: #2C3E50" >Author<h6> <p> author here </p>
					<button style="height:10%; width:40%;" type="submit" class="btn btn-primary " id="">Complete</button>
			   </div></center> </div> </div>
			   
		   <div class="col-md-6 col-lg-4">
		   <div class=" d-block mx-auto" href="">
              <div class="portfolio-item-caption d-flex position-absolute h-100 w-100">
                
              </div>
			   <center> <div  style="height:258px; width:100%; background-color:#CCB2A3;" ><br>
			        <h6 style="color: #2C3E50" >Title<h6> <p> title here </p>
					<h6 style="color: #2C3E50" >Description<h6> <p> description here </p>
					<h6 style="color: #2C3E50" >Author<h6> <p> author here </p>
					<button style="height:10%; width:40%;" type="submit" class="btn btn-primary " id="">Complete</button>
			   </div></center> </div> </div>
       
     
	 </div>
    </section>
	
	
	
	
	
	
	

    

	
	

	
	
	
	
	
	
	
	
	
	
	
	


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
        <small>Copyright &copy; Book Sharing 2017</small>
      </div>
    </div>

    <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
    <div class="scroll-to-top d-lg-none position-fixed ">
      <a class="js-scroll-trigger d-block text-center text-white rounded" href="#page-top">
        <i class="fa fa-chevron-up"></i>
      </a>
    </div>

    <!-- Portfolio Modals -->

   
  


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
            var username = "<%=session.getAttribute("username")%>";
         //   alert(username);
        	$.ajax({
		type:'POST', 
		url: 'ws',
                data: {type: "myList", assignto:username},
		
		
	  
                 success: function(response){ 
	    
	         $("#myList").html(response);
	    $.ajax({
		type:'POST', 
		url: 'ws',
                data: {type: "myorders", username:username},
		
		
	  
                 success: function(response){ 
	         $("#myOrder").html(response);
	
		} //End success function
		
		, error: function(errors) {
		alert("عذرا حصل خطأ, الرجاء التأكد من اتصالك بالانترنت");

		}
		}); //End AJAX	
		} //End success function
		
		, error: function(errors) {
		alert("عذرا حصل خطأ, الرجاء التأكد من اتصالك بالانترنت");

		}
		}); //End AJAX	
             
 });
      function updateorder(element)
      {
     var arr = $(element).attr("id").split("-");
     var orderid = arr[0];
     var user = arr[1];
     var isbn = arr[2];
     //
         $.ajax({
		type:'POST', 
		url: 'ws',
                data: {type: "updateorder", orderid:orderid, user:user , isbn:isbn},
		
		
	  
                 success: function(response){ 
                 alert(response);
	        // $("#myOrder").html(response);
	
        location='account.jsp';
		} //End success function
		
		, error: function(errors) {
		alert("عذرا حصل خطأ, الرجاء التأكد من اتصالك بالانترنت");

		}
		}); //End AJAX	
    
      }
        </script>
  </body>

</html>

