package main;

import java.io.*;
import java.util.regex.*;

/**
 * Created by huidong on 7/2/14.
 */
public class CountKeyword {
    static int importCount = 0;
    private static boolean isComment = false;

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
                System.out.println(line);
                String[] s = line.replaceAll("\"[^\"]*\"", "").trim().split("[^0-9a-zA-Z]+");
                for (int i = 0; i < s.length; i++) {
                    System.out.println(s[i]);
                    if(s[i].equals("import")) {
                        importCount++;
                    }
                }
            }
            //exclude strings in "" or ''
            //split line with none characters

            //if words are keyword, count them.


//            String newline = line.replaceAll("\".*\"", "");
//            System.out.println(newline);
            /*Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                System.out.println(line);
//                String[] s = line.split("\"[^\"]*\"");
                String[] s = line.replaceAll("\"[^\"]*\"", "").trim().split("[^0-9a-zA-Z]+");
                for (int i = 0; i < s.length; i++) {
                    System.out.println(s[i]);
                }
            }*/
        }
        System.out.println("'import' appears " + importCount + " times in files " + path);
        bufferedreader.close();
        filereader.close();
    }

    public boolean isEmptyLine(String line) {
        return line.trim().matches("^$");
    }

    public boolean isCommentLine(String line) {
        return false;
    }

    public static void main(String args[]) {
        CountKeyword countkeyword = new CountKeyword();
        try {
            countkeyword.keywordInAFile("D:\\IdeaProject\\Small\\src\\CountKeyword.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
