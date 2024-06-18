package addroleserlet.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import addroleservlet.antity.RolesAntity;
import addroleservlet.config.MySqlConfig;
@WebServlet (name = "addroles",urlPatterns = "/add-role")
public class AddRoleController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.getRequestDispatcher("role-add.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	try {
    		req.setCharacterEncoding("UTF-8");
        	String description = req.getParameter("roles");
        	String name = req.getParameter("description");
        	RolesAntity rolesAntity = new RolesAntity(0, description, name);
        	String sql = "INSERT roles(description,name) VALUES (?,?)";
        	Connection connection = MySqlConfig.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	preparedStatement.setString(1, rolesAntity.getDescription());
        	preparedStatement.setString(2, rolesAntity.getName());
        	preparedStatement.executeUpdate();
        	connection.close();
		} catch (Exception e) {
			
		}
    	
    	
    }
}
