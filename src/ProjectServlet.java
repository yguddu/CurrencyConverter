import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import bean.CurrencyBean;
@WebServlet("/ProjectServlet")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out=response.getWriter();  
        String action=request.getParameter("action");
        if("Refresh".equals(action)){   	
        	JSONObject jobj;
        	jobj=ApiCall.call();
        	ParseData parse=new ParseData();
        	List<CurrencyBean> parseCurrencyData = parse.parseData(jobj);
        	StoreAndFetchData.storeData(parseCurrencyData);
        	response.sendRedirect("/CurrencyConverter/index.jsp");
        }
        else if("convert".equals(action)){
        	Manager valid= new Manager();
        	String resultValue;
        	resultValue = valid.validate(request,response);
        	if(resultValue == null){
        		out.print("<center><h2><font color=\"white\">!..Enter a valid amount..!</font></h2></center>"); 
          	    RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");  
          	    rd.include(request, response);     		
        	}
        	else{
        		request.setAttribute("conversionResult", resultValue);
			    RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");  
		        rd.forward(request, response); 
        	}
        	}
        
        }
}

