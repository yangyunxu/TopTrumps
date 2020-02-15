package Test;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.is; 
import static org.hamcrest.MatcherAssert.assertThat; 

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import basic.Card;
import basic.Player;


class PlayerTest {
	
	private String test1 =  "playerTest";
	private boolean Usertest = false;
	
	private String test2 = "orange 1 2 3 4 5 ";
	private String test3 = "apple 1 2 3 4 5 ";
	
	Card c1 = new Card(test2);
	Card c2 = new Card(test3);
	
	private Queue<Card> listTest = new LinkedList<Card>();

	
	Player playertest = new Player(test1,Usertest);
	
	@BeforeEach
	void before() {
		 listTest.offer(c1);
		 listTest.offer(c2);
	}

	@org.junit.Test
	void testAdd() {
		playertest.add(c1);
		assertThat(playertest.getCards().peek(),is(c1));
	}

//	@Test
//	void testAddCards() {
//		playertest.addCards();
//	}

	@Test
	public void testGetCards() {
		playertest.add(c1);
		playertest.add(c2);
		assertThat(playertest.getCards(),is(listTest));
	}

	@Test
	void testIsUser() {
		assertThat(playertest.isUser(),is(false));
	}

	@Test
	void testGetName() {
		assertThat(playertest.getName(),is("playerTest"));
	}
	
	@Test
	void testRemove() {
		playertest.add(c1);
		playertest.add(c2);	
		playertest.remove();
		assertThat(playertest.getCards().peek(),is(c2));
		}



}
