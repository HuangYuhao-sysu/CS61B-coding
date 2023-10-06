package byow.lab13;

import edu.princeton.cs.introcs.StdDraw;
import org.junit.Test;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) throws InterruptedException {
        //if (args.length < 1) {
        //    System.out.println("Please enter a seed");
        //    return;
        //}

        //int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40);
        game.startGame();
    }

    public MemoryGame(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        this.rand = new Random();
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
    }

    public String generateRandomString(int n) {
        String s = "";
        for (int i = 0; i < n; i += 1) {
            s += CHARACTERS[rand.nextInt(0, 26)];
        }
        return s;
    }

    public void drawFrame(String s, String stage, String encourage) {
        //TODO: Take the string and display it in the center of the screen
        Font font = new Font("Monaco", Font.BOLD, 20);
        StdDraw.clear();
        StdDraw.setFont(font);
        StdDraw.text(this.width/2,this.height/2,s);
        StdDraw.textLeft(0,this.height - 1,"Round: " + this.round);
        StdDraw.text(this.width/2, this.height - 1, stage);
        StdDraw.textRight(this.width, this.height - 1, encourage);
        StdDraw.show();
        //TODO: If game is not over, display relevant game information at the top of the screen
    }

    public void flashSequence(String letters) throws InterruptedException {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        String en = ENCOURAGEMENT[rand.nextInt(0,7)];
        for (int i = 0; i < letters.length(); i += 1) {
            char c = letters.charAt(i);
            drawFrame(Character.toString(c), "Watch!", en);
            Thread.sleep(1000);
            drawFrame("", "Watch!", en);
            Thread.sleep(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String en = ENCOURAGEMENT[rand.nextInt(0,7)];
        int number = n;
        String s = "";
        while (number != 0) {
            if (StdDraw.hasNextKeyTyped()) {
                s += StdDraw.nextKeyTyped();
                number -= 1;
            }
            drawFrame(s, "Type!", en);
        }
        return s;
    }

    public void startGame() throws InterruptedException {
        //TODO: Set any relevant variables before the game starts
        Boolean end = false;
        String randomS;
        String playerS;
        round = 1;
        //TODO: Establish Engine loop
        while (true) {
            if (end) {
                drawFrame("Game Over!", "WOO!", "OH!");
                Thread.sleep(500);
                drawFrame("You made it to round: " + round, "SAD!", "OH!");
                break;
            } else {
                drawFrame("Round: " + round, "Watch!", "SHIT!");
                Thread.sleep(500);
                randomS = generateRandomString(round);
                flashSequence(randomS);
                playerS = solicitNCharsInput(round);
                if (playerS.equals(randomS)) {
                    end = false;
                    round += 1;
                } else {
                    end = true;
                }
            }
        }
    }
}
