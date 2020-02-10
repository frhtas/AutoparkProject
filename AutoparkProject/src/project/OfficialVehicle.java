package project;

public class OfficialVehicle implements Vehicle {
	private String plate;

	public OfficialVehicle(String plate) {
		this.plate = plate;
	}

	@Override
	public String getPlate() {
		return plate;
	}

	@Override
	public Subscription getSubscription() {
		return null;
	}

	@Override
	public boolean isSpecial() {
		return false;
	}

}
