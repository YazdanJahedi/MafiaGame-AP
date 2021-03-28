public class Joker extends Roles{
    static boolean assignedJokerRole = false;

    public Joker() {
        assignedJokerRole = true;
    }

    @Override
    public String toString() {
        return "Joker";
    }
}
