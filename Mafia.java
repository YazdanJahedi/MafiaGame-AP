public class Mafia extends MafiasGroup{
    protected static final int MAX_LIVE = 1;

    public Mafia() {
        NUMBER_OF_MAFIAS++;
    }

    @Override
    public String toString() {
        return "mafia";
    }
}
