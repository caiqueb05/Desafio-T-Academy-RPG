import Combat.Game;
import Header.Header;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Header header = new Header();
        header.printBackground();
        while (true) {
            game.showOptions();
        }
    }
}