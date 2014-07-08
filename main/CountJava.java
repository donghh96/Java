package main;

import java.io.*;
import java.util.regex.*;

/**
 * Created by huidong on 7/2/14.
 */
public class CountJava {

    static int javaCount = 0;

    public void javaInADirectory(String path) {
        //for each file or directory
        File dir = new File(path);
        File[] files = dir.listFiles();
        for ( int i = 0; i < files.length; i++) {
            if(files[i].isDirectory()) {
                javaInADirectory(files[i].getAbsolutePath());
            }else {
                try {
                    javaInAFile(files[i].getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("'public' appears " + javaCount + " times in files " + path);
    }

    public void javaInAFile(String path) throws IOException {
        FileReader filereader = new FileReader(path);
        BufferedReader bufferedreader = new BufferedReader(filereader);

        Pattern pattern = Pattern.compile("public");

        String line = null;
        while ((line = bufferedreader.readLine()) != null) {
            //System.out.println(line);
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()) {
                javaCount++;
            }

        }

        bufferedreader.close();
        filereader.close();
    }

    public static void main(String args[]) {
        CountJava countJava = new CountJava();
            countJava.javaInADirectory("D:\\IdeaProject\\Small\\src");
    }
}
