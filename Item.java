import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String itemDescription;
    private int itemWeight;
    private int weight;
    private ArrayList<Item> items;
    private String name;
    private String magicCookie;
    private String itemName;
    
    public Item() {
    itemDescription = "";
    itemWeight = 0;
    name = "";
    }
       /**
         * Constructor for objects of class Item
         * Create an item described "description", and Weight
         */
    public Item(String description, int weight)
    {
        // initialise instance variables
        description = description;
        weight = weight;
        this.items = new ArrayList<Item>();
        this.magicCookie = "Cookies";

    }
    
    public String getItemDescription() {
    String itemString = "\tItem name: " + this.itemDescription +"\n";
    itemString += "\tItem Weight: "+this.itemWeight;
    itemString = itemString +"\nThis room has cookies";
    return itemString;
    }
    
    public String getItemName() {
    return name;
    }

    /**
     * Return the description of the item
     */
    public String getDescriptionOfItem()
    {
        return itemDescription;
    }
    
    public int getWeight() {
    return itemWeight;
    }

    /**
     * Return the weight of the item
     */
    public int weight()
    {
        return itemWeight;
    }

    /**
     * Return a long description of the item that includes the description 
     * and weight.
     */
    public String getLongDescription()
    {
        return "Item " + itemDescription + " Weight " + itemWeight;
    }
   
    /**
     * create a variety of items
     */
    public void add(Item aItem)
    {
        items.add(aItem);
    }

    /**
     * get the item
     */
    public ArrayList<Item> getItems()
    {
        return items;
    }

    /**
     * 
     */
    public String getItemString()
    {
        String returnString = "Item:";

        {
            returnString += " " + items;
        }
        return returnString;
    }

}
