public class Detective extends VillagersGroup {
    boolean hasAsked = false;

    public Detective() {
        NUMBER_OF_VILLAGERS++;
        isNightPlayer = true;
    }

    /**
     * this is the specific property of detective .
     * he can check a player .
     * if the player is in MafiaGroup will print YES . else prints NO!
     * @param player this is the player we want to check.
     */
    public void inquiry(Player player) {
        if (player != null) {
            if(player.role.isMafia){
                System.out.println("Yes. "+ player.name+ " is mafia");
            } else {
                System.out.println("NO. "+ player.name+ " isn't mafia");
            }
            hasAsked = true;
        }
    }


    @Override
    public String toString() {
        return "detective";
    }
}
