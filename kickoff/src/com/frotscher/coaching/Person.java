package com.frotscher.coaching;

public class Person {

	private String vorname;
	private String name;
	
	public Person(String vorname, String name) {
		this.vorname = vorname;
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return vorname + " " + name;
	}
}