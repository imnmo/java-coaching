package com.frotscher.coaching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Training {

	public List<String> getTrainees() {
		List<String> trainees = new ArrayList<>();
		trainees.add("Uwe Meier");
		trainees.add("Anja Krause");
		trainees.add("Ioannis X.");
		trainees.add("Franz Osebold");
		trainees.add("Konrad Acker");
		trainees.add("Waldemar Käfer");
		trainees.add("Laura Andea");
		trainees.add("Bert Lindemann");
		trainees.add("Alireza Bizmark");
		trainees.add("Rasul Wakili");
		trainees.add("Joern Hinrichsen");
		
		Collections.sort(trainees);
		return trainees;
	}
	
	public List<Person> getTraineesAsPersons() {
		List<Person> trainees = new ArrayList<>();
		trainees.add(new Person("Uwe", "Meier"));
		trainees.add(new Person("Anja", "Krause"));
		trainees.add(new Person("Ioannis", "X."));
		trainees.add(new Person("Franz", "Osebold"));
		trainees.add(new Person("Konrad", "Acker"));
		trainees.add(new Person("Waldemar", "Käfer"));
		trainees.add(new Person("Laura", "Andea"));
		trainees.add(new Person("Bert", "Lindemann"));
		trainees.add(new Person("Alireza", "Bizmark"));
		trainees.add(new Person("Rasul" , "Wakili"));
		trainees.add(new Person("Joern", "Hinrichsen"));
		
		Collections.sort(trainees, new PersonComparator());
		
//		Collections.sort(trainees, (p1, p2) -> p2.getName().compareTo(p1.getName()));
		
		return trainees;
	}

	
	class PersonComparator implements Comparator<Person> {
		@Override
		public int compare(Person p1, Person p2) {
			return p1.getName().compareTo(p2.getName());
		}
	}
}