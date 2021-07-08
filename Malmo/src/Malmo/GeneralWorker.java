package Malmo;

public class GeneralWorker extends Worker {	
	
	private boolean isIntern;
	
	//Constructor 
	public GeneralWorker(String name, String id, String address,boolean isIntern) {
		super(name,id,address);
		this.isIntern=isIntern;
	}
	
	public boolean getIsIntern() {
		return this.isIntern;
	}
}
