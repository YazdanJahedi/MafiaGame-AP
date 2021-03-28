public class Player {
    String name;
    Roles role;
    boolean isAlive = true;
    boolean isTicked = false;
    boolean isSilenced = false;
    boolean hasVoted = false;
    int numberOfVotes = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRole(String roleName) {
        switch (roleName) {
            case "bulletproof" -> role = new Bulletproof();
            case "detective" -> role = new Detective();
            case "doctor" -> role = new Doctor();
            case "godfather" -> role = new Godfather();
            case "Joker" -> role = new Joker();
            case "mafia" -> role = new Mafia();
            case "silencer" -> role = new Silencer();
            case "villager" -> role = new Villager();
            default -> System.out.println("role not found");
        }
    }

    // in the day
    public void isHanged() {
        isAlive = false;


        if (role instanceof MafiasGroup) {
            MafiasGroup.NUMBER_OF_MAFIAS--;
        } else if (role instanceof VillagersGroup) {
            VillagersGroup.NUMBER_OF_VILLAGERS--;
        } else if (role instanceof Joker){
            Joker.hangedInDay = true;
        }
    }

    // in the night
    public void isKilled() {
        if(this.role instanceof Bulletproof){
            if(!((Bulletproof) this.role).isOneTimeHurt) {
                ((Bulletproof) this.role).isOneTimeHurt = true;
                return;
            }
        }
        isAlive = false;

        if (role instanceof MafiasGroup) {
            MafiasGroup.NUMBER_OF_MAFIAS--;
        } else if (role instanceof VillagersGroup) {
            VillagersGroup.NUMBER_OF_VILLAGERS--;
        }
    }

    public void hasBeenVoted(){
        numberOfVotes++;
    }

    public void resetVote(){
        this.hasVoted = false;
        numberOfVotes = 0;
    }

}
