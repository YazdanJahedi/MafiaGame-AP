public class Day extends Main {
    static int DAY_NUMBER = 1;


    public static void printPlayersHasNotVoted() {
        int numberOfPlayersHasNotVoted = 0;
        String[] names = new String[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            if (!players[i].hasVoted && players[i].isAlive && !players[i].isSilenced) {
                names[numberOfPlayersHasNotVoted++] = players[i].name;
            }
        }

        if (numberOfPlayersHasNotVoted == 0) {
            System.out.println("All players voted correctly!!");
            System.out.println("use \"end_vote\" command");
        } else {
            System.out.println("Player(s) who have not yet voted : ");
            for (int i = 0; i < numberOfPlayersHasNotVoted; i++) {
                System.out.print(names[i] + " ");
            }
            System.out.println();
        }
    }

    public static void gettingVoteInTheDay(String[] voteData) {
        if (voteData.length == 2) {
            if (findPlayer(voteData[0]) != null && findPlayer(voteData[1]) != null) {
                Player voter = findPlayer(voteData[0]);
                Player votee = findPlayer(voteData[1]);

                // voter part
                if (voter.isAlive) {
                    if (!voter.hasVoted) {
                        if (!voter.isSilenced) {
                            voter.hasVoted = true;
                        } else {
                            System.err.println("voter is silenced");
                        }
                    } else {
                        System.err.println("voter has voted before .player can't vote two times");
                    }

                    // votee part
                    if (votee.isAlive) {
                        votee.hasBeenVoted();
                        printPlayersHasNotVoted();
                    } else {
                        System.err.println("votee already dead");
                        voter.hasVoted = false;
                    }
                } else {
                    System.err.println("voter is dead");
                }
            }
        } else {
            System.err.println("input is incorrect. you should inter votes like this pattern: (voter_name) (votee_name)");
        }
    }

    public static void hangInTheDay() {
        Player[] maxPlayers = findMaxVotedPlayers();
        if (maxPlayers.length == 1) {
            maxPlayers[0].isHanged();
        } else {
            System.err.println("villagers couldn't decide who would be hanged in the day...");
            System.out.println("nobody died");
        }
    }

    public static void saveChangesAndReset() {
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i].isSilenced = false;
            players[i].resetVote();
        }
    }
}
