package basic;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class Model {

    private ArrayList<Player> players, copy_players;
    private ArrayList<Card> roundCard;
    private CommunalPile cp;
    private boolean isUser;
    private int category;
    private Scanner scanner;
    private Random random;
    private Player winner;
    private ArrayList<Card> winningCards;
    private HashMap<String, Object> winnerMap;
    private boolean Draw, printLost;
    private int numberOfDraws;
    private int[] scoreOfPlayers;
    private Logger mylogger;
    private String info;


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
        numberOfDraws = 0;
        scoreOfPlayers = new int[]{0, 0, 0, 0, 0};
        copy_players = new ArrayList<>(players);
        printLost = false;
        mylogger = Logger.getLogger("toplog");
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
            //write in log
            mylogger.info("The contents of the complete deck has been read");
            info = "";
            for(Card c:cardList){
                info+=c.getCardName();
                info+= " ";
            }
            mylogger.info(info);
            mylogger.info("----------------------------------");

            Collections.shuffle(cardList);

            mylogger.info("The contents of the complete deck has been shuffled");
            info="";
            for(Card c:cardList){
                info+=c.getCardName();
                info+= " ";
            }
            mylogger.info(info);
            mylogger.info("----------------------------------");

            Iterator<Player> it = players.iterator();
            for(Card c:cardList){
                if(it.hasNext()){
                    it.next().add(c);
                }else {
                    it = players.iterator();
                    it.next().add(c);
                }
            }

            //each player's deck
            for(Player p:players){
                info="";
                info+=p.getName();
                info+=": ";
                for(Card c:p.getCards()){
                    info+=c.getCardName();
                    info+=" ";
                }
                mylogger.info(info);

            }
            mylogger.info("----------------------------------");

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
        String[] categories = {"size", "speed", "range", "firepower", "cargo"};
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
                mylogger.info(String.format("Player %s choose the category %s with value %d.", player.getName(), categories[num-1], c.getCategoryValue(num)));
                mylogger.info("----------------------------------");
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

        info="";
        for(Card c: roundCard){
            info+=c.getCardName();
            info+=" ";
        }
        mylogger.info(info);
        mylogger.info("----------------------------------");

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
            if(!printLost){
                System.out.print("You have Lost!\n");
                printLost=true;
            }

        }

        //compare value
        category = getCategory(winner, roundCard.get(players.indexOf(winner)));
        winningCards = CardWithMaxValue(roundCard, category);

        if(winningCards.size()==1){
            winner = players.get(roundCard.indexOf(winningCards.get(0)));
        }


        /*
        if there is a draw
         */
        if(winningCards.size()>1){
            Draw = true;
            numberOfDraws++;
            cp.addCards(roundCard);
            mylogger.info(String.format("The communalPile add %d cards, together %d cards", roundCard.size(), cp.getCards().size()));
            mylogger.info("----------------------------------");
        }else {
            Draw = false;
            winner.addCards(roundCard);
            if(cp.getCards().size()>0){
                winner.addCards(cp.remove());
                mylogger.info(String.format("The communalPile remove %d cards, and left 0 cards", cp.getCards().size()));
                mylogger.info("----------------------------------");
            }
            scoreOfPlayers[players.indexOf(winner)]++;
        }

        updatePlayers();//delete loser

        winnerMap = new HashMap<>();
        winnerMap.put("winner", winner);
        winnerMap.put("winningCard", winningCards.get(0));

        for(Player p:players){
            info="";
            info+=p.getName();
            info+=": ";
            for(Card c:p.getCards()){
                info+=c.getCardName();
                info+=" ";
            }
            mylogger.info(info);

        }
        mylogger.info("----------------------------------");

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

    public int getNumberOfDraws(){
        return numberOfDraws;
    }

    public int[] getScoreOfPlayers(){
        return scoreOfPlayers;
    }

    public int getIndexOfPlayers(Player p){
        return copy_players.indexOf(p);
    }

    public String scoreOneGame(){
        String s = "\n";
        s+=String.format("The overall winnder was %s\n", getWinner().getName());
        s+="Scores:\n";
        for(Player p:copy_players){
            s+=String.format("   %s: %d\n", p.getName(),scoreOfPlayers[copy_players.indexOf(p)]);
        }
        s+="\n\n";
        return s;
    }

    public void setPrintLost(boolean b){
        printLost = b;
    }

}
