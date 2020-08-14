package java.main.social.controller;

import java.main.social.db.UserDBUtil;
import java.main.social.model.User;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/LoginUser")
public class LoginUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public LoginUser() {
        super();
    }

	
	@Resource(name="jdbc/java.main.social")
    private DataSource datasource;
    private UserDBUtil userdb;

    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
    	super.init();
		
		try {
			
			userdb = new UserDBUtil(datasource);
		
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String logemail = request.getParameter("logemail");
		String logpassword = request.getParameter("logpassword");
		
		User tempUser = new User("","",logemail,logpassword);
		
		User returnedUser = new User();
		
		returnedUser = tempUser.loginUser(userdb);
		
		if(returnedUser!=null) {
			if(logpassword.contentEquals(returnedUser.getPassword())) {
				session.setAttribute("user", returnedUser);
				response.sendRedirect("loginSuccess.jsp");
			}	
			else {
				String result = "Invalid Username or Password!!";
				request.setAttribute("result",result);
			    String strViewPage="/index.jsp";
			    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(strViewPage);
			    dispatcher.forward(request, response);
			}
		}
		else {
			String result = "Invalid Username or Password!!";
			request.setAttribute("result",result);
		    String strViewPage="/index.jsp";
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(strViewPage);
		    dispatcher.forward(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}