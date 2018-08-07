import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Task {
	private int id;
	private static int count = 1;
	private String nume;
	private String ora;
	private String WeekDay;
	private String minut;
	
	Task(){
		System.out.println("Introduceti task-ul: ");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			this.nume = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Introduceti ziua din saptamana [Luni, Marti, ...]: ");
		try {
			this.WeekDay = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		System.out.println("Introduceti doar ora evenimentului [hh]: ");
		try {
			this.ora = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		System.out.println("Introduceti doar minutul evenimentului [mm]: ");
		try {
			this.minut = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.id=count++;
	}
	
	public abstract void WhatKind();
	
	public void getInfo() {
		System.out.println("Evenimentul " + id + ": " + WeekDay + " la " + ora + ":" + minut + " - " + nume);
	}
	public String getHour() {
		return this.ora;
	}
	public String getNume() {
		return this.nume;
	}
	public String getMinute() {
		return this.minut;
	}
	public String getDay() {
		return this.WeekDay;
	}
}
