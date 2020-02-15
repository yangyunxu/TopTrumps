package Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is; 
import static org.hamcrest.MatcherAssert.assertThat; 
import org.junit.jupiter.api.Test;

import basic.Card;

class CardTest {
	
	String descriptiontest = "orange 1 2 3 4 5  ";
	
	Card cardTest = new Card(descriptiontest);
	
	
	@Test
	final void testGetCardName() {
		assertThat(cardTest.getCardName(), is("orange"));
	}

	@Test
	final void testGetCategories() {
		
		int[] a = new int[5];
		a[0] = 1;
		a[1] = 2;
		a[2] = 3;
		a[3] = 4;
		a[4] = 5;
		
		assertThat(cardTest.getCategories(),is(a));

	}

	@Test
	final void testGetSize() {
		assertThat(cardTest.getSize(),is(1));
	}

	@Test
	final void testGetSpeed() {
		assertThat(cardTest.getSpeed(),is(2));
	}

	@Test
	final void testGetRange() {
		assertThat(cardTest.getRange(),is(3));
	}

	@Test
	final void testGetFirepower() {
		assertThat(cardTest.getFirepower(),is(4));
	}

	@Test
	final void testGetCargo() {
		assertThat(cardTest.getCargo(),is(5));
	}

}