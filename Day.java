import java.util.Scanner;

public class Day extends Main {
    protected static int DAY_NUMBER = 1;


    public static void printPlayersHasNotVoted() {
        int numberOfPlayersHasNotVoted = 0;
        String[] names = new String[NUMBER_OF_PLAYERS];
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (!players[i].hasVoted && players[i].isAlive && !players[i].isSilenced) {
                names[numberOfPlayersHasNotVoted++] = players[i].getName();
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

                            // votee part
                            if (votee.isAlive) {
                                votee.hasBeenVoted();
                                printPlayersHasNotVoted();
                            } else {
                                System.err.println("votee already dead");
                                voter.hasVoted = false;
                            }
                        } else {
                            System.err.println("voter is silenced");
                        }
                    } else {
                        System.err.println("voter has voted before .player can't vote two times");
                    }
                } else {
                    System.err.println("voter is dead");
                }
            }
        } else {
            System.err.println("input is incorrect. you should inter votes like this pattern: (voter_name) (votee_name)");
            System.err.println("also you can end voting with \"end_vote\" command.");
        }
    }

    public static void DayTimeVote() {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("end_vote")) {
            input = scanner.nextLine();
            if (input.equals("get_game_state")) {
                printGameState();
            } else if (input.startsWith("start_game")) {
                System.out.println("game has already started");
            }
            // vetting vote part of the day :
            else if(!input.equals("end_vote")){
                String[] voteDate = input.split(" ");
                Day.gettingVoteInTheDay(voteDate);
            }
        }

    }

    public static void hangInTheDay() {
        Player[] maxPlayers = findMaxVotedPlayers();
        if (maxPlayers.length == 1) {
            maxPlayers[0].isHanged();
        } else {
            System.out.println("villagers couldn't decide who would be hanged in the day...");
            System.out.println("nobody died");
        }
    }

    public static void saveChangesAndReset() {
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players[i].isSilenced = false;
            players[i].resetVote();
        }
    }

}
