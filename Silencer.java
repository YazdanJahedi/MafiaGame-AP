public class Silencer extends MafiasGroup{
    boolean hasSilenced = false;

    public Silencer() {
        NUMBER_OF_MAFIAS++;
    }

    // the special ability of the silencer role:
    public void silence (String name){
        Player target = Main.findPlayer(name);
        if( target != null){
            if(target.isAlive){
                target.isSilenced = true;
                hasSilenced = true;
                System.out.println(name +" is silenced for the next day");
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
