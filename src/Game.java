import java.util.Arrays;
import java.util.Scanner;


/** Game class: Hosts the R-P-S game with all functions
 * @version 0.2
 * @since JRE 11
 * @author Reily M
 */
public class Game {

    /**Print out the overall wins // ties for the current session to the terminal
     *
     * @param playerOne Player one name
     * @param playerTwo Player two name
     * @param score overallScore array to print
     */
    public void overallWins(String playerOne, String playerTwo, int[] score){
        System.out.println("Here are the overall wins for the current session: ");
        System.out.println(playerOne + ": " + score[0]);
        System.out.println(playerTwo + ": " + score[1]);
        System.out.println("Total amount of ties: " + score[2]);
        System.out.println();
    }

    /**Gets the game type the player(s) would like to play
     * @return Returns the game type the player(s) wants to play
     */
    public String getGameType(){
        Scanner scan = new Scanner(System.in);
        String gameType;
        System.out.println("What game type would you like to play?: " +
                "\nBest of one (1)" +
                "\nBest of three (2)" +
                "\nBest of five (3)");
        gameType = scan.nextLine();
        System.out.println();

        while (!(gameType.equals("1") || gameType.equals("2") || gameType.equals("3"))) {
            System.out.println("Please input either 1, 2, or 3 for your choice:");
            System.out.println("What game type would you like to play?: " +
                    "\nBest of one (1)" +
                    "\nBest of three (2)" +
                    "\nBest of five (3)");
            gameType = scan.nextLine();
            System.out.println();
        }

        switch(gameType){
            case "1": gameType = "setOne";
                break;
            case "2": gameType = "setThree";
                break;
            case "3": gameType = "setFive";
                break;
        }

        return gameType;
    }

    /** Check with pOneChoice and pTwoChoice, and determines a winner, while adding score.
     *  @param playerOneName Player one's name
     * @param playerTwoName Player twp's name
     * @param pOneChoice Gets player one's choice
     * @param pTwoChoice Gets player two's choice
     * @param score Takes in the current score table
     */
    public void checkWhoWins(String playerOneName, String playerTwoName, String pOneChoice,
                              String pTwoChoice, int[] score) {
        //Compare
        //Player one wins
        if (pOneChoice.equals("R") && pTwoChoice.equals("S")) { //Rock v Scissors
            System.out.println("Rock beats Scissors");
            System.out.println(playerOneName + " wins the round!");
            score[0] += 1;
        } else if (pOneChoice.equals("P") && pTwoChoice.equals("R")) { //Paper v Rock
            System.out.println("Paper beats Rock");
            System.out.println(playerOneName + " wins the round!");
            score[0] += 1;
        } else if (pOneChoice.equals("S") && pTwoChoice.equals("P")) { //Scissors v Paper
            System.out.println("Scissors beats Paper");
            System.out.println(playerOneName + " wins the round!");
            score[0] += 1;
        } else if (pOneChoice.equals("S") && pTwoChoice.equals("R")) { //Player Two: Rock v Scissors
            System.out.println("Rock beats Scissors");
            System.out.println(playerTwoName + " wins the round!");
            score[1] += 1;
        } else if (pOneChoice.equals("R") && pTwoChoice.equals("P")) { //Paper v Rock
            System.out.println("Paper beats Rock");
            System.out.println(playerTwoName + " wins the round!");
            score[1] += 1;
        } else if (pOneChoice.equals("P") && pTwoChoice.equals("S")) { //Scissors v Rock
            System.out.println("Scissors beats Paper");
            System.out.println(playerTwoName + " wins the round!");
            score[1] += 1;
        } else {
            if (pOneChoice.equals("R") && pTwoChoice.equals("R")) { //Rock v Rock
                System.out.println("Rock ties with Rock");
                System.out.println("Tie Game");
                score[2] += 1;
            } else if (pOneChoice.equals("P") && pTwoChoice.equals("P")) { //Paper v Paper
                System.out.println("Paper ties with Paper");
                System.out.println("Tie Game");
                score[2] += 1;
            } else if (pOneChoice.equals("S") && pTwoChoice.equals("S")) { //Scissors v Scissors
                System.out.println("Scissors ties with Scissors");
                System.out.println("Tie Game");
                score[2] += 1;
            }
        }
    }

    /**Clears the screen to hide player choice
     */
    public void screenClear() {
        for (int i = 0; i < 12; i++) {
            System.out.println("********");
        }
        System.out.println();
    }

    /**Checks for the player type, and allows the user to pick a choice
     *
     * @param playerName name of the current player
     * @return The userinput choice Rock (R), Paper (P), or Scissor (S)
     */
    public String currentTurn(String playerName) {

        String userChoice = "";
        boolean stringCheck = true;
        Scanner choice = new Scanner(System.in);

        System.out.println(playerName + "'s turn");
        System.out.println("Pick Rock (R), Paper (P), Scissors (S)");

        while (stringCheck) {
            userChoice = choice.nextLine();
            while (!(userChoice.equals("R") || userChoice.equals("P") || userChoice.equals("S"))) {
                System.out.println();
                System.out.println("Please input either R, P, or S for your choice:");
                userChoice = choice.nextLine();
            }
            stringCheck = false;
        }
        System.out.println();
        return userChoice;

    }

    /**
     * Main for the RPS project
     *
     * @param args Array of Strings
     */
    public static void main(String[] args) {

        //Game Variables
        Scanner scan = new Scanner(System.in);
        String playerOneName;
        String playerTwoName;
        String playerOneChoice;
        String playerTwoChoice;
        String replaySTR;
        String gameType;
        int rounds = 0;
        int[] currentScore = new int[3]; // 0 (Player 1), 1 (Player 2), 2 (Ties)
        int[] overallScore = new int[3]; // 0 (Player 1), 1 (Player 2), 2 (Ties)
        boolean game = true;
        boolean roundEnd = false;
        Game gameInst = new Game();

        System.out.print("Player One name: ");
        playerOneName = scan.nextLine();
        System.out.print("Player Two name: ");
        playerTwoName = scan.nextLine();
        System.out.println();

        gameType = gameInst.getGameType();

        //Game loop for the program
        while (game) {

            //Check the rounds
            if(gameType.equals("setThree")){
                if(rounds < 3){
                    rounds++;
                }else{
                    roundEnd = true;
                }
            }else if(gameType.equals("setFive")){
                if (rounds < 5){
                    rounds++;
                }else{
                    roundEnd = true;
                }
            }

            // Check for replay-ability
            if(roundEnd) {

                if((currentScore[2] >= currentScore[0]) && (currentScore[0] >= currentScore[1])){
                    System.out.println("Neither player win, tied game!");
                }else if(currentScore[0] > currentScore[1]){
                    System.out.println(playerOneName + " wins!!");
                }else{
                    System.out.println(playerTwoName + " wins!!");
                }

                System.out.println();
                System.out.println("Would you like to play again (Y/N)");
                replaySTR = scan.nextLine();

                while (!(replaySTR.equals("Y") || replaySTR.equals("N"))) {
                    System.out.println("Please input either Y, or N for your choice:");
                    replaySTR = scan.nextLine();
                }

                if (replaySTR.equals("Y")) {
                    System.out.println("Restarting the game...");
                    gameInst.screenClear();
                    rounds = 0;
                    roundEnd = false;
                    for(int i = 0; i < currentScore.length; i++){
                        overallScore[i] += currentScore[i];
                    }
                    Arrays.fill(currentScore, 0);
                    gameInst.overallWins(playerOneName, playerTwoName, overallScore);
                    gameType = gameInst.getGameType();

                } else {
                    System.out.println("Ending game...");
                    game = false;
                }
            }else{

                // Player Choices
                System.out.println("Round: " + rounds);
                playerOneChoice = gameInst.currentTurn(playerOneName);
                gameInst.screenClear();
                playerTwoChoice = gameInst.currentTurn(playerTwoName);
                gameInst.screenClear();

                // Check
                gameInst.checkWhoWins(playerOneName, playerTwoName, playerOneChoice,
                        playerTwoChoice, currentScore);

                //Display current scores
                System.out.println();
                if(!gameType.equals("setOne")){
                    System.out.println("Current round: " + rounds);
                }else roundEnd = true;

                //Last letter check
                if(playerOneName.substring(playerOneName.length() - 1).equals("s")){

                    System.out.println(playerOneName + "' score: " + currentScore[0]);
                }else{

                    System.out.println(playerOneName + "'s score: " + currentScore[0]);
                }
                if(playerTwoName.substring(playerTwoName.length() - 1).equals("s")){

                    System.out.println(playerTwoName + "' score: " + currentScore[1]);
                }else{

                    System.out.println(playerTwoName + "'s score: " + currentScore[1]);
                }

                System.out.println("Total amount of ties: " + currentScore[2]);
                System.out.println();
            }
        }
    }
}
