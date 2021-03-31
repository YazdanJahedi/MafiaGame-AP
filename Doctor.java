public class Doctor extends VillagersGroup {
    /*
        this field is used in the night part of the game.
        this role can not cure more than one player in the night.
        is this property was true, he can't cure again!
     */
    public boolean hasCured = false;

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
                    player.setChosenByDoctor(true);
                    hasCured = true;
                    System.out.println("Doctor chose \"" + player.getName() + "\" to cure!");
                } else {
                    System.err.println("user already is dead");
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
