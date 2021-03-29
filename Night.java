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
            if (findPlayer(voteData[0]) != null && findPlayer(voteData[1]) != null) {
                Player mafia = findPlayer(voteData[0]);
                Player target = findPlayer(voteData[1]);

                // mafia part
                if (mafia.isAlive) {
                    if (isNightPlayer(voteData[0])) {
                            mafia.hasVoted = true;
                    } else {
                        System.err.println("user can not wake up during night");
                    }


                    // target part
                    if (target.isAlive) {
                        target.hasBeenVoted();
                    } else {
                        System.err.println("target already dead");
                        target.hasVoted = false;
                    }
                } else {
                    System.err.println("user is dead");
                }
            }
        } else {
            System.err.println("input is incorrect. you should inter votes like this pattern: (mafia_name) (target_name)");
        }
    }
}
