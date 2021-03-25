public class Doctor extends VillagersGroup {
    protected static final int MAX_LIVE = 1;


    public Doctor() {
        NUMBER_OF_VILLAGERS++;
    }

    /**
     * this is the specific property of doctor.
     * he can chose a name and if the player was killed in the night doctor can help him !
     *
     * @param player is the player we want to cure
     */
    public void cure(Player player) {
        if (player != null) {
            if (!player.isAlive()) {
                System.out.println("user is dead");
            } else if (player.isAlive() && player.isTicked()) {
                player.getRole().lives++;
            }
        }
    }

    @Override
    public String toString() {
        return "doctor";
    }
}
