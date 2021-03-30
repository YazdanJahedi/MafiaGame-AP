public class Villager extends VillagersGroup{

    public Villager() {
        /*
          this is the only constructor of the Villager class.
          once a villager object creates , NUMBER_OF_VILLAGERS will increase.
         */
        NUMBER_OF_VILLAGERS++;
    }

    @Override
    public String toString() {
        return "villager";
    }
}
