import java.util.Random;
import java.util.Scanner;

public class implementation{
    public static void main (String[] args){
        Random roll = new Random();
        Scanner enter = new Scanner(System.in);
        int pHealth = 100;
        int mHealth1 = 20;
        int mHealth2 = 35;
        int mHealth3 = 50;
        int mHealth4 = 70;
        int round = 0;
        System.out.print("Enter player name: ");
        String name = enter.next();
        int score = 30;
        System.out.println("Initial score: "+score);

        while(pHealth > 0 && mHealth1 > 0 && mHealth2 > 0 && mHealth3 > 0 && mHealth4 > 0){
            if(score > 0){
            while(mHealth1 > 0){
            round++;
            System.out.println("Round "+round);
            System.out.println("Your turn");
            System.out.println("How will you attack?");
            System.out.println("1. Throw rock  2. Cast Freeze  3. Cast Fireball");
            int atk = enter.nextInt();
            int atk1 = roll.nextInt(15);
            int atk2 = roll.nextInt(25);
            int atk3 = roll.nextInt(50);
            int mAtk1= roll.nextInt(10);

            if(atk == 1){
                score -= 15;
                        if(atk > 0){
                        System.out.println("You did "+atk1+" damage!");
                        mHealth1 -= atk1;
                    }
                    else{
                        System.out.println("You missed!");
                    }
                }
                else if(atk == 2){
                    score -= 25;
                        if(atk2 > 0){
                        System.out.println("You did "+atk2+" damage!");
                        mHealth1 -= atk2;
                    }
                    else{
                        System.out.println("You missed!");
                    }
                }
                else if(atk == 3){
                    score -= 50;
                        if(atk3 > 0){
                            System.out.println("You did "+atk2+" damage!");
                            mHealth1 -= atk3;
                        }
                }
             System.out.println("Enemys turn!");
            }
           
        }
        if(pHealth <= 0){
            System.out.println("GAME OVER");
            break;
        }
        if(mHealth4 <= 0){
            System.out.println("You Won!");
            break;
        } 
    }
        
    }
}

