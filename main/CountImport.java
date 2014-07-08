package main;

import java.io.*;
import java.util.regex.*;

/**
 * Created by huidong on 7/3/14.
 */
public class CountImport {
    int importCount = 0;
    private boolean isComment = false;

    public void javaInADirectory(String path) {
        //for each file or directory
        File dir = new File(path);
        File[] files = dir.listFiles();
        for ( int i = 0; i < files.length; i++) {
            if(files[i].isDirectory()) {
                javaInADirectory(files[i].getAbsolutePath());
            }else {
                try {
                    keywordInAFile(files[i].getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("'import' appears " + importCount + " times in files " + path);
    }

    public void keywordInAFile(String path) throws IOException {
        FileReader filereader = new FileReader(path);
        BufferedReader bufferedreader = new BufferedReader(filereader);

        Pattern pattern = Pattern.compile("\".*\"");

        String line = null;
        while ((line = bufferedreader.readLine()) != null) {
            //exclude comment line and blank line
            if(!isEmptyLine(line) && !isCommentLine(line)) {
//                System.out.println(line);
                String[] s = line.replaceAll("\"[^\"]*\"", "").trim().split("[^0-9a-zA-Z]+");
                for (int i = 0; i < s.length; i++) {
//                    System.out.println(s[i]);
                    if(s[i].equals("true")) {
                        importCount++;
                    }
                }
            }
        }
        System.out.println("'true' appears " + importCount + " times in files " + path);
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
        CountImport countimport = new CountImport();
//        try {
//            countimport.keywordInAFile("D:\\IdeaProject\\Small\\src\\CountImport.java");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        countimport.javaInADirectory("D:\\IdeaProject\\Small\\src");
    }

}
