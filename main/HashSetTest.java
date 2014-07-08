package main;

import java.util.*;

/**
 * Created by huidong on 7/3/14.
 */
public class HashSetTest {
    public static void main(String[] args) {
        HashMap<String, Integer> javaKeysMap = new HashMap<String, Integer>();
        javaKeysMap.put("import", 0);

        Iterator iterator = javaKeysMap.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            System.out.println((String)entry.getKey() + ": " +(Integer)entry.getValue());
        }

    }
}
