package basic;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.io.File;

public class Game {


    private Model model;
    protected int round;

    public Game(int numPeople) {
        if (numPeople < 2 || numPeople > 5) {
            System.out.println("Please enter right number of people.");
            return;
        }
        //game initial
        model = new Model(numPeople);
        model.distributeCards(new File("StarCitizenDeck.txt"));
//        for (Player p:model.getPlayers()){
//            System.out.println(p.getName()+" "+p.getCards().size());
//        }



    }


    public Model getModel() {
        return model;
    }

    public int getRound() {
        return round;
    }

    public boolean isUserAlive() {
        return model.players.get(0).isUser();
    }

}
