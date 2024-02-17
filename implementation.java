import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

class dmgResult {
    int score;
    int dmg;

    public dmgResult(int score, int dmg) {
        this.score = score;
        this.dmg = dmg;
    }
}

public class implementation{
    static Random roll = new Random();
    static Scanner enter = new Scanner(System.in);
    static int pHealth = 100;
    static int round = 0;
    static Map<String, Integer> leaderboard = new HashMap<>();

    public static void main (String[] args) throws InterruptedException{
        int score = 50;
        System.out.print("Enter player name: ");
        String name = enter.next();

        while(pHealth>0){
        score = battle(25, 55, score);
        score = battle(35, 70, score);
        score = battle(50, 100, score);
        if(pHealth > 0 && score > 14){
            System.out.println("BOSS INCOMING");
        }
        score = bossBattle(100, 100, score);
        if (score < 15 && pHealth > 0) {
            System.out.println("You ran out of points!");
            break;
        }
        if(pHealth < 0){
            System.out.println("GAME OVER");
            break;
        }
        }
    }

    static int battle(int mHealth, int scorePlus, int score) throws InterruptedException {
        while (pHealth > 0 && mHealth > 0 && score > 14) {
            round++;
            Thread.sleep(800);
            System.out.println("Your turn");
            System.out.println("Round " + round);
            System.out.println("How will you attack?");
            System.out.println("Monster's HP: " + mHealth);
            System.out.println("Your HP: " + pHealth);
            System.out.println("Points: " + score);
            Thread.sleep(500);
            System.out.println("1. Zap (-15 points)");
            System.out.println("2. Cast Freeze(-25 points)");
            System.out.println("3. Cast Fireball (-35 points)");
            System.out.println("4. +20 HP (-20 points)");
            int atk = enter.nextInt();

            if (atk == 4) {
                if (score >= 20) {
                    score -= 20;
                    pHealth += 20;
                    if (pHealth > 100) {
                        pHealth = 100;
                    }
                    System.out.println("You regenerated 20 HP!");
                } else {
                System.out.println("Not enough points!");
                }
                } else {
                  dmgResult result = calcDmg(atk, score);
                   score = result.score;
                  int dmg = result.dmg;

                   if (dmg > 0) {
                       System.out.println("You did " + dmg + " damage!");
                       mHealth -= dmg;
                  } else {
                     System.out.println("You missed!");
                   }

                    if (mHealth <= 0) {
                        System.out.println("Monster defeated!");
                        score += scorePlus;
                        round = 0;
                        Thread.sleep(800);
                        System.out.println("A new monster appeared!");
                        break;
                    }

                    Thread.sleep(800);
                    System.out.println("Enemy's turn");
                    int mAtk = roll.nextInt(30) + 1;
                    System.out.println("The monster did " + mAtk + " damage!");
                    pHealth -= mAtk;
                }
            } return score;
        }

    static int bossBattle(int bossHealth, int scorePlus, int score) throws InterruptedException {
        while (pHealth > 0 && bossHealth > 0 && score > 14) {
            round++;
            Thread.sleep(800);
            System.out.println("Your turn");
            System.out.println("Round " + round);
            System.out.println("How will you attack?");
            System.out.println("BOSS's HP: " + bossHealth);
            System.out.println("Your HP: " + pHealth);
            System.out.println("Points: " + score);
            Thread.sleep(500);
            System.out.println("1. Zap (-15 points)");
            System.out.println("2. Cast Freeze(-25 points)");
            System.out.println("3. Cast Fireball (-35 points)");
            System.out.println("4. +20 HP (-20 points)");
            int atk = enter.nextInt();

            if (atk == 4) {
                if (score >= 20) {
                    score -= 20;
                    pHealth += 20;
                    if (pHealth > 100) {
                        pHealth = 100;
                    }
                    System.out.println("You regenerated 20 HP!");
                } else {
                    System.out.println("Not enough points!");
                }
            } else {
                dmgResult result = calcDmg(atk, score);
                score = result.score;
                int dmg = result.dmg;

                if (dmg > 0) {
                    System.out.println("You did " + dmg + " damage!");
                    bossHealth -= dmg;
                } else {
                    System.out.println("You missed!");
                }

                if (bossHealth <= 0) {
                    System.out.println("BOSS defeated!");
                    System.out.println("YOU WIN");
                    score += scorePlus;
                    round = 0;
                    break;
                }

                Thread.sleep(800);
                System.out.println("BOSS's turn");
                int mAtk = roll.nextInt(30) + 1;
                System.out.println("The BOSS did " + mAtk + " damage!");
                pHealth -= mAtk;
            }   
        }
        return score;
    }


    static dmgResult calcDmg(int atk, int score) {
        int updScore = score;
        int dmg = 0;
        switch (atk) {
            case 1:
                if (score > 14) {
                    updScore -= 15;
                    dmg = roll.nextInt(25);
                    if(dmg > 0 && dmg < 25){
                        dmg += 5;
                        if(dmg > 25){
                            dmg = 25;
                        }
                    }
                } else {
                    System.out.println("Not enough points!");
                }
                break;
    
            case 2:
                if (score > 24) {
                    updScore -= 25;
                    dmg = roll.nextInt(35);
                    if(dmg > 0 && dmg < 35){
                        dmg += 10;
                        if(dmg > 35){
                            dmg = 35;
                        }
                    }
                } else {
                    System.out.println("Not enough points!");
                }
                break;
    
            case 3:
                if (score > 35) {
                    updScore -= 35;
                    dmg = roll.nextInt(45);
                    if(dmg > 0 && dmg < 45){
                        dmg += 15;
                        if(dmg > 45){
                            dmg = 45;
                        }
                    }
                } else {
                    System.out.println("Not enough points!");
                }
                break;
        }
        return new dmgResult(updScore, dmg);
    }
}