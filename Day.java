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
}
