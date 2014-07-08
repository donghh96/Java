package main;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by huidong on 7/1/14.
 */
public class GuessNumber {
    private int aRandomNumber;

    private GuessNumber() {
        this.aRandomNumber = randomNumber();
    }
    private int randomNumber() {
        Random random = new Random();
        return random.nextInt(100);
    }

    public void guessNumber() {
        boolean incorrect = true;
        int n;
        while(incorrect) {
            n = getInputNumber();
            if (aRandomNumber == n) {
                incorrect = false;
            } else if(aRandomNumber > n) {
                System.out.println("Try a bigger one!");
            } else {
                System.out.println("Try a smaller one!");
            }
        }
        System.out.println("Congratulations, you got the correct number " + aRandomNumber);
    }

    private int getInputNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input a integer between 0 and 100.");
        return input.nextInt();
    }

    public static void main(String args[]) {
        GuessNumber guess = new GuessNumber();
        guess.guessNumber();
    }
}
