public class Doctor extends VillagersGroup {
    boolean hasCured = false;

    public Doctor() {
        NUMBER_OF_VILLAGERS++;
        isNightPlayer = true;
    }

    /**
     * this is the specific property of doctor.
     * he can chose a name and if the player was killed in the night doctor can help him !
     *
     * @param player is the player we want to cure
     */
    public void cure(Player player) {
        if (player != null) {
            if (!hasCured) {
                if (player.isAlive) {
                    player.isChosenByDoctor = true;
                    hasCured = true;
                    System.out.println("Doctor chose \"" + player.name + "\" to cure!");
                } else {
                    System.out.println("user already is dead");
                }
            } else {
                System.err.println("doctor has chosen a player before!!");
            }
        }
    }

    @Override
    public String toString() {
        return "doctor";
    }
}
