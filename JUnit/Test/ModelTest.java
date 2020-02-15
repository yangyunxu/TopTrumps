package Test;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelTest {
	int num1 = 3;
	Model modeltest = new Model(num1);
	@Test
	void testDistributeCards() {
		modeltest.distributeCards(new File("ModelTestFile.txt"));
		assertThat(modeltest.getPlayers().get(0).getCards().isEmpty(),is(false));
		assertThat(modeltest.getPlayers().get(0).getCards().size(),is(2));
		for(int i = 0;i<num1;i++) {
			System.out.println(modeltest.getPlayers().get(i).getName());
			System.out.println(modeltest.getPlayers().get(i).getCards().peek().getCardName()+" "+modeltest.getPlayers().get(i).getCards().peek().getSize());
		}
		
	}

	@Test
	void testUpdatePlayers() {
		modeltest.distributeCards(new File("ModelTestFile.txt"));
		modeltest.getPlayers().get(1).remove();
		modeltest.updatePlayers();
		assertThat(modeltest.getPlayers().size(),is(3));
		assertEquals(modeltest.getPlayers().get(1).getName(),"AI Player 1");
		modeltest.getPlayers().get(1).remove();
		modeltest.updatePlayers();
		assertThat(modeltest.getPlayers().size(),is(2));
		assertThat(modeltest.getPlayers().get(0).getName(),is("You"));
	}

	@Test
	void testCardWithMaxValue() {
		modeltest.distributeCardsMock(new File("ModelTestFile.txt"));
		modeltest.startRound(1);
		assertThat(modeltest.CardWithMaxValue(modeltest.getRoundCard(), 1).get(0).getCardName(),is("apple"));
	}
//	@Test
//	void testGetCategory() {
//		modeltest.distributeCardsMock(new File("ModelTestFile.txt"));
//		int num0 = modeltest.getCategory(modeltest.getPlayers().get(0), modeltest.getPlayers().get(0).getCards().peek());
//		assertThat(num0,is(1));
//		//choose 1
//		//excepted:range 100
//		modeltest.getCategory(modeltest.getPlayers().get(1), modeltest.getPlayers().get(1).getCards().peek());
//		assertThat(num0,is(1));
//	}
//
//	@Test
//	void testStartRound() {
//		modeltest.distributeCardsMock(new File("ModelTestFile.txt"));
//		modeltest.startRound(1);
//		assertThat(modeltest.getWinningCard().getCardName(),is("apple"));
//		assertThat(modeltest.getWinningCard().getSize(),is(100));
//	}

}
