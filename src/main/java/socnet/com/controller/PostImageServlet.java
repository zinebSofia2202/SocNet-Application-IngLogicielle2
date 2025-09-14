package socnet.com.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import socnet.com.metier.PostManager;
import socnet.com.metier.UserManager;

/**
 * Servlet implementation class PostImageServlet
 */
@WebServlet("/getPostImage")
public class PostImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostManager pm;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		pm=new PostManager();
	}

    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postId = request.getParameter("id");
        if (postId != null) {
            long pid = Long.parseLong(postId);
            byte[] imageBytes = pm.PostImage(pid);
            if (imageBytes != null) {
                response.setContentType("image/jpeg");
                response.setContentLength(imageBytes.length);
                response.getOutputStream().write(imageBytes);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
