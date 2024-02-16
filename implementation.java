import java.util.Random;
import java.util.Scanner;

public class implementation{
    public static void main (String[] args) throws InterruptedException{
        Scanner enter = new Scanner(System.in);
        int pHealth = 100;
        int mHealth1 = 20;
        int mHealth2 = 40;
        int mHealth3 = 60;
        int mHealth4 = 100;
        int round = 0;
        System.out.print("Enter player name: ");
        String name = enter.next();
        int score = 40;
        System.out.println("Your turn");

        while(pHealth > 0){
            while(mHealth1 > 0 && score > 14){
            round++;
            Thread.sleep(800);
            System.out.println("Round "+round);
            System.out.println("How will you attack?");
            System.out.println("Monster's HP: " + mHealth1);
            System.out.println("Your HP: "+pHealth);
            System.out.println("Points: "+score);
            Thread.sleep(500);
            System.out.println("1. Throw rock (-15 points)");
            System.out.println("2. Cast Freeze(-25 points)");
            System.out.println("3. Cast Fireball (-50 points)");
            int atk = enter.nextInt();
            int atk1 = roll.nextInt(25);
            int atk2 = roll.nextInt(50);
            int atk3 = roll.nextInt(75);

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
                else if(atk == 2 && score > 24){
                    score -= 25;
                    if(atk2 > 0){
                        System.out.println("You did "+atk2+" damage!");
                        mHealth1 -= atk2;
                    }
                    else{
                        System.out.println("You missed!");
                    }
                }
                else if(atk == 3 && score > 49){
                    score -= 50;
                        if(atk3 > 0){
                            System.out.println("You did "+atk2+" damage!");
                            mHealth1 -= atk3;
                        }
                        else{
                            System.out.println("You missed!");
                        }
                }

                if (mHealth1 <= 0){
                    System.out.println("Monster defeated!");
                    score += 50;
                    round = 0;
                    Thread.sleep(800);
                    System.out.println("A new monster appeared!");
                    System.out.println("Your turn");
                    break;
                }
                
            Thread.sleep(800);
            System.out.println("Enemys turn");
            int mAtk1= roll.nextInt(30);
            if(mAtk1 > 0){
                System.out.println("The monster did "+mAtk1+" damage!");
                pHealth -= mAtk1;
                System.out.println("Your turn");
            }
            else{
                System.out.println("The monster missed!");
                System.out.println("Your turn");
             }
            if(pHealth <= 0){
                System.out.println("GAME OVER");
                break;
            }
            if(score < 15){
                System.out.println("Not enough points!");
                System.out.println("GAME OVER");
                break;
            }
        }
    }
}
static int calcDmg(int atk) {
    Random roll = new Random();
    int dmg = 0;
    switch (atk) {
        case 1:
            dmg = score > 14 ? roll.nextInt(25) : System.out.println("Ran out of points!");
            break;
        case 2:
            dmg = score > 24 ? roll.nextInt(50) : System.out.println("Ran out of points!");
            break;
        case 3:
            dmg = score > 49 ? roll.nextInt(75) : System.out.println("Ran out of points!");
            break;
    }
    return dmg;
}
}