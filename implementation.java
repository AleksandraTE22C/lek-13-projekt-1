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
        int atk1 = roll.nextInt(15);
        int atk2 = roll.nextInt(25);
        int atk3 = roll.nextInt(50);
        System.out.print("Enter player name: ");
        String name = enter.next();
        int score = 30;

        while(pHealth > 0){
            System.out.println("Initial score: "+score);

            

        }


    }

}

