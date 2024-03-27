

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String query="insert into bookshop values(?,?,?)";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		String bookName=req.getParameter("book_name");
		String bookEdition=req.getParameter("book_edition");
		float bookPrice=Float.parseFloat(req.getParameter("book_price"));
		
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","Amk@151198");
			PreparedStatement ps=con.prepareStatement(query);)
		{
			ps.setString(1, bookName);
			ps.setString(2,bookEdition);
			ps.setFloat(3, bookPrice);
			
			int count=ps.executeUpdate();
			
			 if (count != 0) {
		            // Registration successful, add JavaScript popup
		            pw.println("<script>");
		            pw.println("alert('Record is registered successfully');");
		            pw.println("window.location.href='home.html';");
		            pw.println("</script>");

		            // Redirect to another page or perform additional actions if needed
		            // For example, you can redirect to a confirmation page
		            // res.sendRedirect("confirmation.html");
		        } else {
		            pw.println("<script>");
		            pw.println("alert('Record not registered successfully');");
		            pw.println("window.location.href='home.html';");
		            pw.println("</script>");
		        }
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			pw.println("<h1>"+se.getMessage()+"</h1>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		pw.println("<a href='home.html'>Home</a>");
		pw.println("<br>");
		pw.println("<a href='BookList'>Book List </a>");
		
		
		
	}

}
