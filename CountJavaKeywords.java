import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * Created by huidong on 7/3/14.
 */
public class CountJavaKeywords {
    int importCount = 0;
    boolean isComment = false;
    HashMap<String, Integer> javaKeysMap = new HashMap<String, Integer>();

    String[] javaKeywords = {"abstract","assert","boolean","break","byte","case","catch","char","class","const",
            "continue","default","do","double","else","enum","extends","final","finally","float","for",
            "if","implements","import","instanceof","int","interface","long","native","new","package",
            "private","protected","public","return","short","static","strictfp","super","switch","synchronized",
            "this","throw","throws","transient","try","void","volatile","while"};

    public void putKeysToMap() {
        for ( int i = 0; i < javaKeywords.length; i++) {
            javaKeysMap.put(javaKeywords[i], 0);
        }
    }

    public void printJavaKeysMap() {
        Iterator iterator = javaKeysMap.entrySet().iterator();//Iterator
        while(iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            System.out.println((String)entry.getKey() + ": " +(Integer)entry.getValue());
        }
    }

    public void javaKeysInADirectory(String path) {
        putKeysToMap();
        //for each file or directory
        File dir = new File(path);
        File[] files = dir.listFiles();
        for ( int i = 0; i < files.length; i++) {
            if(files[i].isDirectory()) {
                javaKeysInADirectory(files[i].getAbsolutePath());
            }else {
                try {
                    javaKeysInAFile(files[i].getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        printJavaKeysMap();
    }

    public void javaKeysInAFile(String path) throws IOException {
        FileReader filereader = new FileReader(path);
        BufferedReader bufferedreader = new BufferedReader(filereader);

        String line = null;
        while ((line = bufferedreader.readLine()) != null) {
            //exclude comment line and blank line
            if(!isEmptyLine(line) && !isCommentLine(line)) {
//                System.out.println(line);
                String[] s = line.replaceAll("\"[^\"]*\"", "").trim().split("[^0-9a-zA-Z]+");
                for (int i = 0; i < s.length; i++) {
//                    System.out.println(s[i]);
                    if(javaKeysMap.containsKey(s[i])) {
                        javaKeysMap.put(s[i], javaKeysMap.get(s[i]) + 1 );
                    }
                }
            }
        }

        bufferedreader.close();
        filereader.close();
    }

    public boolean isEmptyLine(String line) {
        return line.trim().matches("^$");
    }

    public boolean isCommentLine(String line) {
        //comment line within '/* */'
        if(isComment) {
            if (line.trim().matches(".*\\*/$")) {
                isComment = false;
            }
            return true;
        }
        //comment line begin with '//'
        else {
            if (line.trim().matches("^//.*")) {
                return true;
            } else {
                if (line.trim().matches("^/\\*.*")) {
                    isComment = true;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        CountJavaKeywords countJavaKeys = new CountJavaKeywords();
        countJavaKeys.javaKeysInADirectory("D:\\IdeaProject\\Small\\src");
    }
}
