public class Night extends Main {
    static int NIGHT_NUMBER = 1;

    public static void printNightPlayers() {
        System.out.println("_____________________________");
        System.out.println(" ** ALIVE NIGHT PLAYERS :");
        for (int i = 0; i < numberOfPlayers; i++) {
            if (players[i].role.isNightPlayer && players[i].isAlive) {
                System.out.println(players[i].name + ": " + players[i].role);
            }
        }
        System.out.println("-----------------------------");
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

    public static void killInTheNight(){
        Player[] maxPlayers = findMaxVotedPlayers();

        System.out.print("mafia tried to kill: ");
        for (int i = 0; i < maxPlayers.length; i++) {
            System.out.print(maxPlayers[i].name + " ");
        }
        System.out.println();

        if(maxPlayers.length ==1 ){
            if(maxPlayers[0].isChosenByDoctor){
                System.out.println("Doctor saved !!");
            } else {
                maxPlayers[0].isKilled();
            }
        }else if(maxPlayers.length ==2){
            if(!maxPlayers[0].isChosenByDoctor && !maxPlayers[1].isChosenByDoctor){
                System.out.println("nobody is killed");
            } else if(maxPlayers[0].isChosenByDoctor){
                maxPlayers[1].isKilled();
            } else {
                maxPlayers[0].isKilled();
            }
        }else {
            System.out.println("mafias couldn't decide who would be kiiled in the Night...");
            System.out.println("nobody is killed");
        }

    }

    public static void saveChangesAndReset() {
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i].resetVote();
            players[i].isChosenByDoctor = false;

            if(players[i].isSilenced && players[i].isAlive){
                System.out.println(players[i].name +"  is silenced");
            }
            if(players[i].role instanceof Doctor){
                ((Doctor) players[i].role).hasCured = false;
            }
            if(players[i].role instanceof Detective){
                ((Detective) players[i].role).hasAsked = false;
            }
            if(players[i].role instanceof Silencer){
                ((Silencer) players[i].role).hasSilenced = false;
            }
        }
    }


}
