import java.util.Scanner;

public class Main {
    // static properties used in the main part
    static final Player[] players = new Player[100];
    static int numberOfPlayers = 0;  // max number of the players : 100

    static final String[] rolesName = {"mafia", "doctor", "villager", "Joker", "godfather", "bulletproof", "detective", "silencer"};
    static final int NUMBER_OF_ROLES = 8;


    public static Player findPlayer(String name) {
        for (int i = 0; i < numberOfPlayers; i++) {
            if (players[i].getName().equals(name)) {
                return players[i];
            }
        }
        System.out.println("user not found");
        return null;
    }

    public static boolean findRole(String name) {
        // 8 is number of roles
        for (int i = 0; i < NUMBER_OF_ROLES; i++) {
            if (name.equals(rolesName[i])) {
                return true;
            }
        }
        return false;
    }

    // this method will print all the players that are in the game (also they're alive)
    // also this method will print number of mafias and number of villagers in the game
    public static void printGameState() throws InterruptedException {
        System.err.println("_____________________________");
        System.out.println("number of mafias: " + MafiasGroup.NUMBER_OF_MAFIAS);
        System.out.println("number of villagers: " + VillagersGroup.NUMBER_OF_VILLAGERS);
        System.out.println("is there any Joker: " + Joker.assignedJokerRole);
        System.out.println();
        Thread.sleep(500);
        System.err.println("** Alive players in the game:");
        for (int i = 0; i < numberOfPlayers; i++) {
            if (players[i].isAlive) {
                System.out.println(players[i].getName() + ": " + players[i].role);
            }
        }
        System.err.println("------------------------------");
    }

    //todo: shayad behtar bashe ke in method , abstract beshe !
    public static void saveChangesAndReset() {
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i].isSilenced = false;
            players[i].isTicked = false;
            players[i].resetVote();

//            if (!players[i].isAlive) {
//                // todo : fekr konam shart khoob nsit
//                players[i].isKilled();
//            }

        }
    }

    public static int findMaxVote() {
        int max = 0;
        for (int i = 0; i < numberOfPlayers; i++) {
            if (players[i].numberOfVotes > max) {
                max = players[i].numberOfVotes;
            }
        }

        return max;
    }

    public static int findNumberOfMaxPlayers() {
        int numberOfMaxPlayer = 0;
        for (int i = 0; i < numberOfPlayers; i++) {
            if (players[i].numberOfVotes == findMaxVote()) {
                numberOfMaxPlayer++;
            }
        }
        return numberOfMaxPlayer;
    }

    public static Player[] findMaxVotedPlayers() {
        Player[] maxPlayers = new Player[findNumberOfMaxPlayers()];

        System.err.println("max voted player(s) :");

        for (int i = 0, j = 0; i < numberOfPlayers; i++) {
            if (players[i].numberOfVotes == findMaxVote()) {
                maxPlayers[j] = players[i];
                j++;
                System.err.print(players[i].name + "  ");
            }
        }

        System.out.println();
        return maxPlayers;
    }


    //                *#*#*   MAIN PART   *#*#*
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // this part will print an explanation for the user how to use the code
        System.out.println("WELCOME TO \"MAFIA GAME\"");
        System.out.println("First please create a new game with the \"create_game\" command , after that you should inter players names...");
        System.out.println("use this example : create_game player_1 player_2 player_3 ...");

        // this String will get the fist command of the game (starting command + players names)
        String[] beginningDate = scanner.nextLine().split(" ");

        // the fist command should be "create_game" : if was not : repeat!
        // also this part will fix the number of players.  if the number of players was less than min , will print an alarm
        while (!beginningDate[0].equals("create_game") || beginningDate.length < 4) {
            if(!beginningDate[0].equals("create_game")){
                System.out.println("no game created");
                System.err.println("first please create a new game with the \"create_game\" command");
                System.err.println("try again...");
            } else {
                System.err.println("Number of players is less than required");
            }
                beginningDate = scanner.nextLine().split(" ");
        }



        // print alarm massage !
        System.out.println("game created successfully! please assign a role to each player");

        // set number of players and print it !
        numberOfPlayers = beginningDate.length - 1;
        System.out.println("number of players : " + numberOfPlayers);

        // makes each player
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(beginningDate[i + 1]);
        }

        // this part will assign all the players a specific role .
        // there is an int named "assignments" that counts numbers of assignments .
        // this loop stops when the assignments will be equals with the numberOfPlayers.
        int assignments = 0;
        while (assignments != numberOfPlayers) {
            String[] assignDate = scanner.nextLine().split(" ");
            // if "assign_role" command wasn't found , prints an error
            if (assignDate[0].equals("assign_role")) {
                // if player wasn't found , the method will print  : "user not found"
                if (findPlayer(assignDate[1]) != null) {
                    // if role wasn't found , prints an error
                    if (findRole(assignDate[2])) {
                        findPlayer(assignDate[1]).setRole(assignDate[2]);
                        System.out.println("+");
                        assignments++;
                        System.out.println(assignments);

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


            while (!input.equals("end_vote")) {
                input = scanner.nextLine();
                if (input.equals("get_game_state")) {
                    printGameState();
                } else if (input.startsWith("start_game")) {
                    System.out.println("game has already started");
                }
                // vetting vote part of the day :
                else if (!input.startsWith("end_vote")){
                    String[] voteDate = input.split(" ");
                    Day.gettingVoteInTheDay(voteDate);
                }
            }
            Day.hangInTheDay();
            Day.saveChangesAndReset();


            // middle condition!!
            if(MafiasGroup.NUMBER_OF_MAFIAS >= VillagersGroup.NUMBER_OF_VILLAGERS
                    || MafiasGroup.NUMBER_OF_MAFIAS == 0 || Joker.hangedInDay){
                break;
            }


            // ++++  Night part
            for (int i = 0; i < 3; i++) {
            System.out.println();
            }
            System.out.println("Night " + Night.NIGHT_NUMBER++);


            while (!input.equals("end_night")) {
                input = scanner.nextLine();
                if (input.equals("get_game_state")) {
                    printGameState();
                } else if (input.startsWith("start_game")) {
                    System.out.println("game has already started");
                }
                //  voting for mafia and do night_players abilities!
                else if(!input.startsWith("end_night")){
                    // todo
                }
            }

        }



        // winning alarm and ending part of the game :
        if(Joker.hangedInDay){
            System.out.println("Joker won!");
        } else if (MafiasGroup.NUMBER_OF_MAFIAS == 0){
            System.out.println("Villagers won!");
        }else {
            System.out.println("Mafia won!");
        }


        Thread.sleep(2000);
        System.err.println("the game is over. GOODLUCK :)");

    }
}
