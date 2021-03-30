public abstract class VillagersGroup extends Roles {
    // this static int , will be count the number of "alive" villagers in the game
    public static int NUMBER_OF_VILLAGERS = 0;

    protected VillagersGroup() {
        // non of the villagers is mafia :)
        isMafia = false;

        // generally villagers can't wake up in the night
        // but some of the roles can! like doctor and detective
        isNightPlayer = false;
    }
}
