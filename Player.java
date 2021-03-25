public class Player {
    private String name;
    private Roles role;
    private boolean alive = true;
    private boolean ticked = false;
    private boolean isSilenced = false;
    boolean voted = false;
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
    public Roles getRole() {
        return role;
    }


    public void isKilled() {
        alive = false;

        if (role instanceof MafiasGroup) {
            MafiasGroup.NUMBER_OF_MAFIAS--;
        } else if (role instanceof VillagersGroup) {
            VillagersGroup.NUMBER_OF_VILLAGERS--;
        }
    }
    public boolean isAlive() {
        return alive;
    }


    public void setTicked(boolean ticked) {
        this.ticked = ticked;
    }
    public boolean isTicked() {
        return ticked;
    }


    public void setSilenced(boolean silenced) {
        isSilenced = silenced;
    }
    public boolean isSilenced() {
        return isSilenced;
    }


    public void isVoted(){
        numberOfVotes++;
    }
    public int getNumberOfVotes() {
        return numberOfVotes;
    }
    public void resetVote(){
        numberOfVotes = 0;
    }


    public void setVoted(boolean voted) {
        this.voted = voted;
    }
    public boolean getVoted(){
        return voted;
    }
}
