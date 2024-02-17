import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Class to represent the result created with the values from calcDmg
class dmgResult {
    int score;
    int dmg;

    //Initialises the score and damage values
    public dmgResult(int score, int dmg) {
        this.score = score;
        this.dmg = dmg;
    }
}

//Main class for game functionality
public class fighting_game{
    private static final String board = "leaderboard.txt"; //Defines the file path as constant
    static Random roll = new Random(); //Initialises random number generator
    static Scanner enter = new Scanner(System.in);
    static boolean gameWon = false; //Starts the game with gameWon set to false
    static int pHealth = 100;
    static int round = 0;
    static Map<String, Integer> leaderboard = new HashMap<>(); //Initialises leaderboard to store player names and scores
    
    //Main method to run the game
    public static void main (String[] args) throws InterruptedException{ 
        loadLeaderboard(); //Loads the leaderboard so the scores don't get overwritten
        System.out.print("Enter player name: ");
        String name = enter.next(); //Lets the player enter their name
        int score = 50; //The starting points meant to be used for attacks

        //Loop until players health reaches 0 or game is won
        while(pHealth>0 && !gameWon){

        // Monster battles
        score = battle(25, 55, score);
        score = battle(35, 70, score);
        score = battle(50, 100, score);

        //Check if boss battle can be triggered
        if(pHealth > 0 && score > 14){
            System.out.println("BOSS INCOMING");
        }

        //Boss battle
        score = bossBattle(100, 100, score);

        //Check if there are enough points to pick an option, end game if there aren't
        if (score < 15 && pHealth > 0) {
            System.out.println("You ran out of points!");
            break;
        }

        //Check if the players HP is less than 1, end game if it is
        if(pHealth < 1){
            System.out.println("GAME OVER");
            break;
        }
    }
    
    //Space and pause for readability
    System.out.println();
    Thread.sleep(800);

    //Check if the game has been won
    if (gameWon) {
        updateLeaderboard(name, score); //Updates the leaderboard with the players name and score
        displayLeaderboard(); //Displays the leaderboard
        saveLeaderboard(); //Save leaderboard data to the text file
    }
}

    //Method to update the leaderboard
    static void updateLeaderboard(String name, int score) {

        //Check if the players name is already on the leaderboard
        if (leaderboard.containsKey(name)) {

            //If the player exists get their new score and update it
            int currentScore = leaderboard.get(name);
            leaderboard.put(name, currentScore + score);

        } else {
            //If the player doesn't exist, put their name and score on the leaderboard
            leaderboard.put(name, score);
        }
    }

    //Method to display the leaderboard
    static void displayLeaderboard() {

        //Print the leaderboard header
        System.out.println("---------------------------------------------");
        System.out.println("\t\tLEADERBOARD");
        System.out.println("---------------------------------------------");
        System.out.println("Player Name\t\t\tScore");

        //Sort leaderboard entries from highest score to lowest
        List<Map.Entry<String, Integer>> highScores = leaderboard.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .collect(Collectors.toList());

        //Print each players name and score from the text file
        for (Map.Entry<String, Integer> entry : leaderboard.entrySet()) {
            System.out.println(entry.getKey() + "\t\t\t\t" + entry.getValue());
        }
    }

        //Saves the new leaderboard data
        static void saveLeaderboard() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(board))) {

            //Write the leaderboard entry in the format 'name, score'
            for (Map.Entry<String, Integer> entry : leaderboard.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }

        //Print the stack trace if an IOException occurs
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Loads data from the leaderboard.txt file
    static void loadLeaderboard() {
        try (BufferedReader reader = new BufferedReader(new FileReader(board))) {
            String line;

            //Read each line from the file
            while ((line = reader.readLine()) != null) {

                //Split the lines into name and score parts
                String[] parts = line.split(",");

                //Add name and score to the leaderboard map
                leaderboard.put(parts[0], Integer.parseInt(parts[1]));
            }

        //Print the stack trace if an IOException occurs
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method for the normal battles
    static int battle(int mHealth, int scorePlus, int score) throws InterruptedException {

        //Loop while the players and monster's health is above 0 and while the score is higher than 14
        while (pHealth > 0 && mHealth > 0 && score > 14) {
            round++; //Adds 1 to the round variable every time it loops
            Thread.sleep(800); //Pause for readability

            //Prints all the values the player needs to know
            System.out.println("Your turn");
            System.out.println("Round " + round);
            System.out.println("How will you attack?");
            System.out.println("Monster's HP: " + mHealth);
            System.out.println("Your HP: " + pHealth);
            System.out.println("Points: " + score);
            Thread.sleep(500); //Pause for readability
            System.out.println("1. Zap (-15 points)");
            System.out.println("2. Cast Freeze(-25 points)");
            System.out.println("3. Cast Fireball (-35 points)");
            System.out.println("4. +20 HP (-20 points)");
            int atk = enter.nextInt(); //Write a number on a scale of 1-4

            //Check if the player chose option 4
            if (atk == 4) {

                //Check if there are enough points to do this action
                if (score >= 20) {
                    score -= 20; //Remove 20 points from players score
                    pHealth += 20; //Add 20 HP

                    //If players health exceeds the max it will be automatically set to 100
                    if (pHealth > 100) {
                        pHealth = 100;
                    }
                    System.out.println("You regenerated 20 HP!");
                } else {
                System.out.println("Not enough points!"); //Print message if there are not enough points
                }

                //If the player picked options 1-3
                } else {
                    dmgResult result = calcDmg(atk, score); //Calculate the damage and score based on the players input
                    score = result.score; //Result of score after calculation
                    int dmg = result.dmg; //Result of damage after calculation

                    //Check if the number rolled in the calcDmg method is more than 0
                    if (dmg > 0) {
                        System.out.println("You did " + dmg + " damage!"); //Display the damage done
                        mHealth -= dmg; //Updates the monsters health

                    //Prints 'You missed' if dmg is 0 or less
                    } else {
                        System.out.println("You missed!"); 
                    }

                    //Check if the monsters HP is 0 or lower
                    if (mHealth <= 0) {
                        System.out.println("Monster defeated!");
                        score += scorePlus; //Add points to total score
                        round = 0; //Reset rounds for next battle
                        Thread.sleep(800); //Pause for readbility
                        System.out.println("A new monster appeared!");
                        break;
                    }

                    //Monsters's attack
                    Thread.sleep(800);//Pause for readability
                    System.out.println("Monster's turn");
                    int mAtk = roll.nextInt(30) + 1; //Randomly generate the value of the monster's attack and add 1 so it never misses
                    System.out.println("The monster did " + mAtk + " damage!"); //Display the damage done
                    pHealth -= mAtk; //Update player's HP
                }
            } 
            return score; //Return updated score
        }

    //Method for the boss battle
    static int bossBattle(int bossHealth, int scorePlus, int score) throws InterruptedException {
        
        //Loop while the player's and boss's health is above 0 and while the score is higher than 14
        while (pHealth > 0 && bossHealth > 0 && score > 14) {
            round++; //Adds 1 to the round variable every time it loops
            Thread.sleep(800); //Pause for readability

            //Prints all the values the player needs to know
            System.out.println("Your turn");
            System.out.println("Round " + round);
            System.out.println("How will you attack?");
            System.out.println("BOSS's HP: " + bossHealth);
            System.out.println("Your HP: " + pHealth);
            System.out.println("Points: " + score);
            Thread.sleep(500); //Pause for readability
            System.out.println("1. Zap (-15 points)");
            System.out.println("2. Cast Freeze(-25 points)");
            System.out.println("3. Cast Fireball (-35 points)");
            System.out.println("4. +20 HP (-20 points)");
            int atk = enter.nextInt(); //Write a number on a scale of 1-4

            //Check if the player chose option 4
            if (atk == 4) {

                //Check if there are enough points to do this action
                if (score >= 20) {
                    score -= 20; //Remove 20 points from players score
                    pHealth += 20; //Add 20 HP

                    //If players health exceeds the max it will be automatically set to 100
                    if (pHealth > 100) {
                        pHealth = 100;
                    }
                    System.out.println("You regenerated 20 HP!");
                } else {
                    System.out.println("Not enough points!"); //Print if there are not enough points
                }

            //If the player picked options 1-3
            } else {
                dmgResult result = calcDmg(atk, score); //Calculate the damage and score based on the players input
                score = result.score; //Result of score after calculation
                int dmg = result.dmg; //Result of damage after calculation

                //Check if the number rolled in the calcDmg method is more than 0
                if (dmg > 0) {
                    System.out.println("You did " + dmg + " damage!"); //Display the damage done
                    bossHealth -= dmg; //Update the boss's health

                //Prints 'You missed' if dmg is 0 or less
                } else {
                    System.out.println("You missed!");
                }

                //Check if the monsters HP is 0 or lower
                if (bossHealth <= 0) {
                    System.out.println("BOSS defeated!");
                    System.out.println("YOU WON");
                    score += scorePlus; //Add points to total score
                    gameWon = true; //Set the boolean 'gameWon' to true
                    break;
                }

                //Boss's attack
                Thread.sleep(800); //Pause for readability
                System.out.println("BOSS's turn");
                int mAtk = roll.nextInt(30) + 1; //Randomly generate the value of the boss's attack and add 1 so it never misses
                System.out.println("The BOSS did " + mAtk + " damage!"); //Display the damage done
                pHealth -= mAtk; //Update the player's health
            }   
        }
        return score; //Return updated score
    }

    //Method to calculate damage based on option chosen and current score
    static dmgResult calcDmg(int atk, int score) {
        int updScore = score; //Store the updated score after applying damage
        int dmg = 0;
        switch (atk) {
            case 1:

                //Check if player has enough points for this attack
                if (score > 14) {
                    updScore -= 15; //Deduct points for this attack
                    dmg = roll.nextInt(16); //Generate a random number up to 15 for the attack

                    //Check that the damage is within a set range
                    if(dmg > 0 && dmg < 15){

                        //Makes sure that the damage dealt is between 10-15
                        dmg += 10;
                        if(dmg > 15){
                            dmg = 15;
                        }
                    }
                } else {
                    System.out.println("Not enough points!"); //Print message if there are not enough points
                }
                break;
    
            case 2:

                //Check if player has enough points for this attack
                if (score > 24) {
                    updScore -= 25; //Deduct points for this attack
                    dmg = roll.nextInt(26); //Generate a random number up to 25 for the attack

                    //Check that the damage is within a set range
                    if(dmg > 0 && dmg < 25){

                        //Makes sure that the damage dealt is between 15-25
                        dmg += 15;
                        if(dmg > 25){
                            dmg = 25;
                        }
                    }
                } else {
                    System.out.println("Not enough points!"); //Print message if there are not enough points
                }
                break;
    
            case 3:

                //Check if player has enough points for this attack
                if (score > 34) {
                    updScore -= 35; //Deduct points for this attack
                    dmg = roll.nextInt(36); //Generate a random number up to 35 for the attack

                    //Check that the damage is within a set range
                    if(dmg > 0 && dmg < 35){

                        //Makes sure that the damage dealt is between 20-35
                        dmg += 20;
                        if(dmg > 35){
                            dmg = 35;
                        }
                    }
                } else {
                    System.out.println("Not enough points!"); //Print message if there are not enough points
                }
                break;
        }
        return new dmgResult(updScore, dmg); //Return the updated score and damage
    }
}