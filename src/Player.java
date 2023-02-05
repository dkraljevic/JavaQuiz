import java.util.Scanner;

public class Player {
    private String playerTag;
    public Scanner scanner;

    public Player(){

    }

    public Player(Scanner scanner) {
        this.scanner = scanner;
    }

    public void addTag(String playerTag){
        this.playerTag = playerTag;
    }

    public String getTag(){
        return this.playerTag;
    }
}
