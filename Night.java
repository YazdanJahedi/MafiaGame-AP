public class Night extends Main {
    static int NIGHT_NUMBER = 1;

    public static void printNightPlayers() {
        System.err.println("_____________________________");
        System.err.println(" ** ALIVE NIGHT PLAYERS :");
        for (int i = 0; i < numberOfPlayers; i++) {
            if (players[i].role.isNightPlayer && players[i].isAlive) {
                System.out.println(players[i].name + ": " + players[i].role);
            }
        }
        System.err.println("-----------------------------");
    }

    public static boolean isNightPlayer(String name) {
        if (findPlayer(name) != null) {
            if (findPlayer(name).role.isNightPlayer) {
                return true;
            }
        }
        return false;
    }

    public static void gettingVoteInTheNight(String[] voteData) {
        if (voteData.length == 2) {
            if (findPlayer(voteData[1]) != null) {
                Player mafia = findPlayer(voteData[0]);
                Player target = findPlayer(voteData[1]);

                // mafia part
                mafia.hasVoted = true;

                // target part
                if (target.isAlive) {
                    target.hasBeenVoted();
                    System.out.println(mafia.name + " (" + mafia.role + ") voted to " + target.name + " (" + target.role + ")");
                } else {
                    System.err.println("target already dead");
                    mafia.hasVoted = false;
                }

            }
        } else {
            System.err.println("input is incorrect. you should inter votes like this pattern: (mafia_name) (target_name)");
        }
    }
}
