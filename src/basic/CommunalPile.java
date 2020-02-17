package basic;

import java.util.ArrayList;
import java.util.Collections;

/*
the cards will be put here if the round is a draw
 */
public class CommunalPile {

    private ArrayList<Card> cards;

    public CommunalPile(){
        cards = new ArrayList<Card>();
    }


    public void addCards(ArrayList<Card> manyCards){
        cards.addAll(manyCards);
    }

    public ArrayList<Card> remove(){
        ArrayList<Card> temp = new ArrayList<Card>(cards);
        Collections.shuffle(temp);
        cards.clear();
        return temp;
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

}
