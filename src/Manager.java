import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.CurrencyBean;
import bean.TableBean;
public class Manager {
	public static Map<String, String> mapElements= new HashMap<String, String>();
	public String validate(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		String baseCurrency=request.getParameter("currency1");
    	String convertCurrency =request.getParameter("currency2");
    	String amount=request.getParameter("amount1");
    	String resultValue=null;
    	if(!amount.isEmpty() && amount.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+"))
    	{
  		   double value=Double.parseDouble(amount);
    	   if(mapElements.size()==0)
    	     {
    	    	 StoreAndFetchData f=new StoreAndFetchData();
    	    	 mapElements =(Map<String, String>) f.fetchData();
    	     } 	 
    	double baseValue =Double.parseDouble((mapElements.get(baseCurrency)));	
    	double convertValue =Double.parseDouble((mapElements.get(convertCurrency)));
		double dollarValue = value/baseValue;
		resultValue = String.valueOf(dollarValue*convertValue);
    	}
    	return resultValue;
	}
	
	public  ArrayList<TableBean> validateHistoryServlet(HttpServletRequest request,HttpServletResponse response)throws IOException, ParseException{
		String fromCurrency=request.getParameter("currency1");
		String toCurrency=request.getParameter("currency2");
		String from_date=request.getParameter("fromdate");
		String to_date=request.getParameter("todate");
		from_date=from_date.concat(" "+"00:00:00");
		to_date=to_date.concat(" "+"23:59:59");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date dateFrom =(Date) dateFormat.parse(from_date);
		Date dateTo =(Date) dateFormat.parse(to_date);
		ArrayList<TableBean> table=new ArrayList<TableBean>();
		if(dateTo.compareTo(dateFrom)>0)
			{
	    	StoreAndFetchData fetchHistory=new StoreAndFetchData();
	    	List<CurrencyBean> list=(List<CurrencyBean>) fetchHistory.fetchHistoryData(dateFrom, dateTo, fromCurrency, toCurrency);
	    	if(!fromCurrency.equals(toCurrency))
	    	{
	    		Iterator<CurrencyBean> iterator=list.iterator();
		        while(iterator.hasNext())
				    {  
				        CurrencyBean currency1=iterator.next(); 
				        Timestamp date=currency1.getDate();
				        String dateString=date.toString();
				        String curr=currency1.getCurrencyName();
						double baseValue=0.0;
						double convertValue=0.0;
				        if(curr.equals(fromCurrency))
				        {
				        	baseValue =Double.parseDouble((currency1.getCurrencyValue()));
				        }
				        else if(curr.equals(toCurrency))
				        {
				             convertValue =Double.parseDouble((currency1.getCurrencyValue()));
				        }
				        else
				        {
							return table;
				        }
				        CurrencyBean currency2=iterator.next();
				        String curr1=currency2.getCurrencyName();
				    	if(curr1.equals(fromCurrency)){
				        	 baseValue =Double.parseDouble((currency2.getCurrencyValue()));
				        }
				        else if(curr1.equals(toCurrency)){
				        	 convertValue =Double.parseDouble((currency2.getCurrencyValue()));
				        }
				        else{
							return table;
				       }
						double dollarValue = 1/baseValue;
						String resultValue = String.valueOf(dollarValue*convertValue);
						table.add(new TableBean(dateString,fromCurrency,toCurrency,resultValue));
				    }
			    }
	    	else{
			    	Iterator<CurrencyBean> iterator=list.iterator();
			        while(iterator.hasNext())
					    {  
					        CurrencyBean currency1=iterator.next(); 
					        Timestamp date=currency1.getDate();
					        String dateString=date.toString();
					        String resultValue="1.0";
					        table.add(new TableBean(dateString,fromCurrency,toCurrency,resultValue));
					    }
			    }
	    	}
		return table;
		}	
}
