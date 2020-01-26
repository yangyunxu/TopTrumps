package basic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class CommunalPile {

    protected ArrayList<Card> cards;

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
