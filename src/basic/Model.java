package basic;

import java.awt.font.LineMetrics;
import java.io.*;
import java.math.RoundingMode;
import java.util.*;

public class Model {

    protected ArrayList<Player> players;
    protected ArrayList<Card> roundCard;
    protected CommunalPile cp;
    protected boolean isUser;
    protected int category;
    protected Scanner scanner;
    protected Random random;
    protected Player winner;
    protected ArrayList<Card> winningCards;
    protected HashMap<String, Object> winnerMap;
    protected boolean Draw;


    /*
    constructor
     */
    public Model(int numPeople){
        //create players
        players = new ArrayList<Player>();
        Player user = new Player("You", true);
        players.add(user);
        for(int i=1;i<numPeople;i++){
            players.add(new Player(String.format("AI Player %d", i), false));
        }

        /*
        Parameter initialization
         */
        roundCard = new ArrayList<Card>();
        cp = new CommunalPile();
        scanner = new Scanner(System.in);
        //the first player is selected at random
        random = new Random();
        winner = players.get(random.nextInt(players.size()));
    }

    //distribute cards to players randomly
    public void distributeCards(File file){
        BufferedReader reader = null;
        List<Card> cardList = new ArrayList<Card>();
        try {
            reader = new BufferedReader(new FileReader(file));
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


    /*
    core function
    get the card which has the max value about category each round
     */
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
                        }else if(maxCard.getSize()==tempCards.get(i).getSize()){
                            tempWinningCards.add(tempCards.get(i));
                        }
                        break;
                    case 3:
                        if(maxCard.getRange()<tempCards.get(i).getRange()){
                            maxCard = tempCards.get(i);
                            tempWinningCards.clear();
                            tempWinningCards.add(maxCard);
                        }else if(maxCard.getSize()==tempCards.get(i).getSize()){
                            tempWinningCards.add(tempCards.get(i));
                        }
                        break;
                    case 4:
                        if(maxCard.getFirepower()<tempCards.get(i).getFirepower()){
                            maxCard = tempCards.get(i);
                            tempWinningCards.clear();
                            tempWinningCards.add(maxCard);
                        }else if(maxCard.getSize()==tempCards.get(i).getSize()){
                            tempWinningCards.add(tempCards.get(i));
                        }
                        break;
                    case 5:
                        if(maxCard.getCargo()<tempCards.get(i).getCargo()){
                            maxCard = tempCards.get(i);
                            tempWinningCards.clear();
                            tempWinningCards.add(maxCard);
                        }else if(maxCard.getSize()==tempCards.get(i).getSize()){
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


    //winner choose the category to compare
    public int getCategory(Player player, Card c){
        int num;
        if(player.isUser()){
            System.out.print("It is your turn to select a category, the categories are:\n" +
                    "   1: size\n" +
                    "   2: speed\n" +
                    "   3: range\n" +
                    "   4: firepower\n" +
                    "   5: cargo\n" +
                    "Enter the number for your attribute: ");
            num = scanner.nextInt();
            scanner.nextLine();
            if(num>=1 && num<=5){
                return num;
            }else {
                System.out.println("Please enter a right number again: 1 - 5");
                return getCategory(player, c);
            }
        }else {
            //AI choose category randomly
            //num = random.nextInt(5)+1;

            //AI choose category which is the max
            int tempInt = c.getSize();
            num = 1;
            for(int i=1;i<5;i++){
                if(c.getCategories()[i]>tempInt){
                    tempInt = c.getCategories()[i];
                    num = i;
                }
            }
            return num;
        }
    }

    public HashMap<String, Object> startRound(int round){
        if (winnerMap!=null) {
            winnerMap.clear();
        }
        if(roundCard!=null){
            roundCard.clear();
        }
        //get card from players and add them to communal pile
        for(Player p:players){
            roundCard.add(p.remove());
        }

        if(players.get(0).isUser()){
            String s = String.format("You drew '%s':\n", roundCard.get(0).getCardName()) +
                    String.format("   > size: %d\n", roundCard.get(0).getSize()) +
                    String.format("   > speed: %d\n", roundCard.get(0).getSpeed()) +
                    String.format("   > range: %d\n", roundCard.get(0).getRange()) +
                    String.format("   > firepower: %d\n", roundCard.get(0).getFirepower()) +
                    String.format("   > cargo: %d\n", roundCard.get(0).getCargo());
            System.out.print(s);
            s = String.format("There are '%d cards in your deck\n", players.get(0).getCards().size());
            System.out.print(s);
        }else {
            System.out.print("You have Lost!\n");
        }

        //compare value
        category = getCategory(winner, roundCard.get(players.indexOf(winner)));
        winningCards = CardWithMaxValue(roundCard, category);

        winner = players.get(roundCard.indexOf(winningCards.get(0)));
        updatePlayers();//delete loser
        cp.addCards(roundCard);

        if(!players.contains(winner)){
            winner = players.get(0);
        }




        /*
        if there is a draw
         */
        if(winningCards.size()>1){
            Draw = true;
        }else {
            Draw = false;
            winner.addCards(cp.remove());
        }

        winnerMap = new HashMap<>();
        winnerMap.put("winner", winner);
        winnerMap.put("winningCard", winningCards.get(0));

        return winnerMap;
    }

    //whether is a draw
    public boolean isDraw(){
        return Draw;
    }

    public int getCategory(){
        return category;
    }


    //get the winner's card each round
    public Card getWinningCard(){
        return winningCards.get(0);
    }

    //get the winner each round
    public Player getWinner(){
        return winner;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public ArrayList<Card> getRoundCard(){
        return roundCard;
    }

    public CommunalPile getComPile(){
        return cp;
    }

}
