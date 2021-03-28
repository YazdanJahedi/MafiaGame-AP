public class Godfather extends MafiasGroup{

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
