package Game.States;

public class Menu extends State {

    protected int currentChoice;
    protected String[] choice;

    public Menu() {}

    public int getCurrentChoice() {
        return currentChoice;
    }

    public String[] getChoice() {
        return choice;
    }
}
