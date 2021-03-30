public class Godfather extends MafiasGroup {

    public Godfather() {
         /*
          this is the only constructor of the Mafia class.
          once a Mafia object creates , NUMBER_OF_MAFIAS will increase.
         */
        NUMBER_OF_MAFIAS++;

        /*
         this is the specific property of the godfather role.

         this is the point:
            godfather is always child(sub-class) of the MafiasGroup .
            but the "isMafia" property is false for him.

            # actually if the detective checks him , he can't find out he is a mafia!
         */
        isMafia = false;
    }

    @Override
    public String toString() {
        return "godfather";
    }
}
