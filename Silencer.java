public class Silencer extends MafiasGroup{

    public Silencer() {
        NUMBER_OF_MAFIAS++;
    }

    // the special ability of the silencer role:
    public void silence (Player player){
        player.isSilenced = true;
    }

    @Override
    public String toString() {
        return "silencer";
    }
}
