public class Silencer extends MafiasGroup{
    protected static final int MAX_LIVE = 1;

    public Silencer() {
        NUMBER_OF_MAFIAS++;
    }

    // the special ability of the silencer role:
    public void silence (Player player){
        player.setSilenced(true);
    }

    @Override
    public String toString() {
        return "silencer";
    }
}
