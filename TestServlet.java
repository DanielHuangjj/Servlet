package test3.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kaaproject.kaa.client.DesktopKaaPlatformContext;
import org.kaaproject.kaa.client.Kaa;
import org.kaaproject.kaa.client.KaaClient;
import org.kaaproject.kaa.client.event.registration.UserAttachCallback;
import org.kaaproject.kaa.common.endpoint.gen.UserAttachResponse;

public class TestServlet extends HttpServlet {
	
	private static Connection conn;
	private static Statement command;
	private static ResultSet data;
	private static final String DataBase_URL="jdbc:mysql://localhost:3306/mysql";
	private static final String database_user="root";
	private static final String database_password="test";
	private static final String S_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	
	
	protected void doGet (HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{
		resp.getWriter().println(new Date());
		resp.getWriter().println("light: ");
		resp.getWriter().println("temperature: ");
	}
	protected void doPost (HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		/*
		if (req.getParameter("name")==null || req.getParameter("password")==null){
			getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);return;
		}
		*/
		String Name = req.getParameter("name");
		String Password = req.getParameter("password");
		//req.setAttribute("Hello ", Name);
		resp.getWriter().println("Hello "+Name+" !");
		//resp.getWriter().println("Hello "+Password+" !");
		
		
		resp.getWriter().println(new Date());
		resp.getWriter().println();
		//resp.getWriter().println("light: ");
		//resp.getWriter().println("temperature: ");
		
		
		
		
		boolean judge;

		judge = checkPassword(Name, Password);
			
		if(judge==true){
			resp.getWriter().println("present the data");
			resp.getWriter().println("light: ");
			resp.getWriter().println("temperature: ");
		}
		else{
			resp.getWriter().println("WRONG PASSWORD!");
		}

	}
	private boolean checkPassword(String name, String passwprd){
		// TODO Auto-generated method stub
		
		try{
			try {
				Class.forName(S_JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			conn = DriverManager.getConnection(DataBase_URL, database_user, database_password);
			command = conn.createStatement();
			//command.execute("select * from mysql.users;");
			data = command.executeQuery("select password from mysql.users;");
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try {
				if(data.first()){
					System.out.println(data.getString("password"));
					if (data.getString("password").equals(passwprd)) return true;
					while(data.next()){
						System.out.println(data.getString("password"));
						if (data.getString("password").equals(passwprd)) return true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		return false;
	}

}
