public class Night extends Main{
    static int NIGHT_NUMBER = 1;

    public static void printNightPlayers(){
        System.err.println("_____________________________");
        System.err.println(" ** ALIVE NIGHT PLAYERS :");
        for (int i = 0; i < numberOfPlayers; i++) {
            if(players[i].role.isNightPlayer && players[i].isAlive){
                System.out.println(players[i].name + ": " + players[i].role);
            }
        }
        System.err.println("-----------------------------");
    }

}
