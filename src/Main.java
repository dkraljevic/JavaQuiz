import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Quiz quiz = new Quiz();
        UI ui = new UI(scanner, quiz);

        ui.start(scanner);
        scanner.close();
    }
}