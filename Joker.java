public class Joker extends Roles{
    static boolean assignedJokerRole = false;
    static boolean hangedInDay = false;

    public Joker() {
        assignedJokerRole = true;
    }

    @Override
    public String toString() {
        return "Joker";
    }
}
