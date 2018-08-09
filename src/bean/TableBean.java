package bean;
public class TableBean {
	private String result_value;
	private String from_Currency;
	private String to_currency;
	private String date;
	public TableBean(String date, String from_currency, String to_currency, String result_value) {
		this.result_value=result_value;
		this.from_Currency=from_currency;
		this.to_currency=to_currency;
		this.date=date;
	}
	public String getResult_value() {
		return result_value;
	}
	public void setResult_value(String result_value) {
		this.result_value = result_value;
	}
	public String getFrom_Currency() {
		return from_Currency;
	}
	public void setFrom_Currency(String from_Currency) {
		this.from_Currency = from_Currency;
	}
	public String getTo_currency() {
		return to_currency;
	}
	public void setTo_currency(String to_currency) {
		this.to_currency = to_currency;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
