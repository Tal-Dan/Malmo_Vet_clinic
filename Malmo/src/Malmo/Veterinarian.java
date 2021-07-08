package Malmo;

public class Veterinarian extends Worker {
	
	private String licenseNumber;
	
	public Veterinarian(String name, String id, String address)
	{
		super(name,id,address);
		this.licenseNumber=licenseNumber;
	}
	
	public String get_licenseNumber()
	{
		return licenseNumber;
	}
	
	public void set_licenseNumber(String licenseNumber)
	{
		this.licenseNumber=licenseNumber;
	}
	
	

}
