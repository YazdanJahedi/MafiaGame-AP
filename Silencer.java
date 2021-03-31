public class Silencer extends MafiasGroup {
    /*
        this role can not silence more than one player in the night.
        also can not change his choice
        is this property was true, he can't change his choice again!
     */
    boolean hasSilenced = false;

    public Silencer() {
        NUMBER_OF_MAFIAS++;
    }

    // the special ability of the silencer role:
    // silencer can chose a player in the night , this player can not attend in the next dat poll !
    public void silence(String name) {
        Player target = Main.findPlayer(name);
        if (target != null) {
            if (target.isAlive) {
                target.setSilenced(true);
                hasSilenced = true;
                System.out.println(name + " is silenced for the next day");
            } else {
                System.out.println("user is dead");
            }
        }
    }

    @Override
    public String toString() {
        return "silencer";
    }
}
