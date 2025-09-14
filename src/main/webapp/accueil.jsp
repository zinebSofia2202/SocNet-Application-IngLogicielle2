<%@page import="socnet.com.dao.UserDao"%>
<%@page import="socnet.com.entities.Invitation"%>
<%@page import="socnet.com.entities.Post"%>
<%@ page import="socnet.com.entities.Comment" %>
<%@ page import="socnet.com.metier.CommentManager" %>
<%@ page import="socnet.com.entities.ReactionType" %>
<%@page import="socnet.com.metier.UserManager"%>
<%@page import="socnet.com.metier.PostManager"%>
<%@page import="java.util.List"%>
<%@page import="socnet.com.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<%
User u=(User)session.getAttribute("ut");
UserManager um=new UserManager();
List<User> us = um.allusers();

PostManager pm = new PostManager();
List<Post> pts = pm.allposts();
%>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Home</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/adminlte.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <style>
    body {
        background-color: #f3f4f6;
        font-family: 'Segoe UI', sans-serif;
    }
    .card {
        background-color: white;
        border-radius: 15px;
        box-shadow: 0 0 10px rgba(0,0,0,0.05);
        padding: 15px;
    }
    .card-header {
        background-color: #4e73df;
        color: white;
        border-top-left-radius: 15px;
        border-top-right-radius: 15px;
    }
    .btn-comment {
        background-color: #1cc88a;
        color: white;
    }
    .btn-comment:hover {
        background-color: #17a673;
    }
    .modal {
        display: none; 
        position: fixed;
        z-index: 1050;
        left: 0; top: 0;
        width: 100%; height: 100%;
        background-color: rgba(0,0,0,0.5);
    }
    .modal-content {
        background-color: white;
        margin: 10% auto;
        padding: 20px;
        border-radius: 10px;
        width: 40%;
    }
    .close-modal {
        float: right;
        font-size: 20px;
        cursor: pointer;
    }
     .modal {
      display: none;
      position: fixed;
      z-index: 1050;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0,0,0,0.5);
  }
  .modal-content {
      background-color: white;
      margin: 10% auto;
      padding: 20px;
      border-radius: 10px;
      width: 40%;
      position: relative;
  }
  .close-modal {
      position: absolute;
      right: 15px;
      top: 15px;
      cursor: pointer;
      font-size: 24px;
      font-weight: bold;
      color: #666;
  }
</style>
  
</head>
<body class="hold-transition sidebar-mini">
<% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <%= request.getAttribute("error") %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
<% } %>

<!-- Site wrapper -->
<div class="wrapper">
  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="index3.html" class="nav-link">Home</a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="#" class="nav-link">Contact</a>
      </li>
    </ul>

    <!-- Right navbar links -->

  </nav>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="#" class="brand-link">
      <img src="dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      <span class="brand-text font-weight-light">SocNet</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">
          <img src="dist/img/user2-160x160.jpg" class="img-circle elevation-2" alt="User Image">
        </div>
        <div class="info">
          <a href="#" class="d-block"><%=u.getNom() %></a>
        </div>
      </div>

      <!-- SidebarSearch Form -->
      <div class="form-inline">
        <div class="input-group" data-widget="sidebar-search">
          <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
          <div class="input-group-append">
            <button class="btn btn-sidebar">
              <i class="fas fa-search fa-fw"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
 
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Home</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Home</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="card">
        <div class="card-header">
          <h3 class="card-title">Welcome Dear User</h3>

          <div class="card-tools">
            <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
              <i class="fas fa-minus"></i>
            </button>
            <button type="button" class="btn btn-tool" data-card-widget="remove" title="Remove">
              <i class="fas fa-times"></i>
            </button>
          </div>
        </div>
        <div class="card-body">
          Connect with the world. Share your thoughts, your moments, your life  :)
        </div>
        <!-- /.card-body -->
        <div class="card-footer">
          Your world. Your connections.
        </div>
        <!-- /.card-footer-->
      </div>
      <!-- /.card -->

	<section class="d-flex justify-content-center align-items-start" style="padding:0px 15px">
		<div class="col-md-6" id="listOfFriends">
                <!-- USERS LIST -->
                <div class="card">
                  <div class="card-header">
                    <h3 class="card-title">Friends</h3>

                    <div class="card-tools">
                      <span class="badge badge-success">Your Friends</span>
                      <button type="button" class="btn btn-tool" data-card-widget="collapse">
                        <i class="fas fa-minus"></i>
                      </button>
                      <button type="button" class="btn btn-tool" data-card-widget="remove">
                        <i class="fas fa-times"></i>
                      </button>
                    </div>
                  </div>
                  <!-- /.card-header -->
                  <div class="card-body p-0">

                    <ul class="users-list clearfix">
                      <% 
                      	Boolean noFrinds = true;
                      
                      for(User ut:us){ 
                    	  if (ut.getId() != u.getId()) 
                    	  {
                    		  Invitation v =  (um.isInvite(u.getId(), ut.getId()));
                    		  if (v != null && v.isState())
                    		  { 
                    			  noFrinds = false;
                    	 
                    	  		%>
				                      <li>
				                        <img src="dist/img/user1-128x128.jpg" alt="User Image">
				                        <a class="users-list-name" href="#"><%=ut.getNom() %></a>
				                        <span class="users-list-date"><%=ut.getDate() %></span>
				                        <span>Amis</span>
			                         </li>
		                        <% 
		                      }
            
                      	} 
                      }
                      %>
         
                    </ul>
                                        
                    <% 
                    if (noFrinds) {
                    	
                    	%>
                    	<div   style="text-align:center; margin-bottom:15px">
                    		<span>No Friends Found!</span>
                    	</div>
                    	<% 
                    }
                    %>
                    <!-- /.users-list -->
                  </div>
                  <!-- /.card-body -->
                  <div class="card-footer text-center">
                    <a href="javascript:">View All Friends</a>
                  </div>
                  <!-- /.card-footer -->
                </div>
                <!--/.card -->
              </div>
              
              
        <div class="col-md-6">
                <!-- USERS LIST -->
                <div class="card">
                  <div class="card-header">
                    <h3 class="card-title">Latest Members</h3>

                    <div class="card-tools">
                      <span class="badge badge-danger">New Members</span>
                      <button type="button" class="btn btn-tool" data-card-widget="collapse">
                        <i class="fas fa-minus"></i>
                      </button>
                      <button type="button" class="btn btn-tool" data-card-widget="remove">
                        <i class="fas fa-times"></i>
                      </button>
                    </div>
                  </div>
                  <!-- /.card-header -->
                  <div class="card-body p-0">
                    <ul class="users-list clearfix">
                      <% 
                      for(User ut:us){ 
                    	  if (ut.getId() != u.getId()) {
                    	 
                    	  %>
                      <li>
                        <img src="dist/img/user1-128x128.jpg" alt="User Image">
                        <a class="users-list-name" href="#"><%=ut.getNom() %></a>
                        <span class="users-list-date"><%=ut.getDate() %></span>
                        
                        <% 
                        
                        Invitation v =  (um.isInvite(u.getId(), ut.getId()));
                        
                        if (v != null)
                        {
                        	if (v.isState())
                        	{
                        		%>
                        		<span>Amis</span>
                        		<%
                        	} 
                        	else 
                        	{ 
                        		if (v.getUrecieve().getId() == u.getId())
                        		{
                        			%>
                        			<a class="users-list-name" href="invitation?op=accepter&idr=<%=u.getId()%>&ids=<%=ut.getId()%>">Accepter</a>
                                	<%
                        		}
                        		else
                        		{
		                        	%>
		                        	<span>En Attente</span>
		                        	<%
                        		}
                        	}
                        }
                        else 
                        {
                        	%>
                        	<a class="users-list-name" href="invitation?op=inviter&ids=<%=u.getId()%>&idr=<%=ut.getId()%>">inviter</a>
                        	<%
                        }
                        %>
                        
                      </li>
                      <%
                      } 
                      }
                      %>
                    
                    </ul>
                    <!-- /.users-list -->
                  </div>
                  <!-- /.card-body -->
                  <div class="card-footer text-center">
                    <a href="javascript:">View All Users</a>
                  </div>
                  <!-- /.card-footer -->
                </div>
                <!--/.card -->
              </div>
    </section>
    
    <section>
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Posts List</h1>
          </div>
          <div class="col-sm-6">
            <ol class="newPostBtn float-sm-right">
	             <!-- New Post Button -->
				<button type="button" id="newPostBtn" class="btn btn-success">+ New Post</button>     
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section> 
    
    <section name="PostFormSection">
	    <div class="row justify-content-center" style="display:none;padding:0px 15px;margin:15px 0px" id="newPostForm">		
	    	<div class="col-md-12" style="border:3px solid #4e4187;border-radius:20px">
			<!-- Form Container (Initially hidden) --
			    <div class="card card-primary card-outline mb-4">
                  <!--begin::Header-->
                  <div class="card-header"><div class="card-title">New Post</div></div>
                  <!--end::Header-->
                  <!--begin::Form-->
                  <form method="post" action="PostContoller" enctype="multipart/form-data">
                    <!--begin::Body-->
                    <div class="card-body">
                      <div class="mb-3">
                        <label for="postTitle" class="label-postTitle">Title</label>
                        <input type="text" class="form-control" id="postTitle" name="postTitle">
                      </div>
                      <div class="mb-3">
                        <label for="postContent" class="label-postContent">Content</label>
                        <input type="postContent" class="form-control" id="postContent" name="postContent">
                      </div>
                      <div class="input-group mb-3">
                        <input type="file" class="form-control" id="postImage" name="postImage">
                        <label class="input-group-text" for="postImage" name="postImage">Upload Image</label>
                      </div>
                    </div>
                    <!--end::Body-->
                    <!--begin::Footer-->
                    <div class="text-center">
                      <button type="submit" class="btn btn-primary">Submit</button>
                      <button type="button" class="btn btn-info" id="cancelPostBtn">Cancel</button>
                    </div>
                    <!--end::Footer-->
                  </form>
                  <!--end::Form-->
                </div>
        </div>
    </section>  
    
       
    

<section>
    <div class="row justify-content-center" style="padding:0px 15px" name="PostsList">
        <div class="col-md-12 text-center">
            <%
                // Assuming 'pts' (List<Post>) is available in the request scope or similar
                // Example of how to retrieve it if passed from a servlet:
                // List<Post> pts = (List<Post>) request.getAttribute("postsList");
                // if (pts == null) {
                //    // Handle case where pts is null, e.g., initialize an empty list
                //    pts = new java.util.ArrayList<>();
                // }

                for(Post p : pts){
            %>
            <div class="card card-primary card-outline mb-4" width="80%">
                <div class="card-header">
                    <div class="card-title d-flex align-items-center">
                        <img src="getUserImage?id=<%= p.getUser().getId() %>" alt="User Image" class="img-size-50 mr-3 img-circle">
                        <h2 class="mb-0">
    <%= p.getUser().getNom() %> on <%= p.getDate() %>

    <% if (p.isViolent()) { %>
        <span class="badge bg-danger ms-2">Reported Content </span>
    <% } %>
</h2>

                    </div>
                </div>
                <form>
                    <div class="card-body">
                        <div class="mb-3">
                            <div id="postTitle" class="form-text" style="text-align: center">
                                <h3><%= p.getTitle() %></h3>
                            </div>
                        </div>
                        <div id="postContent" class="form-text text-left">
                            Content: <br/>
                            <%= p.getContent() %>
                        </div>
                        <% if (p.isViolent()) { %>
    <div class="alert alert-warning mt-2"> This content has been marked as potentially inappropriate!
    </div>
<% } %>
                        
                        <div>
                            <img src="getPostImage?id=<%= p.getId() %>" alt="Post Image" onerror="this.style.display='none';" width="30%"/>
                        </div>
                    </div>
                </form>
                <div class="card-footer d-flex justify-content-between align-items-center">
                    <div class="reaction-summary" id="reactionSummary-<%= p.getId() %>">
                        <%-- Initial display for reactions. JavaScript will update this. --%>
                        <span class="text-muted">Loading reactions...</span>
                    </div>

                    <div class="reaction-buttons">
                        <%
                            // Loop through ReactionType enum to dynamically generate buttons
                            // This ensures consistency with your Java enum
                            for (socnet.com.entities.ReactionType type : socnet.com.entities.ReactionType.values()) {
                        %>
                            <button type="button" class="btn btn-light reaction-btn"
							        data-post-id="<%= p.getId() %>"
							        data-reaction-type="<%= type.name() %>">
							    <i class="fa <%= type.getIconClass() %>"></i> <%= type.getDisplayName() %>
							</button>

                        <%
                            }
                        %>
                    </div>
                    <!-- Commentaires associés à ce post -->
<button type="button" class="btn btn-comment btn-sm open-comment-modal" data-post-id="<%= p.getId() %>">Commenter</button>

                    
                </div>
                </div>
            <%
                }
            %>
        </div>
    </div>
</section>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
    $(document).ready(function() {
        // Function to update reaction display for a specific post
        function updateReactionDisplay(postId) {
            $.ajax({
                url: 'reactions', // Points to your ReactionControllerServlet
                type: 'GET',
                data: { postId: postId },
                success: function(response) {
                    // 'response' will be a JSON object like: { "LIKE": 5, "DISLIKE": 2}
                    var reactionSummaryDiv = $('#reactionSummary-' + postId);
                    reactionSummaryDiv.empty(); // Clear existing counts

                    if (Object.keys(response).length === 0) {
                        reactionSummaryDiv.html('<span class="text-muted">No reactions yet.</span>');
                    } else {
                        // Iterate over all possible reaction types from JavaScript side
                        // This makes the client-side display resilient to changes in enum order
                        var reactionTypes = [
						    { name: "LIKE", icon: "fa-thumbs-up", label: "Like" },
						    { name: "DISLIKE", icon: "fa-thumbs-down", label: "Dislike" }
						];


                        reactionTypes.forEach(function(type) {
                            var count = response[type.name];
                            if (count) {
                                reactionSummaryDiv.append(
                                    '<span class="reaction-count me-2">' +
                                    '<i class="fa ' + type.icon + '"></i> ' + count +
                                    '</span>'
                                );
                            }
                        });

                    }
                },
                error: function(xhr, status, error) {
                    console.error("Error fetching reactions:", error);
                }
            });
        }

        // Initial load of reactions for all posts when the page loads
        $('.reaction-summary').each(function() {
            var postId = $(this).attr('id').replace('reactionSummary-', '');
            updateReactionDisplay(postId);
        });

        // Handle reaction button clicks
        $('.reaction-btn').on('click', function() {
            var postId = $(this).data('post-id');
            var reactionType = $(this).data('reaction-type'); // This will be the enum name string (e.g., "LIKE")

            $.ajax({
                url: 'reactions', // Points to your ReactionControllerServlet
                type: 'POST',
                data: {
                    postId: postId,
                    reactionType: reactionType // Send the enum name string
                },
                success: function(response) {
                    // On success, update the reaction display for this post
                    updateReactionDisplay(postId);
                },
                error: function(xhr, status, error) {
                    console.error("Error reacting to post:", error);
                    alert("Failed to react. Please try again."); // User feedback
                }
            });
        });
    });
</script>
   
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="float-right d-none d-sm-block">
      <b>Version</b> 3.2.0
    </div>
    <strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">SocNet.io</a>.</strong> All rights reserved.
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
<script>
document.getElementById("newPostBtn").addEventListener("click", function() {
    var form = document.getElementById("newPostForm");
    // Toggle visibility
    if (form.style.display === "none") {
        form.style.display = "block";
    } else {
        form.style.display = "none";
    }
});

document.getElementById("cancelPostBtn").addEventListener("click", function() {
    var form = document.getElementById("newPostForm");
    // Toggle visibility
    form.style.display = "none";

});

</script>
<!-- Popup Modal HTML -->
<div id="commentModal" class="modal">
    <div class="modal-content">
        <!-- Bouton pour fermer le modal -->
        <span class="close-modal">&times;</span>
        <h5>Ajouter un commentaire</h5>
        <form method="post" action="AddComment">
            <input type="hidden" id="comment-post-id" name="postId">
            <div class="form-group">
                <textarea name="commentContent" class="form-control" rows="4" placeholder="Votre commentaire..." required></textarea>
            </div>
            <button type="submit" class="btn btn-primary mt-2">Commenter</button>
        </form>
        <div class="text-end mt-3">
            <a href="#" id="viewAllCommentsLink" class="text-secondary">View all comments</a>
        </div>
    </div>
</div>

<!-- JavaScript/jQuery -->
<script>
$(document).ready(function() {
    // Afficher le popup avec les bons ID
    $('.open-comment-modal').on('click', function() {
        var postId = $(this).data('post-id');
        $('#comment-post-id').val(postId);
        $('#viewAllCommentsLink').attr('href', 'viewComments.jsp?postId=' + postId);
        $('#commentContent').val(''); // Réinitialise le champ texte
        $('#commentMsg').hide(); // Cache le message de succès
        $('#commentModal').fadeIn();
    });

    // Fermer le popup
    $('.close-modal').on('click', function() {
        $('#commentModal').fadeOut();
    });
    $(window).on('click', function(e) {
        if ($(e.target).is('#commentModal')) {
            $('#commentModal').fadeOut();
        }
    });

    // Soumettre le commentaire via AJAX
    $('#ajaxCommentForm').on('submit', function(e) {
        e.preventDefault(); // Empêche le rechargement

        var formData = {
            postId: $('#comment-post-id').val(),
            commentContent: $('#commentContent').val()
        };

        $.ajax({
            url: 'AddComment', // Ton servlet ou contrôleur
            type: 'POST',
            data: formData,
            success: function(response) {
                //  Affiche un message de confirmation
                $('#commentMsg').text("Commentaire ajouté !").fadeIn();

                //  Vide le champ commentaire
                $('#commentContent').val('');

                // Optionnel : tu peux ici recharger les commentaires via AJAX ou les ajouter manuellement
            },
            error: function(xhr, status, error) {
                alert("Erreur lors de l'ajout du commentaire.");
                console.error(error);
            }
        });
    });
});
</script>


</body>
</html>
    