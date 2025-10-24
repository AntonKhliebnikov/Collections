package collection;

import collection.my_map.MyHashMap;
import collection.my_map.MyMap;

import java.util.Arrays;

public class CollectionsTest {
    public static void main(String[] args) {
        MyMap map = new MyHashMap();
        map.put("Anton", 39 );
        map.put("Kris", 29 );
        map.put("Polya", 41 );
        map.put("Karyna", 26 );
        map.put("Stesha", 25 );
        map.put("Anya", 40 );
        map.put("Anya", 41 );
        System.out.println("map.size() = " + map.size());
        System.out.println(map);

        System.out.println("map.keyArray() = " + Arrays.toString(map.keyArray()));
        System.out.println("map.valueArray() = " + Arrays.toString(map.valueArray()));
        System.out.println("map.get(\"Anton\") = " + map.get("Anton"));
        System.out.println("map.remove(\"Kris\") = " + map.remove("Kris"));
        System.out.println(map);
        System.out.println("map.size() = " + map.size());
    }
}
