package main;

import java.io.*;
import java.util.*;

/**
 * Created by huidong on 7/3/14.
 * Count java keywords in a directory.
 */
public class CountJavaKeywords {
    boolean isComment = false;
    HashMap<String, Integer> javaKeysMap = new HashMap<String, Integer>();

    String[] javaKeywords = {"abstract","assert","boolean","break","byte","case","catch","char","class","const",
            "continue","default","do","double","else","enum","extends","final","finally","float","for",
            "if","implements","import","instanceof","int","interface","long","native","new","package",
            "private","protected","public","return","short","static","strictfp","super","switch","synchronized",
            "this","throw","throws","transient","try","void","volatile","while"};

    public void putKeysToMap() {
        for ( String s:javaKeywords) {
            javaKeysMap.put(s, 0);
        }
    }

    public void printJavaKeysMap() {
        for (Map.Entry<String, Integer> keyCountEntry : javaKeysMap.entrySet()) {
            Map.Entry entry = (Map.Entry) keyCountEntry;
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void javaKeysInADirectory(String path) {
        //for each file or directory
        File dir = new File(path);
        File[] files = dir.listFiles();

        for ( File f:files){
            if(f.isDirectory()) {
                javaKeysInADirectory(f.getAbsolutePath());
            }else {
                try {
                    javaKeysInAFile(f.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void javaKeysInAFile(String path) throws IOException {
        FileReader filereader = new FileReader(path);
        BufferedReader bufferedreader = new BufferedReader(filereader);

        String line;
        while ((line = bufferedreader.readLine()) != null) {
            //exclude comment line and blank line
            if(!isEmptyLine(line) && !isCommentLine(line)) {
                //System.out.println(line);
                String[] words = line.replaceAll("\"[^\"]*\"", "").trim().split("[^0-9a-zA-Z]+");
                for ( String s:words) {
                    //System.out.println(s[i]);
                    if(javaKeysMap.containsKey(s)) {
                        javaKeysMap.put(s, javaKeysMap.get(s) + 1 );
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
        if(isComment && line.trim().matches(".*\\*/$")) {
            isComment = false;
        }
        if(!isComment && line.trim().matches("^/\\*.*")) {
            isComment = true;
        }

        return isComment || line.trim().matches("^//.*");
    }

    public void run(String path) {
        putKeysToMap();
        javaKeysInADirectory(path);
        printJavaKeysMap();
    }

    public static void main(String args[]) {
        CountJavaKeywords countJavaKeys = new CountJavaKeywords();
        countJavaKeys.run("D:\\IdeaProject\\Small\\src");
    }
}
