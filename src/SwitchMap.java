/*
 * Michael Mussler 9/25/2016
 */


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author michaelmussler
 */
public class SwitchMap {
public static Map<String, String> getMyMap(Map<String, String> m){
       Map<String, String> map2 = new HashMap<String, String>();
       for(Entry<String, String> Map:m.entrySet()){
            map2.put(Map.getValue(), Map.getKey());
       }
       return map2;
}
public static void main(String[] args) {
    Map<String, String> map1 = new HashMap<String, String>();
    map1.put("First", "Second");
    map1.put("Third", "Fourth");
    map1.put("Fifth", "Sixth");
    getMyMap(map1);
    System.out.println( getMyMap( map1 ) );
}
}