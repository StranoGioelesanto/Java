package it.lipari.hotel;

public class SuiteRoom extends Room {
	

	boolean hasPool = Math.random() < 0.5 ? true : false;
	boolean hasCameraService = Math.random() < 0.5 ? true : false;

	public void init() {
		this.mq = 25;
		this.hasBalcony = true;
		this.hasPrivateBathroom = true;
		this.capacity = 4;
	}
	
	public SuiteRoom() {
		super();
		init();
	}
	
	public SuiteRoom(int number) {
		super(number);
		init();
	}

	public boolean isHasPool() {
		return hasPool;
	}

	public void setHasPool(boolean hasPool) {
		this.hasPool = hasPool;
	}

	public boolean isHasCameraService() {
		return hasCameraService;
	}

	public void setHasCameraService(boolean hasCameraService) {
		this.hasCameraService = hasCameraService;
	}

	@Override
	public void setRandomFeatures() {
		this.hasBalcony = true;
		this.hasPrivateBathroom = true;
		this.hasCameraService = true;
		float mq = Math.random() < 0.5 ? 25 : 30;
		this.mq = mq;
		if (mq == 25) {
			int capacity = Math.random() < 0.5 ? 3 : 4;
			this.capacity = capacity;
			this.hasPool = false;
		} else {
			int capacity = Math.random() < 0.5 ? 5 : 6;
			this.capacity = capacity;
			this.hasPool = true;
		}
	}

	
	
}
