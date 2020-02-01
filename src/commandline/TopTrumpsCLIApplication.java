package commandline;

import basic.Database;
import basic.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

    /**
     * This main method is called by TopTrumps.java when the user specifies that they want to run in
     * command line mode. The contents of args[0] is whether we should write game logs to a file.
     *
     * @param args
     */
    private static int numberOfGames;//number of games
    private static int numberOfMembers;

    public static void main(String[] args) throws IOException {
		/*
		these should be included in database
		 */
		Database.connection();
		Database.createTable();
        numberOfGames = Database.getNumberOfGames();
        numberOfMembers = 5;
        int round;
        String s;


        Scanner scanner = new Scanner(System.in);


        boolean writeGameLogsToFile = false; // Should we write game logs to file?
        if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile = true; // Command line selection

        // State
        boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

        WriteTestLog log = new WriteTestLog();//write test log
        log.writeString("--------------------\n" +
                "--- Top Trumps   ---\n" +
                "--------------------\n");

        Logger mylogger = Logger.getLogger("toplog");
        FileHandler fileHandler = new FileHandler("toptrumps.log");
        if(writeGameLogsToFile){
            fileHandler.setLevel(Level.INFO);
        }else {
            fileHandler.setLevel(Level.OFF);
        }
        mylogger.addHandler(fileHandler);

        // Loop until the user wants to exit the game
        while (!userWantsToQuit) {

            System.out.print("Do you want to see past results or play a game?\n" +
                    "   1: Print Game Statistics\n" +
                    "   2: Play game\n" +
                    "   3: Quit game\n" +
                    "Enter the number for your selection: \n\n\n");

            log.writeInitial();

            int typeIn = scanner.nextInt();
            scanner.nextLine();

            log.writeStart(typeIn);

            switch (typeIn) {
                case 1:
                    //System.out.print("Game Statistics:\n");
                    System.out.println(Database.printString());

                    break;
                case 2:
                    numberOfGames++;
                    mylogger.info(String.format("The game %d begins:", numberOfGames));
                    mylogger.info("----------------------------------");
                    System.out.println("Game Start");
                    Game game = new Game(numberOfMembers);//there are 5 players in CLI Mode
                    round = 0;

                    while (game.getModel().getPlayers().size() > 1) {
                        round++;
                        s = String.format("Round %d\n", round) +
                                String.format("Round %d: Players have drawn their cards\n", round);
                        System.out.print(s);
                        log.writeString(s);


                        if (game.getModel().getWinner().isUser()) {

                        }
                        boolean lastWinnerIsUser = game.getModel().getWinner().isUser();
                        int numLast = game.getModel().getPlayers().get(0).getCards().size() - 1;
                        HashMap<String, Object> maps = game.getModel().startRound(round);
                        //if user is alive
                        if (game.isUserAlive()) {
                            if (game.isUserAlive()) {
                                s = String.format("You drew '%s':\n", game.getModel().getRoundCard().get(0).getCardName()) +
                                        String.format("   > size: %d\n", game.getModel().getRoundCard().get(0).getSize()) +
                                        String.format("   > speed: %d\n", game.getModel().getRoundCard().get(0).getSpeed()) +
                                        String.format("   > range: %d\n", game.getModel().getRoundCard().get(0).getRange()) +
                                        String.format("   > firepower: %d\n", game.getModel().getRoundCard().get(0).getFirepower()) +
                                        String.format("   > cargo: %d\n", game.getModel().getRoundCard().get(0).getCargo());
                                log.writeString(s);

                                s = String.format("There are '%d cards in your deck\n", numLast);
                                log.writeString(s);

                                if (lastWinnerIsUser) {
                                    s = String.format("It is your turn to select a category, the categories are:\n" +
                                            "   1: size\n" +
                                            "   2: speed\n" +
                                            "   3: range\n" +
                                            "   4: firepower\n" +
                                            "   5: cargo\n" +
                                            "Enter the number for your attribute: %d\n", game.getModel().getCategory());
                                    log.writeString(s);
                                }
                            } else {
                                s = "You have Lost!\n";
                                log.writeString(s);
                            }
                        }


                        //round result
                        s = String.format(game.getModel().isDraw() ? String.format("Round %d: This round was a Draw, common pile now has %d cards\n", round, game.getModel().getComPile().getCards().size()) : String.format("Round %d: Player %s won this round\n", round, game.getModel().getWinner().getName())) +
                                String.format("The winning card was '%s':\n", game.getModel().getWinningCard().getCardName()) +
                                String.format("   > size: %d" + (game.getModel().getCategory() == 1 ? " <--" : "") + "\n", game.getModel().getWinningCard().getSize()) +
                                String.format("   > speed: %d" + (game.getModel().getCategory() == 2 ? " <--" : "") + "\n", game.getModel().getWinningCard().getSpeed()) +
                                String.format("   > range: %d" + (game.getModel().getCategory() == 3 ? " <--" : "") + "\n", game.getModel().getWinningCard().getRange()) +
                                String.format("   > firepower: %d" + (game.getModel().getCategory() == 4 ? " <--" : "") + "\n", game.getModel().getWinningCard().getFirepower()) +
                                String.format("   > cargo: %d" + (game.getModel().getCategory() == 5 ? " <--" : "") + "\n\n\n", game.getModel().getWinningCard().getCargo());
                        System.out.print(s);
                        log.writeString(s);
                    }


                    System.out.print("Game End\n\n");

                    mylogger.info(String.format("The winner of this game is %s.", game.getModel().getWinner().getName()));
                    mylogger.info("----------------------------------");

                    System.out.print(game.getModel().scoreOneGame());
                    log.writeString(game.getModel().scoreOneGame());


                    //insert data into database
                    ArrayList<Integer> data = new ArrayList();
                    data.add(numberOfGames);//number of games
                    data.add(numberOfMembers);//number of members this game
                    data.add(game.getModel().getNumberOfDraws());//number of draws this game
                    data.add(game.getModel().getIndexOfPlayers(game.getModel().getWinner()));//winner
                    data.add(round);//number of round this game
                    data.add(game.getModel().getScoreOfPlayers()[0]);//the score of user this game
                    data.add(game.getModel().getScoreOfPlayers()[1]);//the score of AI player 1 this game
                    data.add(game.getModel().getScoreOfPlayers()[2]);//the score of AI player 2 this game
                    data.add(game.getModel().getScoreOfPlayers()[3]);//the score of AI player 3 this game
                    data.add(game.getModel().getScoreOfPlayers()[4]);//the score of AI player 4 this game

                    Database.insert(data);

                    break;
                case 3:
                    userWantsToQuit = true; // use this when the user wants to exit the game
                    log.closeWriter();//stop to write statistics data
                    break;
            }


        }

        Database.close();
    }


}
