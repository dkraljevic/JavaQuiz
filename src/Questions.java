import java.util.ArrayList;


public class Questions {
    private String question;
    private String correctAnswer;
    private ArrayList<String> answerChoices = new ArrayList<>(4);
    private ArrayList<String> answerChoicesRandom = new ArrayList<>(4);


    public Questions() {
    }

    public Questions(String question) {
        this.question = question;
    }

    public void addRandomAnswerChoices(int n, String answer) {
        this.answerChoicesRandom.add(n, answer);
    }

    public void addAnswerChoices(int index, String answer) {
        this.answerChoices.add(index, answer);
    }

    public void addCorrectAnswer(String answer) {
        this.correctAnswer = answer;
    }

    public String getRandomAnswerChoices(int n) {
        return this.answerChoicesRandom.get(n);
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswerChoices(int n) {
        return this.answerChoices.get(n);
    }

    //method that returns string answer based on number from user input
    public String checkAnswer(int n) {
        return answerChoices.get(n);
    }
}