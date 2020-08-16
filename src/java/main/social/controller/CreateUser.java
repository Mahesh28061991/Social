package social.controller;

import social.db.UserDBUtil;
import social.model.User;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUser() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    @Resource(name="jdbc/social")
    private DataSource datasource;
    private UserDBUtil userdb;
    

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
//			userdb = new UserDBUtil(datasource);
			userdb = new UserDBUtil();

		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User tempUser = new User(fname,lname,email,password);
		
		boolean created = tempUser.createUser(userdb);
		
		if(created) {
			String result = "SignUp Success..";
			request.setAttribute("result",result);
			String strViewPage="/index.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(strViewPage);
			dispatcher.forward(request, response);
		}
		else {
			String result = "SignUp Failed..";
			request.setAttribute("result",result);
			String strViewPage="/index.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(strViewPage);
			dispatcher.forward(request, response);
		
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

