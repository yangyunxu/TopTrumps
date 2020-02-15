package Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is; 
import static org.hamcrest.MatcherAssert.assertThat; 
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import org.junit.Before;
import basic.Card;
import basic.CommunalPile;

class communalTest {
	CommunalPile comtest = new CommunalPile();
	ArrayList<Card> cardstest = new ArrayList<Card>();
	
	String test1 ="orange 1 2 3 4 5";
	String test2 ="apple 1 2 3 4 5";
	Card c1 = new Card(test1);
	Card c2 = new Card(test2);
	@Before
	void before() {
		cardstest.add(c1);
		cardstest.add(c2);
	}

	@Test
	void testAddCards() {
		comtest.addCards(cardstest);
		assertThat(comtest.getCards(),is(cardstest));
	}

	@Test
	void testRemove() {
		comtest.addCards(cardstest);
		comtest.remove();
		assertThat(comtest.getCards(),is(new ArrayList<Card>()));
	}

	@Test
	void testGetCards() {
		comtest.addCards(cardstest);
		assertThat(comtest.getCards(),is(cardstest));
		
	}

}
