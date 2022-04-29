import java.util.HashMap;
import java.util.Set;

/**
 * Write a description of class Items here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    private String name;
    private boolean weight;
    private String items;
    private String des;
    private boolean talk;
    private Room coRoom;
    private String coDes;
    
    
    /**
     * Constructor for objects of class Item
     */
    public Item(String name, boolean weight, String des, boolean talk, Room coRoom, String coDes)
    {
        this.name = name;
        this.weight = weight;
        this.des = des;
        this.talk = talk;
        this.coRoom = coRoom;
        this.coDes = coDes;
    }
    
    public String getName()
    {
        return name;
    }
    
    public boolean getWeight()
    {
        return weight;
    }
    
    public String getDes() {
        return des;
    }
    
    public boolean canTalk() {
        return talk;
    }
    
    public Room getCoRoom() {
        return coRoom;
    }
    
    public String getCoDes() {
        return coDes;
    }
}
