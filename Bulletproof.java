public class Bulletproof extends VillagersGroup{
    protected static final int MAX_LIVE = 2;

    public Bulletproof() {
        NUMBER_OF_VILLAGERS++;
        lives = 2 ;
    }

    @Override
    public String toString() {
        return "bulletproof";
    }
}
