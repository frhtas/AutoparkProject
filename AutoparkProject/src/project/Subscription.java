package project;

public class Subscription {
	private Date begin, end;
	private String plate;
	
	public Subscription(Date begin, Date end, String plate) {
		this.begin = begin;
		this.end = end;
		this.plate = plate;
	}
	
	public boolean isValid() {
		if (end.isBeforeThan(end.getToday()) || begin.isAfterThan(begin.getToday())) {
			return true;
		}
		return false;
	}
	
	
	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
	
	
	@Override
	public String toString() {
		return "Plate: " + plate + "           Begin Date: " + begin.toString() + "      End Date: " + end.toString();
	}

}
