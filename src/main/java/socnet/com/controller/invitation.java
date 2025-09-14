package socnet.com.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import socnet.com.dao.InvitationDao;
import socnet.com.metier.UserManager;

/**
 * Servlet implementation class invitation
 */
public class invitation extends HttpServlet {
	private static final long serialVersionUID = 1L;
     UserManager um;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public invitation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		um=new UserManager();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int ids=Integer.parseInt(request.getParameter("ids"));
		int idr=Integer.parseInt(request.getParameter("idr"));
		String op = request.getParameter("op");
		if (op.equals("inviter")) {
			um.inviter(ids, idr);
		}
		else if (op.equals("accepter")) {
			um.accepter(ids, idr);
		}
		
		response.sendRedirect("accueil.jsp");
		
	}

}
