package Malmo;

import java.util.Date;

public class Cat extends Animal{

	// Constructor
	public Cat(String name, Date birthDate, String animalOwnerId) {
		super(name, birthDate, animalOwnerId);
		// TODO Auto-generated constructor stub
	}
	
	// Cat extra properties
	private String furColor;
	
	
	public String getFurColor()
	{
		return this.furColor;
	}
	
	public void setFurColor()
	{
		this.furColor = furColor;
	}

	
}
