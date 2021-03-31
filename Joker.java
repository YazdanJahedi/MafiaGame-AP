public class Joker extends Roles {
    /*
    we can have just one player in the game with Joker role.
    so is field will prevent the user to makes more than one Joker.
     */
    static boolean assignedJokerRole = false;

    /*
    if this field becomes ture , the game is over!!
    this is the special ability of this role .
    if Joker was hanged in the day by villagers , the game is over and Joker will win!
     */
    static boolean hangedInDay = false;

    public Joker() {
        assignedJokerRole = true;
    }

    @Override
    public String toString() {
        return "Joker";
    }
}
