import java.util.Scanner;

public class Main {
    // static properties used in the main part
    private static final Player[] players = new Player[100];
    private static int numberOfPlayers = 0;  // max number of the players : 100

    private static final String[] rolesName = {"mafia", "doctor", "villager", "Joker", "godfather", "bulletproof", "detective", "silencer"};
    private static final int NUMBER_OF_ROLES = 8;


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
    public static void printPlayers() {
        System.err.println("_____________________________");
        System.err.println("** Alive players in the game:");
        for (int i = 0; i < numberOfPlayers; i++) {
            if (players[i].isAlive) {
                System.out.println(players[i].getName() + ": " + players[i].role);
            }
        }
        System.out.println();
        System.err.println("number of mafias: " + MafiasGroup.NUMBER_OF_MAFIAS);
        System.err.println("number of villagers: " + VillagersGroup.NUMBER_OF_VILLAGERS);
        System.err.println("is there any Joker: " + Joker.assignedJokerRole);
        System.err.println("------------------------------");
    }

    public static void saveChangesAndReset() {
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i].isSilenced = false;
            players[i].isTicked = false;
            players[i].resetVote();
            if (!players[i].isAlive) {
                // todo : fekr konam shart khoon nsit
                players[i].isKilled();
            }

        }
    }


    //   ***   MAIN PART   ***
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // this String will get the fist command of the game (starting command + players names)
        String[] beginningDate = scanner.nextLine().split(" ");

        // the fist command should be "create_game" : if was not : repeat!
        while (!beginningDate[0].equals("create_game")) {
            System.out.println("no game created");
            System.err.println("first please create a new game with the \"create_game\" command");
            System.err.println("try again...");
            beginningDate = scanner.nextLine().split(" ");
        }

        // print alarm massage !
        System.err.println("game created successfully! please assign a role to each player");

        // set number of players and print it !
        numberOfPlayers = beginningDate.length - 1;
        System.err.println("number of players : " + numberOfPlayers);

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
        System.err.println("you have created game and also assigned roles . now you should start the game...");

        // this is the String that gets the starting command
        String startCommand = scanner.nextLine();

        // the fist command should be "create_game" : if was not : repeat!
        while (!startCommand.equals("start_game")) {
            System.err.println("please use \"start_game\" command");
            startCommand = scanner.nextLine();
        }

        // print all alive players in the game:
        printPlayers();
        Thread.sleep(2000);
        System.out.println("Ready? Set! Go.");


        while (MafiasGroup.NUMBER_OF_MAFIAS > VillagersGroup.NUMBER_OF_VILLAGERS
                                    || MafiasGroup.NUMBER_OF_MAFIAS != 0 || Joker.hangedInDay) {



        }


    }
}
