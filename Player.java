public class Player {

    // -------------------   class properties   ------------------- //

    private final String name;
    public Roles role;
    private boolean isAssigned = false;
    public boolean isAlive = true;
    private boolean isChosenByDoctor = false;
    private boolean isSilenced = false;
    public boolean hasVoted = false;
    private int numberOfVotes = 0;

    public Player(String name) {
        this.name = name;
    }

    // -------------------   class methods   ------------------- //

    /**
     * this method will assign a role to the player.
     *
     * @param roleName this is name of the role we want to assign
     * @return if the role assigns correctly , returns ture , else returns false.
     */
    public boolean setRole(String roleName) {
        switch (roleName) {
            case "bulletproof" -> role = new Bulletproof();
            case "detective" -> role = new Detective();
            case "doctor" -> role = new Doctor();
            case "godfather" -> role = new Godfather();
            case "Joker" -> {
                if (!Joker.assignedJokerRole) {
                    role = new Joker();
                } else {
                    System.err.println("Joker role has been assigned before");
                    return false;
                }
            }
            case "mafia" -> role = new Mafia();
            case "silencer" -> role = new Silencer();
            case "villager" -> role = new Villager();
            default -> {
                System.out.println("role not found");
                return false;
            }
        }
        return true;
    }

    // in the day
    public void isHanged() {
        isAlive = false;

        if (role instanceof MafiasGroup) {
            MafiasGroup.NUMBER_OF_MAFIAS--;
        } else if (role instanceof VillagersGroup) {
            VillagersGroup.NUMBER_OF_VILLAGERS--;
        } else if (role instanceof Joker) {
            Joker.hangedInDay = true;
        }

        System.out.println(this.name + " is dead");
    }

    // in the night
    public void isKilled() {
        if (this.role instanceof Bulletproof) {
            if (!((Bulletproof) this.role).isOneTimeHurt) {
                ((Bulletproof) this.role).isOneTimeHurt = true;
                System.out.println(this.name + " was bulletproof and lost the extra-lives");
                return;
            }
        }

        if (this.role instanceof Joker) {
            Joker.assignedJokerRole = false;
        }

        isAlive = false;
        System.out.println(this.name + " was killed");

        if (role instanceof MafiasGroup) {
            MafiasGroup.NUMBER_OF_MAFIAS--;
        } else if (role instanceof VillagersGroup) {
            VillagersGroup.NUMBER_OF_VILLAGERS--;
        }
    }

    public void hasBeenVoted() {
        numberOfVotes++;
    }

    public void takeBackTheVote() {
        numberOfVotes--;
    }

    public void resetVote() {
        this.hasVoted = false;
        numberOfVotes = 0;
    }

    // -----------   getters and setters   ----------------
    public String getName() {
        return name;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public boolean isChosenByDoctor() {
        return isChosenByDoctor;
    }

    public void setChosenByDoctor(boolean chosenByDoctor) {
        isChosenByDoctor = chosenByDoctor;
    }

    public boolean isSilenced() {
        return isSilenced;
    }

    public void setSilenced(boolean silenced) {
        isSilenced = silenced;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }
}
