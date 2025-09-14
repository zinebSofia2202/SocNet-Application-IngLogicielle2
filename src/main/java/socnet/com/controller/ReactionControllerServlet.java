package socnet.com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

// You're mixing javax.servlet with jakarta.servlet. Use jakarta consistently.
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
// Removed redundant import: import java.time.LocalDate;
// Removed unnecessary import for servlet: import jakarta.persistence.Enumerated;

import com.google.gson.Gson; // You'll need Gson library for JSON serialization

import socnet.com.dao.PostDao;
import socnet.com.dao.ReactionDao;
import socnet.com.entities.Post;
import socnet.com.entities.Reaction;
import socnet.com.entities.ReactionType; // Correct import for your enum
import socnet.com.entities.User;

@WebServlet(urlPatterns = {"/reactions", "/getPostReactions", "/reactToPost"})
public class ReactionControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PostDao postDAO;
    private ReactionDao reactionDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        postDAO = new PostDao(); // Corrected to use PostDao (consistent naming)
        reactionDAO = new ReactionDao(); // Corrected to use ReactionDao (consistent naming)
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // This method will handle /getPostReactions requests

        long postId = Long.parseLong(request.getParameter("postId"));

        Map<String, Long> reactionCounts = reactionDAO.getReactionCountsForPost(postId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        out.print(gson.toJson(reactionCounts));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // This method will handle /reactToPost requests

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("ut");
        
        System.out.println("Current user in session: " + currentUser);
        
        if (currentUser == null) {
        	System.out.println("User is NOT logged in. Sending 401.");
            // For AJAX requests, it's better to send an unauthorized status code
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in.");
            return;
        }

        long postId = Long.parseLong(request.getParameter("postId"));
        String reactionTypeString = request.getParameter("reactionType"); // Get as String from request

        ReactionType reactionTypeEnum;
        try {
            // Convert string to enum. Ensure the string matches the enum constant names (e.g., "LIKE", "DISLIKE").
            reactionTypeEnum = ReactionType.valueOf(reactionTypeString.toUpperCase());
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid reaction type: " + reactionTypeString);
            return;
        }


        Post post = postDAO.findById(postId); // Assuming findById works

        if (post != null) {
            Reaction existingReaction = reactionDAO.getReactionByUserAndPost(currentUser, post);

            if (existingReaction != null) {
                // IMPORTANT: Use getReactionType() and setReactionType() consistent with your Reaction entity
                if (existingReaction.getReactionType().equals(reactionTypeEnum)) { // Compare enum instances
                    reactionDAO.deleteReaction(existingReaction);
                } else {
                    existingReaction.setReactionType(reactionTypeEnum); // Set enum instance
                    reactionDAO.updateReaction(existingReaction);
                }
            } else {
                // Constructor of Reaction: Reaction(User user, Post post, ReactionType reactionType)
                // You passed LocalDate.now() which is fine if your Reaction constructor takes it as the first arg.
                // However, based on the corrected Reaction entity, the constructor takes User, Post, ReactionType.
                // And LocalDate.now() is already default in Reaction entity.
                Reaction newReaction = new Reaction(currentUser, post, reactionTypeEnum);
                reactionDAO.addReaction(newReaction);
            }
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Post not found.");
        }
    }
}