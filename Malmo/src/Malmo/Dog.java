package Malmo;

import java.util.Date;

public class Dog extends Animal {

	// Constructor
	public Dog(String name, Date birthDate, String animalOwnerId) {
		super(name, birthDate, animalOwnerId);

	}

	// Dog extra properties
	private String dogRace;
	private String dogColor; 
	
	
	// Get and Set Methods	
	public String getDogRace()
	{
		return this.dogRace;
	}
	
	public void setDogRace(String dogRace)
	{
		this.dogRace = dogRace;
	}
	
	public String getDogColor()
	{ 
		return this.dogColor;
	}
	
}
