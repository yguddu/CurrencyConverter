import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONObject;
import bean.CurrencyBean;
public class ParseData {
	public List<CurrencyBean> parseData(JSONObject jobj) {
		 Calendar calendar = Calendar.getInstance();
		 java.sql.Timestamp date = new java.sql.Timestamp(calendar.getTime().getTime());
		 JSONObject jObject = (JSONObject)jobj.get("rates");
		 Set<String> key = jObject.keySet();
		 List <CurrencyBean> currencyExchange=new ArrayList<>();
		 for(String s:key)
			{
			 CurrencyBean bean=new CurrencyBean();
				String currencyName = s;
				String currencyValue = String.valueOf(jObject.get(s));
				bean.setBaseCurrency("USD");
				bean.setCurrencyName(currencyName);
				bean.setCurrencyValue(currencyValue);
				bean.setDate(date);
				currencyExchange.add(bean);
			}
		return currencyExchange;
	}
	
}
