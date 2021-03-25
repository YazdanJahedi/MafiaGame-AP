public class Godfather extends MafiasGroup{
    protected static final int MAX_LIVE = 1;

    public Godfather() {
        NUMBER_OF_MAFIAS++;

        // this is the specific property of the godfather role .
        isMafia = false;
    }

    @Override
    public String toString() {
        return "godfather";
    }
}
