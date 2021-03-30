public abstract class MafiasGroup extends Roles {
    // this static int , will be count the number of "alive" mafias in the game
    public static int NUMBER_OF_MAFIAS = 0;

    protected MafiasGroup() {
        // all of the mafias are in the mafiaGroup :)
        isMafia = true;

        // all of the mafias can wake up in the night and vote.
        // also some of them have special ability in the night!
        isNightPlayer = true;
    }
}
