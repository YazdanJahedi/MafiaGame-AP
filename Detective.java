public class Detective extends VillagersGroup {
    /*
        this field is used in the night part of the game.
        this role can not check more than one player in the night.
        is this property was true, he can't ask again!
     */
    public boolean hasAsked = false;

    public Detective() {
        NUMBER_OF_VILLAGERS++;
        isNightPlayer = true;
    }

    /**
     * this is the specific property of detective .
     * he can check a player .
     * if the player is in MafiaGroup will print YES . else prints NO!
     *
     * @param player this is the player we want to check.
     */
    public void inquiry(Player player) {
        if (player != null) {
            if(!hasAsked) {
                if (player.role.isMafia) {
                    System.out.println("Yes. " + player.getName() + " is mafia");
                } else {
                    System.out.println("NO. " + player.getName() + " isn't mafia");
                }
                hasAsked = true;
            } else {
                System.err.println("detective has already asked");
            }
        }
    }


    @Override
    public String toString() {
        return "detective";
    }
}
