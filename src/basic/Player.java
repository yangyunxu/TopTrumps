package basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Player {

    private String name;
    private Queue<Card> cards;
    private boolean User;

    public Player(String s, boolean isUser){
        name = s;
        cards = new LinkedList<Card>();
        this.User = isUser;
    }

    public void add(Card c){
        cards.add(c);
    }

    public void addCards(ArrayList<Card> manyCards){
        for(Card c:manyCards){
            cards.offer(c);
        }
    }

    public boolean isUser(){
        return User;
    }

    public String getName(){
        return name;
    }

    public Card remove(){
        return cards.poll();
    }

    public Queue<Card> getCards(){
        return cards;
    }

}
