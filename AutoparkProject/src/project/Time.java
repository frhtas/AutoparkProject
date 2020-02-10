package project;

import java.util.Calendar;

public class Time {
	private int hour, minute;
	
	public Time() {
		
	}

	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}
	
	public int getDifference(Time other) {
		int minutes1 = hour*60 + minute;
		int minutes2 = other.hour*60 + other.minute;
		int difference = minutes2 - minutes1;
		
		return difference;
	}
	
	
	public static Time getTime() {
		java.util.Date d = new java.util.Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
		
		Time time = new Time(hour, minute);
		
		return time;
	}

	
	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}
	
}
