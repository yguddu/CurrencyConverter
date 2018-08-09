import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import bean.CurrencyBean;

public class StoreAndFetchData {
	
	public static void storeData(List parseCurrencyData ) {
		 Session session=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory().openSession(); 
		 Transaction t=session.beginTransaction();
		 List<CurrencyBean> list=parseCurrencyData;
		 Iterator<CurrencyBean> iterator=list.iterator();
		 while(iterator.hasNext()){
			 CurrencyBean data=iterator.next();
			 session.save(data);
		 }
		 t.commit(); 
		 session.close();
		 System.out.println("data saved in database");
	}
	public Map<String, String> fetchData() {
		Map<String, String> map=new HashMap<String, String>();
	    Session session=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();  	
	    Query query=session.createQuery("from CurrencyBean order by id desc").setMaxResults(170);
	    List<CurrencyBean> queryList=query.list(); 
	    System.out.println("list size="+queryList.size());
	    Iterator<CurrencyBean> iterator=queryList.iterator();  
	    while(iterator.hasNext())
	    {  
		    CurrencyBean currencyData=iterator.next(); 
		    String currencyName=currencyData.getCurrencyName();
		    String currencyValue=currencyData.getCurrencyValue();
		    map.put(currencyName, currencyValue);    
	    }
		session.close();
		 return map;
	}
	public List<CurrencyBean> fetchHistoryData(Date from_date,Date to_date,String from_currency,String to_currency) throws ParseException 
	{  
	    Session session=new Configuration().configure("hibernate.cfg.xml")  
	                        .buildSessionFactory().openSession();  
	    Date dateFrom=from_date;
	    Date dateTo=to_date;
		String fromCurr=from_currency;
		String toCurr=to_currency;    
		Query query=session.createQuery("from CurrencyBean where date between :dateFrom and :dateTo and currencyName in (:fromCurr,:toCurr) order by date");  
		query.setParameter("dateFrom", dateFrom);
		query.setParameter("dateTo", dateTo);	
		query.setParameter("fromCurr", fromCurr);
		query.setParameter("toCurr", toCurr);
		List<CurrencyBean> list=query.list();
		System.out.println("list created in history of size="+list.size());
		session.close();
		return list;
	}
	
}
	

