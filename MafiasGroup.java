public abstract class MafiasGroup extends Roles {
    // this static int , will be count the number of "alive" mafias in the game
    public static int NUMBER_OF_MAFIAS = 0;

    // this is the last player that mafia has voted to him
    // this filed is used when the mafia wants to change his last vote .
    // in this situation we most knew that who was the player that mafia had voted before
    protected Player lastPlayerThatMafiaHasVotedTo ;

    protected MafiasGroup() {
        // all of the mafias are in the mafiaGroup :)
        isMafia = true;

        // all of the mafias can wake up in the night and vote.
        // also some of them have special ability in the night!
        isNightPlayer = true;
    }

    // --------------   setter and getters  --------------

    public Player getLastPlayerThatMafiaHasVotedTo() {
        return lastPlayerThatMafiaHasVotedTo;
    }

    public void setLastPlayerThatMafiaHasVotedTo(Player lastPlayerThatMafiaHasVotedTo) {
        this.lastPlayerThatMafiaHasVotedTo = lastPlayerThatMafiaHasVotedTo;
    }
}
