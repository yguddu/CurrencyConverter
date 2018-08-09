import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.TableBean;
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Manager validate=new Manager();
		ArrayList<TableBean> table;
		try {
			table = validate.validateHistoryServlet(request, response);
			if(table.isEmpty()){
				out.print("<center><h2>!..Enter a valid date..!</h2></center>"); 
	    	    RequestDispatcher rd=request.getRequestDispatcher("/history.jsp");  
	    	    rd.include(request, response);	
			}
			else{
			  	request.setAttribute("TableArrayList",table);
				RequestDispatcher rd=request.getRequestDispatcher("/history.jsp");  
			    rd.forward(request, response); 
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	}	
}