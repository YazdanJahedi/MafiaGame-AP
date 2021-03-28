public class Detective extends VillagersGroup {

    public Detective() {
        NUMBER_OF_VILLAGERS++;
        isNightPlayer = true;
    }

    /**
     * this is the specific property of detective .
     * he can check a player .
     * if player wasn't found , will print an alarm and returns false
     * @param playerName this is the name of player we want to check.
     * @return if the player is in MafiaGroup will return ture . else returns false.
     */
    public boolean inquiry(String playerName) {
        if (Main.findPlayer(playerName) != null) {
            return Main.findPlayer(playerName).role.isMafia;
        }
        // if player wasn't found :
        return false;
    }


    @Override
    public String toString() {
        return "detective";
    }
}
