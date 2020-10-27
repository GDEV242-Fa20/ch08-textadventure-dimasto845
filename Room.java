import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    public String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> roomItems;
    private String item;
    private Item roomItem;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        roomItems = new ArrayList<Item>();
    }
    
    public ArrayList<Item> getRoomItems() {
        return roomItems;
    }
    
    public Item getItem(String itemName) 
    {
        for (int i = 0; i < roomItems.size(); i++)
        {
            if (roomItems.get(i).getItemName().equalsIgnoreCase(itemName)) {
            return roomItems.get(i);
            }
        }
        return null;
    }
    
    public void removeItem(Item item)
    {
        for (int i=0; i < roomItems.size(); i++)
        {
            if (roomItems.get(i) == item)
            {
                roomItems.remove(item);
                break;
            }
        }
    }


    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
        
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    public String getExitString()
    {

        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    /**
     * Return a long description of this room, of the form:
     *  You are in the kitchen
     *  Exits: north west
     *  @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        return "You are " + description +".\n" + getExitString() + ".\n";
       
    }
    
    public String getItemsInRoom(){
    String returnItems = "Items in the rooms are: \n";
    for(Item item : roomItems){
    returnItems += item.getItemDescription()+"\n";
    }
    return returnItems;
    }
    
    /**
     * add an item to the room when it is created
     */
    public void addItem(Item item)
    {
        roomItems.add(item);
    }
}

