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
            if (players[i].numberOfVotes > max) {
                max = players[i].numberOfVotes;
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
            if (players[i].numberOfVotes == findMaxVote()) {
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

        for (int i = 0, j = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (players[i].numberOfVotes == findMaxVote()) {
                maxPlayers[j] = players[i];
                j++;
                System.out.print(players[i].getName() + "  ");
            }
            System.out.println();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

        System.out.println();
        return maxPlayers;
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
            } else {
                System.err.println("Number of players is less than required (minimum : 3 players)");
            }
        }
        while (!createGameDate[0].equals("create_game") || createGameDate.length < 4);

        // print notification massage!
        System.out.println("game created successfully! please assign a role to each player");

        // set number of players and print it !
        NUMBER_OF_PLAYERS = createGameDate.length - 1;
        System.out.println("number of players : " + NUMBER_OF_PLAYERS);

        // makes each player in the game !
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players[i] = new Player(createGameDate[i + 1]);
        }
    }

    //  ---------------    -*#*#$   MAIN PART   $#*#*-    -------------- //

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        printBeginningMassage();

        create_game();


        // this part will assign all the players a specific role .
        // there is an int named "assignments" that counts numbers of assignments .
        // this loop stops when the assignments will be equals with the NUMBER_OF_PLAYERS.
        int assignments = 0;
        while (assignments != NUMBER_OF_PLAYERS) {
            String[] assignDate = scanner.nextLine().split(" ");
            // if "assign_role" command wasn't found , prints an error
            if (assignDate[0].equals("assign_role")) {
                // if player wasn't found , the method will print  : "user not found"
                if (findPlayer(assignDate[1]) != null) {
                    // if role wasn't found , prints an error
                    if (findRole(assignDate[2])) {
                        if (findPlayer(assignDate[1]).setRole(assignDate[2])) {
                            System.out.println("+");
                            assignments++;
                            System.out.println(assignments);
                        }

                    } else {
                        System.out.println("role not found");
                    }
                }
            } else {
                System.err.println("please assign a role to each player with the command \"assign_role\"");
                System.out.println("one or more player do not have a role");
            }
        }
        System.err.println("you have created game and also assigned roles . now you should start the game... use \"start_game\" command");

        // this is the String that gets the starting command
        String startCommand = scanner.nextLine();

        // the fist command should be "create_game" : if was not : repeat!
        while (!startCommand.equals("start_game")) {
            System.err.println("please use \"start_game\" command");
            startCommand = scanner.nextLine();
        }

        // print all alive players in the game:
        printGameState();
        Thread.sleep(2000);
        System.out.println("Ready? Set! Go.");


        //


        //


        //

        //


        String input = "";
        //              *****    base body of the code    ***** :
        while (MafiasGroup.NUMBER_OF_MAFIAS < VillagersGroup.NUMBER_OF_VILLAGERS
                && MafiasGroup.NUMBER_OF_MAFIAS != 0 && !Joker.hangedInDay) {


            // ++++  Day part
            for (int i = 0; i < 3; i++) {
                System.out.println();
            }
            System.out.println("Day " + Day.DAY_NUMBER++);
            System.out.println();

            while (!input.equals("end_vote")) {
                input = scanner.nextLine();
                if (input.equals("get_game_state")) {
                    printGameState();
                } else if (input.startsWith("start_game")) {
                    System.out.println("game has already started");
                }
                // vetting vote part of the day :
                else if (!input.startsWith("end_vote")) {
                    String[] voteDate = input.split(" ");
                    Day.gettingVoteInTheDay(voteDate);
                }
            }
            Day.hangInTheDay();
            Day.saveChangesAndReset();


            //


            // middle condition!!
            if (MafiasGroup.NUMBER_OF_MAFIAS >= VillagersGroup.NUMBER_OF_VILLAGERS
                    || MafiasGroup.NUMBER_OF_MAFIAS == 0 || Joker.hangedInDay) {
                break;
            }

            //


            // ++++  Night part
            for (int i = 0; i < 3; i++) {
                System.out.println();
            }
            System.out.println("Night " + Night.NIGHT_NUMBER++);
            Thread.sleep(1000);
            Night.printNightPlayers();

            while (!input.equals("end_night")) {
                input = scanner.nextLine();
                if (input.equals("get_game_state")) {
                    printGameState();
                } else if (input.startsWith("start_game")) {
                    System.out.println("game has already started");
                }
                //  voting for mafia and do night_players abilities!
                else if (!input.startsWith("end_night")) {
                    String[] voteDate = input.split(" ");


                    if (findPlayer(voteDate[0]) != null) {
                        Player firstPlayer = findPlayer(voteDate[0]);

                        if (firstPlayer.isAlive) {
                            if (Night.isNightPlayer(voteDate[0])) {

                                // if the first name was in MafiaGroup:
                                if (firstPlayer.role instanceof MafiasGroup) {

                                    // if was silencer (has a special ability in the night)
                                    if (firstPlayer.role instanceof Silencer
                                            && !((Silencer) firstPlayer.role).hasSilenced) {

                                        ((Silencer) firstPlayer.role).silence(voteDate[1]);

                                    }

                                    // if was non-special ability mafia in the night (mafia & godfather & non-voted silencer!!)
                                    else {

                                        Night.gettingVoteInTheNight(voteDate);

                                        // todo : chand bar ray bede ........
                                    }

                                }

                                // if the first name was in VillagerGroup:
                                else if (firstPlayer.role instanceof VillagersGroup) {

                                    if (firstPlayer.role instanceof Doctor) {

                                        ((Doctor) firstPlayer.role).cure(findPlayer(voteDate[1]));

                                    } else if (firstPlayer.role instanceof Detective) {
                                        if (!((Detective) firstPlayer.role).hasAsked) {
                                            ((Detective) firstPlayer.role).inquiry(findPlayer(voteDate[1]));
                                        } else {
                                            System.err.println("detective has already asked");
                                        }
                                    }
                                }
                            } else {
                                System.err.println("user can not wake up during night");
                            }
                        } else {
                            System.out.println("user is dead");
                        }
                    }
                }
            }
            Night.killInTheNight();
            Night.saveChangesAndReset();

        }


        //


        //


        //

        //


        for (int i = 0; i < 4; i++) {
            System.out.println("*");
        }
        // winning alarm and ending part of the game :
        if (Joker.hangedInDay) {
            System.out.println("Joker won!");
        } else if (MafiasGroup.NUMBER_OF_MAFIAS == 0) {
            System.out.println("Villagers won!");
        } else {
            System.out.println("Mafia won!");
        }


        Thread.sleep(2000);
        System.err.println("the game is over. GOODLUCK :)");

    }
}
