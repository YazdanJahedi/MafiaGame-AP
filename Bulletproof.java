public class Bulletproof extends VillagersGroup {
    /**
     * this is the special ability of this role .
     * <p>
     * in the night , if mafia try to kill him , he wont die for the first time!
     * actually bulletproof has an extra-live in the night.
     * but this extra-live can't help him in the day time!
     */
    public boolean isOneTimeHurt = false;


    public Bulletproof() {
         /*
          this is the only constructor of the bulletproof class.
          once a Bulletproof object creates , NUMBER_OF_VILLAGERS will increase.
         */
        NUMBER_OF_VILLAGERS++;
    }

    @Override
    public String toString() {
        return "bulletproof";
    }
}
