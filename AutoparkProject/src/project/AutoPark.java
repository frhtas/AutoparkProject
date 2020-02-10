package project;

import java.util.*;

public class AutoPark {
	private ArrayList<SubscribedVehicle> subscribedVehicles;
	private ArrayList<ParkRecord> parkRecords;
	private Vector<Vehicle> vehicles;
	private double hourlyFee, incomeDaily = 0;
	private int capacity;
	
	public AutoPark(double hourlyFee, int capacity) {
		this.hourlyFee = hourlyFee;
		this.capacity = capacity;
		
		subscribedVehicles = new ArrayList<SubscribedVehicle>();
		parkRecords = new ArrayList<ParkRecord>();
		vehicles = new Vector<Vehicle>();
	}
	
	public SubscribedVehicle searchVehicle(String plate) {
		for (SubscribedVehicle subVehicle : subscribedVehicles) {
			if (subVehicle.getPlate().equals(plate)) {
				return subVehicle;
			}
		}
		return null;
	}
	
	public boolean isParked(String plate) {
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getPlate().equals(plate)) {
				return true;
			}		
		}
		return false;
	}
	
	private void enlargeVehicleArray() {
		vehicles.ensureCapacity(capacity);
	}
	
	public boolean addVehicle(SubscribedVehicle subVehicle) {
		if (searchVehicle(subVehicle.getPlate()) == null) {
			subscribedVehicles.add(subVehicle);
			return true;
		}
		return false;
	}
	
	public boolean vehicleEnters(String plate, Time enter, boolean isOfficial) {
		if (!isParked(plate)) {
			if (searchVehicle(plate) != null) {
				vehicles.add(searchVehicle(plate));
				enlargeVehicleArray();
				ParkRecord p = new ParkRecord(enter, null, searchVehicle(plate));
				parkRecords.add(p);
				return true;
			}
			else if (isOfficial) {
				OfficialVehicle official = new OfficialVehicle(plate);
				ParkRecord p = new ParkRecord(enter, null, official);
				parkRecords.add(p);
				return true;
			}
			else {
				RegularVehicle regular = new RegularVehicle(plate);
				vehicles.add(regular);
				enlargeVehicleArray();
				ParkRecord p = new ParkRecord(enter, null, regular);
				parkRecords.add(p);
				return true;
			}
		}
		return false;
	}
	
	public boolean vehicleExits(String plate, Time exit) {
		if (isParked(plate)) {
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getPlate().equals(plate)) {
					for (ParkRecord p : parkRecords) {
						if (p.getVehicle().equals(vehicle)) {
							p.setExitTime(exit);
							if (vehicle.isSpecial() && (p.getParkingDuration() < 55)) {
								vehicles.remove(vehicle);
								return true;
							}
							else if (vehicle.isSpecial() && (p.getParkingDuration() > 55) && (p.getParkingDuration() < 95)) {
								double parkingFee = p.getParkingDuration()*(hourlyFee/60.0);
								incomeDaily += parkingFee;
								vehicles.remove(vehicle);
								return true;
							}
							vehicles.remove(vehicle);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String info = "Hourly Fee: " + hourlyFee + "\tCapacity: "   + capacity;
		return info;
	}
	
	
	
	public Vector<Vehicle> showParkedVehicles() {
		return vehicles;
	}
	
	
	public ArrayList<SubscribedVehicle> showSubscribedVehicles() {
		return subscribedVehicles;
	}

	public double getHourlyFee() {
		return hourlyFee;
	}

	public void setHourlyFee(double hourlyFee) {
		this.hourlyFee = hourlyFee;
	}

	public double getIncomeDaily() {
		return incomeDaily;
	}

	public void setIncomeDaily(double incomeDaily) {
		this.incomeDaily = incomeDaily;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	
	

}
