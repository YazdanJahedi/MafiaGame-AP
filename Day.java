public class Day extends Main {
    static int DAY_NUMBER = 1;


    public static void gettingVoteInTheDay(String[] voteData) {
        // voter part
        if (findPlayer(voteData[0]) != null) {
            if (!findPlayer(voteData[0]).isSilenced) {
                // todo : felan dar in version avvalie , az vizhegi "hasVoted"  estefade nashode :)
                findPlayer(voteData[0]).hasVoted = true;
            } else {
                System.out.println("voter is silenced");
            }
        }

        // votee part
        if (findPlayer(voteData[1]) != null) {
            if (findPlayer(voteData[1]).isAlive) {
                findPlayer(voteData[1]).hasBeenVoted();
            } else {
                System.out.println("votee already dead");
            }
        }
    }

    public static void hangInTheDay(){
        Player[] maxPlayers =findMaxVotedPlayers();
        if(maxPlayers.length ==1 ){
            maxPlayers[0].isHanged();

            System.err.println(maxPlayers[0].name + " is hanged! ");
        }
        else {
            System.err.println("villagers couldn't decide who would be hanged in the day...");
        }
    }

    public static void saveChangesAndReset() {
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i].isSilenced = false;
            players[i].resetVote();
        }
    }
}
