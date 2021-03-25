public class Villager extends VillagersGroup{
    protected static final int MAX_LIVE = 1;

    public Villager() {
        NUMBER_OF_VILLAGERS++;
    }

    @Override
    public String toString() {
        return "villager";
    }
}
