import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class UI {
    private Scanner scanner;
    private ArrayList<Integer> askedQuestionsLog = new ArrayList<>();
    private Quiz quiz;
    private int score = 0;
    private Player player = new Player();
    private ScoreBoard scoreBoard = new ScoreBoard();


    public UI(Scanner scanner, Quiz quiz) {
        this.scanner = scanner;
        this.quiz = quiz;
    }

    public void start(Scanner scanner) {
        boolean condition = true;
        String menuChoice = "";

        while (condition) {
            System.out.print("          MENU\n" +
                    "Enter - 1 - to start quiz\n" +
                    "Enter - 2 - to view last game score\n" +
                    "Enter - 3 to view all scores\n" +
                    "Enter - end - to end\n" +
                    "---> ");
            menuChoice = scanner.nextLine();
            switch (menuChoice.toLowerCase()) {
                case "end":
                    condition = false;
                    System.out.println("Thank you for playing! :)");
                    break;
                case "1":
                    player.addTag(uiTagInput());
                    askedQuestionsLog.clear();
                    this.score = 0;
                    while (askedQuestionsLog.size()<10){
                        startQuiz(scanner);
                    }
                    scoreBoard.writeToFile(player.getTag(),this.score);
                    endMessage();
                    break;
                case "2":
                    if(askedQuestionsLog.size() == 10){
                        System.out.println("Last player score was --- " + this.score + "\nPlayer tag: " + player.getTag());
                        break;
                    }else{
                        System.out.println("No previous games recorded");
                        break;
                    }
                case "3":
                    scoreBoard.getScores();
                    break;
                default:
                    System.out.println("Unaccepted input");
            }
        }
    }

    public void startQuiz(Scanner scanner) {
        System.out.printf("%22s" + (askedQuestionsLog.size()+1) + "\n", "Question ");
        int randomQuestionNumber;

        quiz.addQuestions();
        quiz.addAnswerChoices();
        quiz.addCorrectAnswers();

        int userAnswer = 0;
        boolean checkIsNumber = false;

        do {
            randomQuestionNumber = ThreadLocalRandom.current().nextInt(1, 18);
        } while (this.askedQuestionsLog.contains(randomQuestionNumber));

        this.askedQuestionsLog.add(randomQuestionNumber);

        System.out.print(quiz.getQuestion(randomQuestionNumber - 1) + "\n" +
                "Enter number (1-4) designated to your answer ---> ");
        while (!checkIsNumber) {
            try {
                userAnswer = Integer.valueOf(scanner.nextLine());
                if (userAnswer < 1 || userAnswer > 4) System.out.print("Enter numbers in range 1 to 4 inclusive!\n" +
                        "Enter number (1-4) designated to your answer ---> ");
                else checkIsNumber = true;
            } catch (Exception e) {
                System.out.print("\nPlease enter numbers in range 1-4 inclusive!\n" +
                        "Enter number (1-4) designated to your answer ---> ");
            }
        }


        if (quiz.checkAnswer(randomQuestionNumber, userAnswer)) {
            System.out.printf("%25s" + "\n", "Correct!");
            this.score++;
            System.out.println("Your score: " + this.score + "/10");
        } else {
            System.out.printf("%25s" + "\n", "Wrong answer!");
            System.out.println("Your score: " + this.score + "/10");
        }
    }

    public void endMessage(){
        System.out.println("Congrats! You have made it to the end!\n" +
                "Lets see your result...   " +
                this.score + " --- " + endScoreMessageCalculator(this.score) +
                "\nReturning to menu...");
    }

    public String endScoreMessageCalculator(int score) {
        if (score <= 3) return ("What?! Was Krillin busy?");
        else if (score > 3 && score <= 6) return ("You have some skills");
        else if (score > 6 && score <= 8) return ("Your kung fu is storng");
        else if (score == 9) return "Hello, Master";
        else return "Holy SHIT!!!!";
    }

    public String uiTagInput(){
        System.out.print("Enter player tag: ");
        String playerTag = scanner.nextLine();

        while (playerTag.length()!=3){
            System.out.print("Tag in unacceptable format!\n" +
                    "Enter your tag: --> ");
            playerTag = scanner.nextLine();

            if (playerTag.length() == 3) break;
        }

        return playerTag;
    }
}
