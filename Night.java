import java.util.Scanner;

public class Night extends Main {
    protected static int NIGHT_NUMBER = 1;

    public static void printNightPlayers() {
        System.out.println("_____________________________");
        System.out.println("** ALIVE NIGHT PLAYERS :");
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (players[i].role.isNightPlayer && players[i].isAlive) {
                System.out.println(players[i].getName() + ": " + players[i].role);
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


                // target part
                if (target.isAlive) {
                    target.hasBeenVoted();

                    if (!mafia.hasVoted) {
                        mafia.hasVoted = true;
                        System.out.println(mafia.getName() + " (" + mafia.role + ") voted to " + target.getName() + " (" + target.role + ")");
                    } else {
                        System.out.println(mafia.getName() + " (" + mafia.role + ") changed his vote to " + target.getName());
                        ((MafiasGroup) mafia.role).getLastPlayerThatMafiaHasVotedTo().takeBackedTheVote();

                    }

                    ((MafiasGroup) mafia.role).setLastPlayerThatMafiaHasVotedTo(target);

                } else {
                    System.err.println("target already dead");
                }

            }

        } else {
            System.err.println("input is incorrect. you should inter votes like this pattern: (mafia_name) (target_name)");

        }
    }

    public static void NightTimeActions(String input) {
        String[] voteDate = input.split("\s+");

        if (findPlayer(voteDate[0]) != null) {
            Player firstPlayer = findPlayer(voteDate[0]);

            if (firstPlayer.isAlive) {
                if (Night.isNightPlayer(voteDate[0])) {

                    // if was silencer (has a special ability in the night)
                    if (firstPlayer.role instanceof Silencer && !((Silencer) firstPlayer.role).hasSilenced) {
                        ((Silencer) firstPlayer.role).silence(voteDate[1]);
                    }
                    // if was non-special ability mafia in the night (mafia & godfather & non-voted silencer!!)
                    else if (firstPlayer.role.isMafia) {

                        Night.gettingVoteInTheNight(voteDate);

                        // todo : chand bar ray bede ........
                    }

                    if (firstPlayer.role instanceof Doctor) {

                        ((Doctor) firstPlayer.role).cure(findPlayer(voteDate[1]));

                    }

                    if (firstPlayer.role instanceof Detective) {
                        if (!((Detective) firstPlayer.role).hasAsked) {
                            ((Detective) firstPlayer.role).inquiry(findPlayer(voteDate[1]));
                        } else {
                            System.err.println("detective has already asked");
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

    public static void NightTime() {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("end_night")) {
            input = scanner.nextLine();
            if (input.equals("get_game_state")) {
                printGameState();
            } else if (input.startsWith("start_game")) {
                System.out.println("game has already started");
            }
            //  voting for mafia and do night_players abilities!
            else if (!input.startsWith("end_night")) {
                NightTimeActions(input);
            }
        }

    }

    public static void killInTheNight() {
        Player[] maxPlayers = findMaxVotedPlayers();

        System.out.print("mafia tried to kill: ");
        if (findMaxVote() != 0) {
            for (Player maxPlayer : maxPlayers) {
                System.out.print(maxPlayer.getName() + " ");
            }
        } else {
            System.out.print(" NOBODY!!");
        }
        System.out.println();

        if (maxPlayers.length == 1) {
            if (maxPlayers[0].isChosenByDoctor) {
                System.out.println("Doctor saved !!");
            } else {
                maxPlayers[0].isKilled();
            }
        } else if (maxPlayers.length == 2) {
            if (!maxPlayers[0].isChosenByDoctor && !maxPlayers[1].isChosenByDoctor) {
                System.out.println("nobody is killed");
            } else if (maxPlayers[0].isChosenByDoctor) {
                maxPlayers[1].isKilled();
            } else {
                maxPlayers[0].isKilled();
            }
        } else {
            System.out.println("mafias couldn't decide who would be kiiled in the Night...");
            System.out.println("nobody is killed");
        }

    }

    public static void saveChangesAndReset() {
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            players[i].resetVote();
            players[i].isChosenByDoctor = false;

            if (players[i].isSilenced && players[i].isAlive) {
                System.out.println(players[i].getName() + "  is silenced");
            }
            if (players[i].role instanceof Doctor) {
                ((Doctor) players[i].role).hasCured = false;
            }
            if (players[i].role instanceof Detective) {
                ((Detective) players[i].role).hasAsked = false;
            }
            if (players[i].role instanceof Silencer) {
                ((Silencer) players[i].role).hasSilenced = false;
            }
        }
    }

}
