package Malmo;

public abstract class Worker {
	
	private String name;
	private String id;
	private String address;

	public Worker(String name, String id, String address) {
		this.name =name;
		this.id = id;
		this.address = address;
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public String getID() {
		return this.id;
	}
	
	public String getAddress() {
		return this.address;
	}

}
