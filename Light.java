import java.util.ArrayList;
/**
 * Write a description of class Light here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Light extends Item
{
    // instance variables - replace the example below with your own
    private boolean is_On = false;

    /**
     * Constructor for objects of class Light
     */
    public Light(String desc, int weight)
    {
        super(desc, weight);
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public boolean isOn ()
    {
        // put your code here
        return is_On;
    }
    
    public void light() {
        is_On = true;
    }
    
    /** 
     * getLight looks on the given list of Items (usually the inventory)
     * to see if there's a Light object that is lit().
     * If so, the first such is returned.
     * If not, null is returned.
     * Note that getLight **must do run-time type checking**
     * (Well, the alternative is to add to Item an isLight method,
     * but that seems to be an AWKWARD use of the inheritance model)
     */
    public static Light getLight(ArrayList stuff) {
        int i = 0;
        while (i < stuff.size()) {
            Item itm = (Item) stuff.get(i);   // plain cast; we know they're all Item
            // now for the dynamic part
            if (itm instanceof Light) {
                Light lt = (Light) itm;
                if (lt.isOn()) return lt;
            }
            i++;
        }
        return null;    // get here if not found
    }
    
    public static boolean haslight(ArrayList stuff) {
        return (getLight(stuff) != null) ;
    }
}
