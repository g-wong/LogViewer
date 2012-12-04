package jp.co.geo.model;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class Date {
	private MessageFormat form;
	private String target;
	private Object[] obj;
	private ArrayList<String> message;
	private String defaultForm = "dd/a/yyyy:hh:mm:ss +T";
	
	String date;
	String month;
	String year;
	String hour;
	String minute;
	String second;
	String diff;
	
	public Date(String form, String target) {
		this.target = target;
		parse();
	}
	
	public Date(String target) {
		this.target = target;
		parse();
	}
	
	private void parse(){
		MessageFormat messageFormat = new MessageFormat("{0}/{1}/{2}:{3}:{4}:{5} +{6}");
		
		try {
			Object[] format = messageFormat.parse(defaultForm);
			obj = messageFormat.parse(target);
			
			for(int i = 0; i < format.length; i++) {
				String s = (String) format[i];
				String t = (String) obj[i];
				if(s.equals("dd")){
					date = t;
				} else if(s.equals("a")){
					month = t;
				} else if(s.equals("yyyy")){
					year = t;
				} else if(s.equals("hh")){
					hour = t;
				} else if(s.equals("mm")){
					minute = t;
				} else if(s.equals("ss")){
					second = t;
				} else if(s.equals("T")){
					diff = t;
				} else {
					System.err.println("日付がフォーマットに従っていません");
				}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getDate() {
		return date;
	}
	
	public String getMonth() {
		return month;
	}
	
	public String getYear() {
		return year;
	}
	
	public String getHour() {
		return hour;
	}
	
	public String getMinute() {
		return minute;
	}
	
	public String getSecond() {
		return second;
	}
	
	public String getDiff() {
		return diff;
	}
	
	public String get() {
		return year + "/" + month + "/" + date + " " + hour + ":" + minute + ":" + second;
	}
}
