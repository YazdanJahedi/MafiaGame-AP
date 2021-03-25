public class Joker extends Roles{
    public static boolean assignedJokerRole = false;
    protected static final int MAX_LIVE = 1;

    public Joker() {
        assignedJokerRole = true;
    }

    @Override
    public String toString() {
        return "Joker";
    }
}
