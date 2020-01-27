package basic;

public class Card {
    private String description;
    private int[] categories;
    /*
    0-size
    1-speed
    2-range
    3-firepower
    4-cargo
     */

    public Card(String s){
        categories = new int[5];
        String[] properties = s.trim().split(" ");
        description = properties[0];
        categories[0] = Integer.parseInt(properties[1]);
        categories[1] = Integer.parseInt(properties[2]);
        categories[2] = Integer.parseInt(properties[3]);
        categories[3] = Integer.parseInt(properties[4]);
        categories[4] = Integer.parseInt(properties[5]);
    }

    public String getCardName(){
        return description;
    }

    public int[] getCategories(){
        return categories;
    }

    public int getSize(){
        return categories[0];
    }

    public int getSpeed(){
        return categories[1];
    }

    public int getRange(){
        return categories[2];
    }

    public int getFirepower(){
        return categories[3];
    }

    public int getCargo(){
        return categories[4];
    }



}
