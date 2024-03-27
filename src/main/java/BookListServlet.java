

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookListServlet
 */
@WebServlet("/BookList")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String query="select * from bookshop";
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
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
			ResultSet rs=ps.executeQuery();
			
			
			pw.println("<html>");
	        pw.println("<head>");
	        pw.println("<title>Book Display</title>");
	        pw.println("<style>");
	        pw.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; }");
	        pw.println(".container { max-width: 800px; margin: auto; padding: 20px; background-color: #fff; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);}");
	        pw.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
	        pw.println("th, td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #ddd; }");
	        pw.println("th { background-color:#089da1; color: white; }");
	        pw.println(".b2{background-color: #007bff;color: #fff;padding: 10px 20px;border: none;border-radius: 5px;cursor: pointer;}");
	        pw.println(".b2 a{text-decoration: none; color: white;}");
	        pw.println("</style>");
	        pw.println("</head>");
	        pw.println("<body>");

			
	        pw.println("<div class='container'>");
	        pw.println("<h2>Book List</h2>");
	        pw.println("<table>");
	        pw.println("<tr><th>Title</th><th>Author</th><th>Price</th></tr>");
			
			while(rs.next())
			{
				pw.println("<tr>");
				pw.println("<td>"+rs.getString(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getFloat(3)+"</td>");
				pw.println("</tr>");
			}
			pw.println("</div>");
			pw.println("</table>");
			pw.println("<br>");
			pw.println("<button class='b2' name='click'><a href='index.html'>Go to home</a></button>");
	        pw.println("</body>");
	        pw.println("</html>");
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		
		
	}

}
