package basic;

public class Card {
    protected String description;
    protected int size;
    protected int speed;
    protected int range;
    protected int firepower;
    protected int cargo;

    public Card(String s){
        String[] properties = s.trim().split(" ");
        description = properties[0];
        size = Integer.parseInt(properties[1]);
        speed = Integer.parseInt(properties[2]);
        range = Integer.parseInt(properties[3]);
        firepower = Integer.parseInt(properties[4]);
        cargo = Integer.parseInt(properties[5]);
    }

    public String getCardName(){
        return description;
    }

    public int getSize(){
        return size;
    }

    public int getSpeed(){
        return speed;
    }

    public int getRange(){
        return range;
    }

    public int getFirepower(){
        return firepower;
    }

    public int getCargo(){
        return cargo;
    }



}
