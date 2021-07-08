package Malmo;

import java.util.Date;
import java.util.UUID;

public abstract class Animal {
	
	// General properties about any animal
	private String name;
	private Date birthDate;
	private char gender; // F for female and M for male
	private int chipNumber; // This field can be empty
	private boolean state; // is alive or dead
	private String animalOwnerId;
	private UUID animalIdentifier; // each animal have a unique identifier number

	// Constructor
	public Animal(String name, Date birthDate, String animalOwnerId)
	{
		this.name = name;
		this.birthDate = birthDate;
		this.animalOwnerId = animalOwnerId;
		this.animalIdentifier = UUID.randomUUID(); // generate random id for animal
	}
	
	// Get and Set Methods

	 public String get_name()
	{
		return name;
	}
	
	public void set_name(String name)
	{
		this.name = name;
	}
	
	public Date get_birthDate()
	{
		return this.birthDate;
	}
	
	public void set_birthDate(Date birthDate)
	{
		this.birthDate=birthDate;
	}
	
	public char get_gender()
	{
		return this.gender;
	}
	
	public void set_gender(char gender)
	{
		this.gender=gender;
	}
	
	public int get_chipNumber()
	{
		return this.chipNumber;
	}
	
	public void set_chipNumber(int chipNumber)
	{
		this.chipNumber=chipNumber;
	}
	
	public boolean get_state()
	{
		return this.state;
	}
	
	public void set_state(boolean state)
	{
		this.state=state;
	}
	
	public String get_animalOwnerId()
	{
		return this.animalOwnerId;
	}
	
	public void set_animalOwnerId(String animalOwnerId)
	{
		this.animalOwnerId=animalOwnerId;
	}
	
	public UUID get_animalIdentifier()
	{
		return animalIdentifier;
	}
	
	public void set_animalIdentifier(UUID animalIdentifier)
	{
		this.animalIdentifier=animalIdentifier;
	}
}