import java.util.Random;
import java.util.Scanner;

public class implementation{
    public static void main (String[] args) throws InterruptedException{
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
        int score = 40;
        System.out.println("Initial points: "+score);

        while(pHealth > 0 ){
            while(score>=15){
            while(mHealth1 > 0){
            round++;
            Thread.sleep(800);
            System.out.println("Round "+round);
            System.out.println("Your turn");
            System.out.println("How will you attack?");
            System.out.println("Monster's HP: " + mHealth1);
            System.out.println("Points: "+score);
            Thread.sleep(500);
            System.out.println("1. Throw rock (-15 points)");
            System.out.println("2. Cast Freeze(-25 points)");
            System.out.println("3. Cast Fireball (-50 points)");
            int atk = enter.nextInt();
            int atk1 = roll.nextInt(15);
            int atk2 = roll.nextInt(25);
            int atk3 = roll.nextInt(50);

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
                        else{
                            System.out.println("You missed!");
                        }
                }
                
            Thread.sleep(800);
            System.out.println("Enemys turn");
            int mAtk1= roll.nextInt(30);
            if(mAtk1 > 0){
                System.out.println("The monster did "+mAtk1+" damage!");
                pHealth -= mAtk1;
            }
            else{
                System.out.println("The monster missed!");
             }
            if (mHealth1 <= 0){
                System.out.println("Monster defeated!");
                score += 40;
                round = 0;
                Thread.sleep(800);
                break;
            }
            if(pHealth <= 0){
                System.out.println("GAME OVER");
                break;
            }
            if(score < 15 && mHealth1 > 0){
                System.out.println("Not enough points!");
                System.out.println("GAME OVER");
                break;
            }
        }

            while(mHealth2 > 0 && mHealth1 <= 0){
                System.out.println("A new monster appeared!");
                round++;
                Thread.sleep(800);
                System.out.println("Round "+round);
                System.out.println("Your turn");
                System.out.println("How will you attack?");
                System.out.println("Monster's HP: " + mHealth2);
                System.out.println("Points: "+score);
                Thread.sleep(500);
                System.out.println("1. Throw rock (-15 points)");
                System.out.println("2. Cast Freeze(-25 points)");
                System.out.println("3. Cast Fireball (-50 points)");
                int atk = enter.nextInt();
                int atk1 = roll.nextInt(15);
                int atk2 = roll.nextInt(25);
                int atk3 = roll.nextInt(50);
    
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
                            else{
                                System.out.println("You missed!");
                            }
                    }
                Thread.sleep(800);
                if(mHealth2>0){
                System.out.println("Enemys turn");
                int mAtk2= roll.nextInt(40);
                if(mAtk2 > 0){
                    System.out.println("The monster did "+mAtk2+" damage!");
                    pHealth -= mAtk2;
                }
                else{
                    System.out.println("The monster missed!");
                 }
                }
                if (mHealth2 <= 0){
                    System.out.println("Monster defeated!");
                    score += 50;
                    break;
                }
                if(pHealth <= 0){
                    System.out.println("GAME OVER");
                    break;
                }
                if(score < 15 && mHealth2 > 0){
                    System.out.println("Not enough points!");
                    System.out.println("GAME OVER");
                    break;
                }

        }
        if(mHealth4 <= 0){
            System.out.println("You Won!");
            break;
        }
    }
}
    }
}
