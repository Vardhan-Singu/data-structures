import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
    This program demonstrates a map that maps names to colors.
*/
public class MapDemo
{
    public static void main(String[] args)
    {
        /*
         * The map interface is generic
         * the first type given is the key type
         * the secibd tyoe given is the value
         */
        Map<String, Color> favColors = new HashMap<>();

        // Add elements to the map using the put method
        favColors.put("Streeram", Color.GREEN);
        favColors.put("Kaitlyn", Color.GREEN);
        favColors.put("Cam", Color.BLUE);
        favColors.put("Nimani", Color.RED);

        // Two different elements with the same value
        favColors.put("kaitlyn", Color.GREEN);

        //Create a set of keys in the map
        Set<String> keys = favColors.keySet();
        for(String key:keys){
            // [name] ([hasCode]) : [color]
            System.out.println(key + "(" + key.hashCode() + ") : " + favColors.get(key));
            
        }
    }
}
