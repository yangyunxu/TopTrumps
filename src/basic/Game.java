package basic;

import java.io.File;

//start a new game
public class Game {


    private Model model;

    public Game(int numPeople) {
        if (numPeople < 2 || numPeople > 5) {
            System.out.println("Please enter right number of people.");
            return;
        }
        //game initial
        model = new Model(numPeople);
        model.distributeCards(new File("StarCitizenDeck.txt"));

        model.setPrintLost(false);


    }


    public Model getModel() {
        return model;
    }

    public boolean isUserAlive() {
        return model.getPlayers().get(0).isUser();
    }

}
