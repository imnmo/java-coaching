package com.frotscher.coaching.zoo;

import org.junit.Test;

public class ZooTest {

	@Test
	public void testZoo() {
		
		Zoo zoo = new Zoo();
		zoo.addAnimal(new Lion());
		zoo.addAnimal(new Dolphin());
		zoo.addAnimal(new Eagle());
		zoo.addAnimal(new Duck());
		zoo.addAnimal(new Emu());
		
		zoo.letAnimalsEat();

		zoo.letAnimalsWalk();
		zoo.letAnimalsFly();
		zoo.letAnimalsSwim();
	}

	@Test
	public void testPolymorphism() {

		Animal lion1 = new Lion();
		Lion lion2 = new Lion();

		lion1 = lion2; // ok, because a Lion is an Animal
		
		// lion2 = lion1; // does not compile, because an Animal might not be a Lion

		lion2 = (Lion) lion1; // ok - explicit cast. Might throw ClassCastException at runtime
	}
	
}
