<%@ page import="java.util.*, socnet.com.entities.*, socnet.com.metier.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Long postId = Long.parseLong(request.getParameter("postId"));
    CommentManager cm = new CommentManager();
    List<Comment> comments = cm.getCommentsByPost(postId);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Comments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f3f4f6;
            font-family: 'Segoe UI', sans-serif;
        }
        .comment-container {
            background-color: white;
            border-radius: 10px;
            padding: 20px;
            margin-top: 50px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .comment {
            border-bottom: 1px solid #eee;
            padding: 10px 0;
        }
        .comment:last-child {
            border-bottom: none;
        }
        .comment strong {
            color: #4e73df;
        }
        .back-link {
            text-decoration: none;
            color: #1cc88a;
            font-weight: bold;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="comment-container">
            <h3 class="mb-4">Commentaires du post #<%= postId %></h3>

            <% if (comments != null && !comments.isEmpty()) { 
                for (Comment c : comments) {
            %>
                <div class="comment">
                    <strong><%= c.getUser().getNom() %></strong> <small class="text-muted">(<%= c.getDate() %>)</small><br/>
                    <%= c.getContent() %>
                </div>
            <% 
                } 
            } else { %>
                <p class="text-muted">Aucun commentaire pour ce post.</p>
            <% } %>

            <div class="mt-4">
                <a href="javascript:history.back()" class="back-link">← Retour au post</a>
            </div>
        </div>
    </div>
</body>
</html>
