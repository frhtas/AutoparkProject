package project;

public class SubscribedVehicle implements Vehicle {
	private Subscription subscription;

	public SubscribedVehicle(Subscription subscription) {
		this.subscription = subscription;
	}

	@Override
	public String getPlate() {
		return subscription.getPlate();
	}

	@Override
	public Subscription getSubscription() {
		return subscription;
	}

	@Override
	public boolean isSpecial() {
		return false;
	}
	
	
	
	

}
