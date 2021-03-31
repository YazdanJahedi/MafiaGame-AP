//  the repository's URL address for this project:
//  https://github.com/YazdanJahedi/MafiaGame-AP

import java.util.Scanner;

public class Main {

    // -------------------   static properties   ------------------- //

    protected static final Player[] players = new Player[100];
    protected static int NUMBER_OF_PLAYERS = 0;   // max number of the players : 100

    private static final String[] rolesName = {"mafia", "doctor", "villager", "Joker", "godfather", "bulletproof", "detective", "silencer"};
    private static final int NUMBER_OF_ROLES = 8;


    // ---------------------   static methods   --------------------- //

    /**
     * @param name this is the name of the player we want to find.
     * @return this method will find a player with this name .
     * if the name wan not found , method will return null and prints an alarm.
     * <p>
     * <p>
     * # this method is one the most used methods in this project.
     */
    public static Player findPlayer(String name) {
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (players[i].getName().equals(name)) {
                return players[i];
            }
        }
        System.err.println("user not found");
        return null;
    }

    /**
     * this method is used in the "assign_role" part of the project.
     *
     * @param name this is name of the role we want to find
     * @return we pass a role's name , if the role exists in the game , will return true .
     * else returns false .
     */
    public static boolean findRole(String name) {
        for (int i = 0; i < NUMBER_OF_ROLES; i++) {
            if (name.equals(rolesName[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * this method will print all the players that are in the game (and also they're alive)
     * also this method will print number of mafias and number of villagers in the game
     */
    public static void printGameState() {
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("number of mafias: " + MafiasGroup.NUMBER_OF_MAFIAS);
        System.out.println("number of villagers: " + VillagersGroup.NUMBER_OF_VILLAGERS);
        System.out.println("is there any Joker: " + Joker.assignedJokerRole);
        System.out.println();
        System.out.println("** Alive players in the game:");
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (players[i].isAlive) {
                System.out.println(players[i].getName() + ": " + players[i].role);
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
    }

    /**
     * this method will check all players and finds the maximum number of given votes to a player.
     * <p>
     * this method won't be used directly , it will be used in other methods!
     *
     * @return the max number of vote between the players
     */
    public static int findMaxVote() {
        int max = 0;
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (players[i].getNumberOfVotes() > max) {
                max = players[i].getNumberOfVotes();
            }
        }

        return max;
    }

    /**
     * in the game it's necessary to know , how many players have the max vote.
     * this method will answer to this question !
     * <p>
     * this method won't be used directly , it will be used in other methods!
     *
     * @return number of the player that have the maximum number of votes
     */
    public static int findNumberOfMaxPlayers() {
        int numberOfMaxPlayer = 0;
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (players[i].getNumberOfVotes() == findMaxVote()) {
                numberOfMaxPlayer++;
            }
        }
        return numberOfMaxPlayer;
    }

    /**
     * with this method we will find out witch players have the maximum number of votes.
     * <p>
     * this method also prints the name of maxPlayers :))
     *
     * @return array of the players that have been voted more than other players .
     * (players with "max number of vote")
     */
    public static Player[] findMaxVotedPlayers() {
        Player[] maxPlayers = new Player[findNumberOfMaxPlayers()];

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("player(s) with maximum number of votes:");

        if (findMaxVote() != 0) {
            for (int i = 0, j = 0; i < NUMBER_OF_PLAYERS; i++) {
                if (players[i].getNumberOfVotes() == findMaxVote()) {
                    maxPlayers[j] = players[i];
                    j++;
                    System.out.print(players[i].getName() + "  ");
                }
            }
        } else {
            System.out.print("NOBODY !!");
        }
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println();
        return maxPlayers;
    }

    /**
     * this method will print the players in the game that don't have any role.
     * to starting the game , all players should have a role
     */
    public static void printPlayersNotAssigned() {
        System.out.println("** player(s) that don't have role: ");

        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (!players[i].isAssigned()) {
                System.out.print(players[i].getName() + " ");
            }
        }
        System.out.println();
    }


    /**
     * this method will print Welcoming massage and also
     * will print an explanation for the user , guide him how to use the code
     */
    public static void printBeginningMassage() {
        System.out.println("WELCOME TO \"MAFIA GAME\"");
        System.out.println("This game is created to help GOD of the game to manage the game better and simpler");
        System.out.println("  *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *");
        System.out.println("First please create a new game with the \"create_game\" command , after that you should inter players names...");
        System.out.println("use this example : create_game player_1 player_2 player_3 ...");
    }

    /**
     * this is the first thing that user should do !!! create_game
     * <p>
     * in this method user should inter players names.
     * the game has a few rules for it.
     * <p>
     * at the end of this part , game knows number of players and also name of them.
     */
    public static void create_game() {
        Scanner scanner = new Scanner(System.in);
        String[] createGameDate;

        /*
         the fist command should be "create_game" : if was not : repeat!
         also this part will fix the number of players.
         if the number of players was less than 4 ,an alarm will be printed
         */
        do {
            // this String[] will get the fist command of the game (starting command + players names)
            // regex :\s+  ==> if the user use one or more than one space character in the line , it will be ignored
            createGameDate = scanner.nextLine().split("\s+");
            if (!createGameDate[0].equals("create_game")) {
                System.err.println("no game created");
                System.err.println("First please create a new game with the \"create_game\" command");
            } else if (createGameDate.length < 4) {
                System.err.println("Number of players is less than required (minimum : 3 players)");
            }
        }
        while (!createGameDate[0].equals("create_game") || createGameDate.length < 4);

        // set number of players and print it !
        NUMBER_OF_PLAYERS = createGameDate.length - 1;
        System.out.println("number of players : " + NUMBER_OF_PLAYERS);

        // makes each player in the game !
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players[i] = new Player(createGameDate[i + 1]);
        }
    }

    /**
     * all player must have a role before starting the game and
     * this method will assign all the players a specific role.
     * <p>
     * this role assignment has a few rules
     */
    public static void assign_role() {
        Scanner scanner = new Scanner(System.in);
        // print notification massage!
        System.out.println("Game created successfully! please assign a role to each player");
        System.out.println("You can use this pattern : assign_role (player_name) (role_name)");
        System.out.println("After all players had a role , you can start the game with this command : start_game");

        // int "assignments" counts numbers of assignments .
        int assignments = 0;
        String[] assignDate;

        do {
            // \s+ ==> ignore all space characters
            assignDate = scanner.nextLine().split("\s+");

            // if "assign_role" command wasn't found , prints an error
            if (assignDate[0].equals("assign_role")) {
                // if player wasn't found , the method will print  : "user not found"
                if (findPlayer(assignDate[1]) != null) {
                    Player player = findPlayer(assignDate[1]);
                    if (findRole(assignDate[2])) {
                        if (!player.isAssigned()) {
                            if (player.setRole(assignDate[2])) {
                                assignments++;
                                player.setAssigned(true);

                                if (assignments < NUMBER_OF_PLAYERS)
                                    printPlayersNotAssigned();
                            }
                        } else {
                            System.err.println("player already has a role");
                        }
                    } else {
                        System.err.println("role not found");
                    }
                } // else prints : user not found
            } else if (assignDate[0].equals("start_game")) {
                System.err.println("one or more player do not have a role , you can't start the game now!");
            } else {
                System.err.println("Assign a role to each player with the command \"assign_role\"");

            }
        }
        // this loop stops when the assignments will be equals with the NUMBER_OF_PLAYERS.
        while (assignments != NUMBER_OF_PLAYERS);

    }

    /**
     * after creating the game and assigning the roles , user should start the game.w
     * this method will do this :)
     */
    public static void start_game() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("you have created game and also assigned roles . now you should start the game...");

        // this is the String that gets the starting command
        String startCommand;

        // the fist command should be "create_game" : if was not ==> repeat!
        do {
            System.err.println("please use \"start_game\" command");
            startCommand = scanner.nextLine();
        }
        while (!startCommand.equals("start_game"));


        // print all alive players in the game:
        printGameState();

        System.out.print("Ready? ");
        Thread.sleep(1000);
        System.out.print("Set! ");
        Thread.sleep(1000);
        System.out.print("GO ...");
        System.out.println();
    }

    /**
     * this method in the end the game , will print the winner :)
     */
    public static void endGameMassage() throws InterruptedException {

        for (int i = 0; i < 3; i++) {
            System.out.println("*");
        }
        System.out.println("the game is over:");
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.println("*");
        }
        // winning massage and ending part of the game :
        if (Joker.hangedInDay) {
            System.out.println("Joker won!");
        } else if (MafiasGroup.NUMBER_OF_MAFIAS == 0) {
            System.out.println("Villagers won!");
        } else {
            System.out.println("Mafia won!");
        }
        System.out.println();
        System.out.println("GOOD LUCK :)");
    }


    //  ---------------    -*#*#$   MAIN PART   $#*#*-    -------------- //

    public static void main(String[] args) throws InterruptedException {

        printBeginningMassage();

        create_game();

        assign_role();

        start_game();

        //              *****    base body of the code    *****
        while (MafiasGroup.NUMBER_OF_MAFIAS < VillagersGroup.NUMBER_OF_VILLAGERS
                && MafiasGroup.NUMBER_OF_MAFIAS != 0 && !Joker.hangedInDay) {


            //  &&&&&&&&&&&&  Day part  &&&&&&&&&&&&
            // print day number
            for (int i = 0; i < 3; i++) {
                System.out.println();
            }
            System.out.println("Day " + Day.DAY_NUMBER++);
            System.out.println();

            if (Night.NIGHT_NUMBER > 1) {
                Night.killInTheNight();
                Night.saveChangesAndReset();
                System.out.println();
                //    ---- end game condition -----
                if (MafiasGroup.NUMBER_OF_MAFIAS >= VillagersGroup.NUMBER_OF_VILLAGERS
                        || MafiasGroup.NUMBER_OF_MAFIAS == 0 || Joker.hangedInDay) {
                    break;
                }
            }


            Day.DayTimeVote();
            Day.hangInTheDay();
            Day.saveChangesAndReset();


            //    ---- end game condition -----
            if (MafiasGroup.NUMBER_OF_MAFIAS >= VillagersGroup.NUMBER_OF_VILLAGERS
                    || MafiasGroup.NUMBER_OF_MAFIAS == 0 || Joker.hangedInDay) {
                break;
            }


            // &&&&&&&&&&&&  Night part  &&&&&&&&&&&&
            // print Night number
            for (int i = 0; i < 3; i++) {
                System.out.println();
            }
            System.out.println("Night " + Night.NIGHT_NUMBER++);

            // print NightPlayers
            Night.printNightPlayers();

            Night.NightTime();
        }

        endGameMassage();
    }

}
