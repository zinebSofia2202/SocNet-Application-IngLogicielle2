package socnet.com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import socnet.com.entities.Comment;
import socnet.com.entities.Post;
import socnet.com.entities.User;
import socnet.com.metier.CommentManager;
import socnet.com.metier.ICommentMetier;
import socnet.com.metier.PostManager;

@WebServlet("/AddComment")
public class CommentControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ICommentMetier commentMetier = new CommentManager();

    @Override
   	protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            String content = request.getParameter("commentContent");
            Long postId = Long.parseLong(request.getParameter("postId"));
            User user = (User) request.getSession().getAttribute("ut");

            if (user != null && content != null && !content.trim().isEmpty()) {
                PostManager pm = new PostManager();
                Post post = pm.findPost(postId);
                Comment comment = new Comment(content.trim(), user, post);
                commentMetier.addComment(comment);
            }

            response.sendRedirect("accueil.jsp"); 
        }
    }