package project;
import java.util.*;

public class Date {
	private int day, month, year;
	
	public Date() {
		
	}

	public Date(int day, int month, int year) {
		if (day < 31)
			this.day = day;
		if (month < 12)
			this.month = month;
		this.year = year;
	}
	
	public boolean isAfterThan(Date other) {
		if (other.year < year)
			return true;
		else if (other.year == year & other.month < month)
			return true;
		else if (other.year == year & other.month == month & other.day < day)
			return true;
		
		return false;
	}
	
	public boolean isBeforeThan(Date other) {
		if (other.year > year)
			return true;
		else if (other.year == year & other.month > month)
			return true;
		else if (other.year == year & other.month == month & other.day > day)
			return true;
		
		return false;
	}
	
	public boolean isEqualsWith(Date other) {
		if (other.year == year & other.month == month & other.day == day)
			return true;
		return false;
	}
	
	public static Date getToday() {
		java.util.Date d = new java.util.Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
		
		Date today = new Date(day, month+1, year);
		
		return today;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
	@Override
	public String toString() {
		return day + "/" + month + "/" + year;
	}
}
