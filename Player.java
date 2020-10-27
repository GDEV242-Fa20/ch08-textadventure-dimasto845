import java.util.*;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    //instance variables
    
    // to hold player's name
    public String playerName;
    
    //to hold items name
    public String itemName;
    
    //to hold players current room
    public Room currentRoom;
    
    //to hold the items weight
    public int weight;
    
    //to hold players maximum capacity to carry
    public int maximumWeight = 0;
    
    //to store the players visited rooms
    public Stack<Room> roomStack;
    
    //to hold the item that the player has picked up
    public Item itemInHand;
    
    public String[] itemCarry = new String[20];
    public int i=0;
    
    /**
     * constructor for objects of class Player
     */
    public Player () {
    // initialise instance variable
    playerName="";
    currentRoom = null;
    maximumWeight = 10000;
    roomStack = new Stack<Room>();
    itemInHand = null;
    }
    
    //parameterized constructor
    public Player(String name, Room currRoom, int maximumWeight) {
        this.playerName = name;
        this.currentRoom = currRoom;
        this.maximumWeight = maximumWeight;
        roomStack = new Stack<Room>();
        itemInHand = null;
    }
    
    //to set and get the players name
    public void setPlayerName(String pName)
    {
        this.playerName = pName;
    }
    public String getPlayerName()
    {
        return this.playerName;
    }
    
    //to set and get the current room object
    public void setCurrentRoom(Room currRoom)
    {
        this.currentRoom = currRoom;
    }
    public Room getCurrentRoom()
    {
        return this.currentRoom;
    }
    
    //to set and get the maximum weight 
    public void setMaximumWeight(int maxWeight)
    {
        this.maximumWeight = maxWeight;
    }
    public int getMaximumWeight() 
    {
        return maximumWeight;
    }
    
    //to set and get the item in the players hand
    public void setItemInHand(Item itempicked)
    {
        itemInHand = itempicked;
    }
    public Item getItemInHand()
    {
        return itemInHand;
    }
    
    //to get the players description
    public String getPlayerDescription()
    {
        String result = "Player "+playerName+": \n";
        if(itemInHand != null) {
        result += "You are carying a/an " + itemInHand.getItemName() + " item in hand. \n\n";
        }
        return result;
    }
    
    //to get the exit room object 
    public Room getPlayerExit(String direction)
    {
        return currentRoom.getExit(direction);
    }
    
    //set the players current entering room
    public void setPlayersEnteringRoom(Room nextRoom)
    {
        roomStack.push(currentRoom);
        currentRoom = nextRoom;
    }
    
    //to display the players entering the previous room information
    public void movePlayerToPreviousRoom()
    {
        if(roomStack.empty())
        {
            System.out.println("Sorry, you are outside the University and there are no previous rooms to go");
        }
        else
        {
            currentRoom = roomStack.pop();
            System.out.println("PLayer "+playerName+ ": ");
            if(itemInHand !=null){
                System.out.println("You are carrying a/an" + itemInHand.getItemName() +"item in hand.");
            }
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    //method to check whether the given item can be picked up by the player. returns boolean value
    public boolean canBePickedUp(String itemName)
    {
        Item item = currentRoom.getItem(itemName);
        if (item == null)
        {
            return false;
        }
        
        if(item.getWeight() < maximumWeight && !alreadyItemExistsInHand())
        {
            return true;
        }
        
        else {
        return false;
        }
        
    }
    
    //method to check whether the given item can be  dropped by the player. returns boolean value
    public boolean canBeDropped(String itemName)
    {
        Item item = currentRoom.getItem(itemName);
        if (item == null)
        {
            return false;
        }
        
        if(itemInHand.getItemName().equalsIgnoreCase(itemName)&& alreadyItemExistsInHand())
        {
            return true;
        }
        
        else {
        return false;
        }
        
    }
    
    //method to check whether there exists a single item in players hand or not
    public boolean alreadyItemExistsInHand()
    {
        if(itemInHand !=null)
        {
            return true;
        }
        else{
        return false;
        }
    }
    
    //method to pick an item by the player present in the current room
    public void pickUpItem(String itemName) {
    if(canBePickedUp(itemName))
    {
        Item item = currentRoom.getItem(itemName);
        setItemInHand(item);
        currentRoom.removeItem(item);
        System.out.println("Player picked the item successfully");
    }
    else{
        if(alreadyItemExistsInHand()){
        System.out.println("Already an item in players hand");
        }
        else {
        System.out.println("Sorry, " + itemName + "not found in the room");
        }
        
    }
    return;
        
        }
    
    //method to drop an item in the room
    public void dropItem (String itemName)
    {
        if(canBeDropped(itemName)) {
        currentRoom.addItem(itemInHand);
        itemInHand = null;
        System.out.println("Player dropped the item successfully");
        }
        else{
        System.out.println("Sorry, there is no item to drop");
        return;
        }
    
}


public void printItem()
    {
        System.out.println("You contain following item\n");
        for(int j=0; j< i; j++)
        {
            System.out.println(" " + (j+1) + " " + itemCarry[j]);
        }
        System.out.println("\nTotal Weight : " + maximumWeight);
    }

public void eatCookie() {
if((maximumWeight + weight) < 2000)
{
    System.out.println("Eats Magic Cookie");
    maximumWeight += 5;
}
else{
System.out.println("You cannot eat magic cookie");
}
}
}
    
        
    
    
    
