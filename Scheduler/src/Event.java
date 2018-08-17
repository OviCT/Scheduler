import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Event {
	public int id;
	private static int count = 1;
	private String name;
	private String hour;
	private String weekday;
	
	Event(){
		System.out.println("Insert the name of the event: ");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			this.name= input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Insert the week day [Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday]: ");
		try {
			this.weekday = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		System.out.println("Insert the hour of the Event [hh:mm]: ");
		try {
			this.hour = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.id=count++;
	}
	
	public abstract void WhatKind();
	
	public String getHour() {
		return this.hour;
	}
	public void getInfo() {
		System.out.println("Event " + id + ": " + weekday + " at " + hour + " - " + name);
	}
	public String getName() {
		return this.name;
	}
	public String getDay() {
		return this.weekday;
	}
	public int getID() {
		return this.id;
	}
}
