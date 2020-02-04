package online.dwResources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import basic.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import com.sun.org.apache.bcel.internal.generic.LSHL;
import javafx.beans.binding.ObjectExpression;
import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input

public class TopTrumpsRESTAPI {

	private int numberGames, round, draws, category;
	private String userName="";
	private ArrayList<Player> players, copy_players;
	private ArrayList<Card> roundCard;
	private CommunalPile cp;
	private Random random = new Random();
	private Player winner;
	private ArrayList<Card> winningCards;
	private int[] scoreOfPlayers;
	private boolean draw;
	private boolean gameContinue;



	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) throws IOException {
		launchGame();

	}

	@GET
	@Path("/launchGame")
	public void launchGame() throws IOException {
		Database.connection();
		Database.createTable();
		numberGames = Database.getNumberOfGames();
		round = 0;
		draws = 0;
		scoreOfPlayers = new int[]{0, 0, 0, 0, 0};
		gameContinue = true;
		draw = false;
	}

	@GET
	@Path("/getStatistics")
	public String getStatistics() throws IOException {
		ArrayList<Object> array = new ArrayList<>();
		array.add(Database.getNumberOfGames());
		array.add(Database.getNumberOfHumanWins());
		array.add(Database.getNumberOfAIWins());
		array.add(Database.getAverageNumberOfDraws());
		array.add(Database.getLongestGame());

		return oWriter.writeValueAsString(array);
	}

	/*
	Game module
	 */

	@GET
	@Path("/numberAi")
	public void numberAiPlayers(@QueryParam("number") int number) throws IOException {
		players = new ArrayList<Player>();
		Player user = new Player(userName, true);
		players.add(user);
		for(int i=1;i<=number;i++){
			players.add(new Player(String.format("AI Player %d", i), false));
		}
		copy_players = new ArrayList<>(players);
		winner = players.get(random.nextInt(players.size()));
		roundCard = new ArrayList<>();
		cp = new CommunalPile();
		//distribute cards
		BufferedReader reader = null;
		List<Card> cardList = new ArrayList<Card>();
		try {
			reader = new BufferedReader(new FileReader("StarCitizenDeck.txt"));
			String line;
			reader.readLine();
			while ((line = reader.readLine()) != null){
				cardList.add(new Card(line));
			}
			Collections.shuffle(cardList);
			Iterator<Player> it = players.iterator();
			for(Card c:cardList){
				if(it.hasNext()){
					it.next().add(c);
				}else {
					it = players.iterator();
					it.next().add(c);
				}
			}
		}catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(reader!=null){
				try {
					reader.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}


	@GET
	@Path("/getCategory")
	public String getCategory() throws IOException{
		int num;
		String[] categories = {"size", "speed", "range", "firepower", "cargo"};
		if(!winner.isUser()){
			Card c = roundCard.get(players.indexOf(winner));
			//AI choose category which is the max
			int tempInt = c.getSize();
			num = 1;
			for(int i=1;i<5;i++){
				if(c.getCategories()[i]>tempInt){
					tempInt = c.getCategories()[i];
					num = i+1;
				}
			}
			category = num;
			return playerWithRoundCard(num);
		}else {
			return playerWithRoundCard(0);//user choose the category
		}
	}

	@GET
	@Path("/getUserCategory")
	public String getUserCategory(@QueryParam("number") int number) throws IOException{
		category = number;
		return playerWithRoundCard(number);
	}

	@GET
	@Path("/showWinner")
	public String showWinner() throws JsonProcessingException {
		startRound();
		ArrayList<Object> list = new ArrayList<>();
		list.add(draw);
		list.add(winner.getName());
		if(players.size()==1){
			gameContinue = false;
			inserDatabase();
		}
		list.add(gameContinue);
		return oWriter.writeValueAsString(list);
	}


	@GET
	@Path("/userName")
	public void setUserName(@QueryParam("name") String name) throws IOException {
		userName = name;
	}

	@GET
	@Path("/nextRound")
	public String nextRound() throws IOException{
		drawCards();
		HashMap<String, String> map = new HashMap<>();
		map.put("Round", String.valueOf(round));
		map.put("activePlayer", winner.getName());
		if(players.get(0).isUser()){
			map.put("userAlive", "true");
			map.put("numberInDeck", String.valueOf(players.get(0).getCards().size()));
			map.put("CardName", roundCard.get(0).getCardName());
			map.put("size", String.valueOf(roundCard.get(0).getSize()));
			map.put("speed", String.valueOf(roundCard.get(0).getSpeed()));
			map.put("range", String.valueOf(roundCard.get(0).getRange()));
			map.put("firepower", String.valueOf(roundCard.get(0).getFirepower()));
			map.put("cargo", String.valueOf(roundCard.get(0).getCargo()));
		}else {
			map.put("userAlive", "false");
		}
		return oWriter.writeValueAsString(map);
	}

	public void updatePlayers(){
		//delete the loser
		Iterator<Player> iterator = players.iterator();
		while (iterator.hasNext()){
			Player p = iterator.next();
			if(p.getCards().size()==0){
				iterator.remove();
			}
		}

	}

	public void drawCards(){
		if(roundCard!=null){
			roundCard.clear();
		}
		//get card from players and add them to communal pile
		for(Player p:players){
			roundCard.add(p.remove());
		}
	}

	public String playerWithRoundCard(int num) throws IOException {
		HashMap<String, Object> map = new HashMap<>();
		for(Player p:players){

			map.put(p.getName()+","+String.valueOf(p.getCards().size()), roundCard.get(players.indexOf(p)));
		}
		map.put("Category", num);
		String result = oWriter.writeValueAsString(map);
		return result;
	}

	public void startRound(){
		round++;
		winningCards = CardWithMaxValue(roundCard, category);
		if(winningCards.size()>1){
			draw = true;
			draws++;
			cp.addCards(roundCard);
		}else {
			draw = false;
			winner = players.get(roundCard.indexOf(winningCards.get(0)));
			winner.addCards(roundCard);
			if(cp.getCards().size()>0){
				winner.addCards(cp.remove());
			}
			scoreOfPlayers[copy_players.indexOf(winner)]++;
		}
		updatePlayers();

	}

	public ArrayList<Card> CardWithMaxValue(ArrayList<Card> tempCards, int category){
		//1-Size 2-Speed 3-Range 4-Firepower 5-Cargo

		ArrayList<Card> tempWinningCards = new ArrayList<>();
		int size = tempCards.size();
		if(size>1){
			tempWinningCards.clear();
			Card maxCard = tempCards.get(0);
			tempWinningCards.add(maxCard);

			for(int i=1;i<size;i++){
				switch (category){
					case 1:
						if(maxCard.getSize()<tempCards.get(i).getSize()){
							maxCard = tempCards.get(i);
							tempWinningCards.clear();
							tempWinningCards.add(maxCard);
						}else if(maxCard.getSize()==tempCards.get(i).getSize()){
							tempWinningCards.add(tempCards.get(i));
						}
						break;
					case 2:
						if(maxCard.getSpeed()<tempCards.get(i).getSpeed()){
							maxCard = tempCards.get(i);
							tempWinningCards.clear();
							tempWinningCards.add(maxCard);
						}else if(maxCard.getSpeed()==tempCards.get(i).getSpeed()){
							tempWinningCards.add(tempCards.get(i));
						}
						break;
					case 3:
						if(maxCard.getRange()<tempCards.get(i).getRange()){
							maxCard = tempCards.get(i);
							tempWinningCards.clear();
							tempWinningCards.add(maxCard);
						}else if(maxCard.getRange()==tempCards.get(i).getRange()){
							tempWinningCards.add(tempCards.get(i));
						}
						break;
					case 4:
						if(maxCard.getFirepower()<tempCards.get(i).getFirepower()){
							maxCard = tempCards.get(i);
							tempWinningCards.clear();
							tempWinningCards.add(maxCard);
						}else if(maxCard.getFirepower()==tempCards.get(i).getFirepower()){
							tempWinningCards.add(tempCards.get(i));
						}
						break;
					case 5:
						if(maxCard.getCargo()<tempCards.get(i).getCargo()){
							maxCard = tempCards.get(i);
							tempWinningCards.clear();
							tempWinningCards.add(maxCard);
						}else if(maxCard.getCargo()==tempCards.get(i).getCargo()){
							tempWinningCards.add(tempCards.get(i));
						}
						break;
				}
			}
			return tempWinningCards;
		}else {
			return null;
		}

	}


	public void inserDatabase() {
		ArrayList<Integer> data = new ArrayList();
		data.add(numberGames);//number of games
		data.add(copy_players.size());//number of members this game
		data.add(draws);//number of draws this game
		data.add(copy_players.indexOf(winner));//winner
		data.add(round);//number of round this game
		data.add(scoreOfPlayers[0]);//the score of user this game
		data.add(scoreOfPlayers[1]);//the score of AI player 1 this game
		data.add(scoreOfPlayers[2]);//the score of AI player 2 this game
		data.add(scoreOfPlayers[3]);//the score of AI player 3 this game
		data.add(scoreOfPlayers[4]);//the score of AI player 4 this game

		Database.insert(data);
	}

}
