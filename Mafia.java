public class Mafia extends MafiasGroup {

    public Mafia() {
        /*
          this is the only constructor of the Mafia class.
          once a Mafia object creates , NUMBER_OF_MAFIAS will increase.
         */
        NUMBER_OF_MAFIAS++;
    }

    @Override
    public String toString() {
        return "mafia";
    }
}
