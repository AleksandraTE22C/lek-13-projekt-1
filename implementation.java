import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class implementation{
    static Random roll = new Random();
    static Scanner enter = new Scanner(System.in);
    static int pHealth = 100;
    static int round = 0;
    static Map<String, Integer> leaderboard = new HashMap<>();
    public static void main (String[] args) throws InterruptedException{
        System.out.print("Enter player name: ");
        String name = enter.next();
        System.out.println("Your turn");
        int score = 40;


    }

    static int battle(int mHealth, int scorePlus, int score) throws InterruptedException {
        while (pHealth > 0 && mHealth > 0 && score > 14) {
            round++;
            Thread.sleep(800);
            System.out.println("Round " + round);
            System.out.println("How will you attack?");
            System.out.println("Monster's HP: " + mHealth);
            System.out.println("Your HP: " + pHealth);
            System.out.println("Points: " + score);
            Thread.sleep(500);
            System.out.println("1. Throw rock (-15 points)");
            System.out.println("2. Cast Freeze(-25 points)");
            System.out.println("3. Cast Fireball (-50 points)");
            int atk = enter.nextInt();
            int damage = calcDmg(atk, score);

            score -= damage > 0 ? damage : 0;
            if (damage > 0) {
                System.out.println("You did " + damage + " damage!");
                mHealth -= damage;
            } else {
                System.out.println("You missed!");
            }

            if (mHealth <= 0) {
                System.out.println("Monster defeated!");
                score += scorePlus;
                round = 0;
                Thread.sleep(800);
                System.out.println("A new monster appeared!");
                System.out.println("Your turn");
                break;
            }

            Thread.sleep(800);
            System.out.println("Enemy's turn");
            int mAtk = roll.nextInt(30) + 1;
            System.out.println("The monster did " + mAtk + " damage!");
            pHealth -= mAtk;
            if (pHealth <= 0) {
                System.out.println("GAME OVER");
                break;
            }
        }
        return score;
    }

    static int calcDmg(int atk, int score) {
        Random roll = new Random();
        int dmg = 0;
        switch (atk) {
            case 1:
                if (score > 14) {
                    score -= 15;
                    dmg = roll.nextInt(25);
                } else {
                    System.out.println("Ran out of points!");
                }
                break;

            case 2:
                if (score > 24) {
                    score -= 25;
                    dmg = roll.nextInt(50);
                } else {
                    System.out.println("Ran out of points!");
                }
                break;

            case 3:
                if (score > 49) {
                    score -= 50;
                    dmg = roll.nextInt(75);
                } else {
                    System.out.println("Ran out of points!");
                }
                break;
            }
        return dmg;
        }
}