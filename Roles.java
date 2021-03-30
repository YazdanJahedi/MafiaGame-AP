public abstract class Roles {

    /**
     * this property will be true if the player had an mafia role (mafia-silencer-godfather)
     * if the player was a villager (villager-doctor-detective-bulletproof) will be false.
     */
    protected boolean isMafia;

    /**
     * some of the players can wake up during the night and do some special things!
     * if the player could wake up in the night , this property will be ture!
     */
    protected boolean isNightPlayer;

}
