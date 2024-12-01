package sample;

import java.io.Serializable;

public class Player implements Serializable {
    private int age,number;
    private double height,salary;
    private String name,country,pos;
    private String clubname;


    Player()
    {
        name= "";
        country= "";
        age= 0;
        height= 0;
		clubname = "";
        pos ="";
        number= 0;
        salary= 0;
    }
    Player(String name, String country, int age, double height, String clubname, String pos, int number, double salary )
    {
        this.name = name;
        this.country = country;
        this.age = age;
        this.height = height;
        this.clubname = clubname;
        this.pos = pos;
        this.number = number;
        this.salary = salary;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setClubname(String clubname) {
		this.clubname = clubname;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getName()
	{
		return name;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public double getHeight()
	{
		return height;
	}
	
	public String getClubname()
	{
		return clubname;
	}
	
	public String getPos()
	{
		return pos;
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public void showInfo()
    {
        System.out.println("Name          : "+name);
        System.out.println("Country Name  : "+country);
        System.out.println("Age           : "+age);
        System.out.println("Height        : "+height);
        System.out.println("Club Name     : "+clubname);
        System.out.println("Position      : "+pos);
        System.out.println("Number        : "+number);
        System.out.println("Weekly Salary : "+salary);
        System.out.println("");
    }
}
