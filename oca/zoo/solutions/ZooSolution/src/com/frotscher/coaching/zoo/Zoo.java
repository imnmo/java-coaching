package com.frotscher.coaching.zoo;

import java.util.ArrayList;
import java.util.List;

public class Zoo {

	List<Animal> animals = new ArrayList<Animal>();
	
	public void addAnimal(Animal animal) {
		animals.add(animal);
		System.out.println("Added to the zoo: " + animal.getName());
	}
	
	public List<Animal> getAnimals() {
		return animals;
	}
	
	public void letAnimalsEat() {
		for (Animal animal : animals) {
			animal.eat();
		}
	}

	public void letAnimalsSwim() {
		for (Animal animal : animals) {
			if (animal instanceof Swimming) {
				((Swimming) animal).swim();
			}
		}
	}
	
	public void letAnimalsFly() {
		for (Animal animal : animals) {
			if (animal instanceof Flying) {
				((Flying) animal).fly();
			}
		}
	}

	public void letAnimalsWalk() {
		for (Animal animal : animals) {
			if (animal instanceof Walking) {
				((Walking) animal).walk();
			}
		}
	}	
}