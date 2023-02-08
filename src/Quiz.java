import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Quiz {
    private ArrayList<Questions> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public void addQuestions() {
        try (Scanner scanner = new Scanner(Paths.get("questions.txt"))) {
            while (scanner.hasNextLine()) {
                String question = scanner.nextLine();
                this.questions.add(new Questions(question));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addCorrectAnswers() {
        try (Scanner scanner = new Scanner(Paths.get("correctAnswers.txt"))) {
            int index = 0;
            while (scanner.hasNextLine()) {
                String correctAnswer = scanner.nextLine();
                this.questions.get(index).addCorrectAnswer(correctAnswer);
                index++;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addAnswerChoices() {
        try (Scanner scanner = new Scanner(Paths.get("answers.txt"))) {
            int indexQuestion = 0;
            int indexAnswer = 0;

            while (scanner.hasNextLine()) {
                String answer = scanner.nextLine();
                this.questions.get(indexQuestion).addAnswerChoices(indexAnswer, answer);
                indexAnswer++;

                if (indexAnswer % 4 == 0) {
                    indexQuestion++;
                    indexAnswer = 0;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //used in ui to generate question and 4 different answers related to that question
    public String getQuestion(int n) {
        ArrayList<Integer> answerIndex = randNumGenerator();

        String quizQuestion = "---> " + this.questions.get(n).getQuestion() + "\n" +
                "Chose answer:\n";

        for (int i = 0; i < 4; i++) {
            quizQuestion += (i + 1) + " --- " + this.questions.get(n).getAnswerChoices(answerIndex.get(i)) + "\n";
            this.questions.get(n).addRandomAnswerChoices(i, this.questions.get(n).getAnswerChoices(answerIndex.get(i)));
        }
        return quizQuestion;
    }

    //method that compares user answer with correct answer for specific question n and returns true if answer is correct
    public boolean checkAnswer(int questionNumber, int userAnswer) {
        String correctAnswer = questions.get(questionNumber - 1).getCorrectAnswer();
        System.out.println("\nChosen answer: " + this.questions.get(questionNumber - 1).getRandomAnswerChoices(userAnswer - 1));

        if (correctAnswer.equals(this.questions.get(questionNumber - 1).getRandomAnswerChoices(userAnswer - 1)))
            return true;
        return false;
    }

    //returns random number from given index in ArrayList in range [0,3] - in total 4 numbers
    public ArrayList<Integer> randNumGenerator() {
        int randNum = ThreadLocalRandom.current().nextInt(0, 4);
        ArrayList<Integer> randNumList = new ArrayList<>(4);

        randNumList.add(randNum);

        for (int i = 0; i < 3; i++) {
            while (randNumList.contains(randNum)) {
                randNum = ThreadLocalRandom.current().nextInt(0, 4);
                if (randNumList.contains(randNum)) continue;
                else break;
            }
            randNumList.add(randNum);
        }
        return randNumList;
    }
}