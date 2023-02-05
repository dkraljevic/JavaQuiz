import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//This class is used for storing and manipulating score
//Scores are written into formatted file up to 20 scores max
public class ScoreBoard {
    private ArrayList<String> scoreContent = new ArrayList<>(20);

    public ScoreBoard() {
    }

    public void createScoresFile() {
        try {
            File score = new File("score.txt");
            if (score.createNewFile())
                System.out.println("File created " + score.getName());//.createNewFile vraca true ako je file uspjesno kreiran
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    //scores are written in format 0x for numbers lower than 10
    public void writeToFile(String playerTag, int newScore) {
        this.scoreContent.clear();
        createScoresFile();
        File score = new File("score.txt");
        String scoreStr = "";

        if (newScore < 10) {
            scoreStr = "0" + String.valueOf(newScore);
        } else {
            scoreStr = String.valueOf(newScore);
        }

        String newInput = scoreStr + "," + playerTag.toLowerCase();

        try (Scanner reader = new Scanner(score)) {
            while (reader.hasNextLine()) {
                this.scoreContent.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

        //if not already sorted
        Collections.sort(this.scoreContent);

        //if list from file doesn't already have 20 entries
        if (this.scoreContent.size() < 20) {
            this.scoreContent.add(newInput);
        }

        //get the worst score from existing list and compare it with new score
        //if new score is higher than the lowest entry replace it
        String[] worstScore = new String[2];
        worstScore = this.scoreContent.get(0).split(",");


        if (this.scoreContent.size() >= 20 && Integer.valueOf(worstScore[0]) < Integer.valueOf(scoreStr)) {
            this.scoreContent.remove(0);
            this.scoreContent.add(newInput);
        }

        //after new score has been added sort the list and save it to file scores.txt
        Collections.sort(scoreContent);

        try {
            FileWriter scoreWriter = new FileWriter("score.txt");
            for (String s : scoreContent) {
                scoreWriter.write(s + "\n");
            }
            scoreWriter.close();
            System.out.println("Writing to file... Complete!");
        } catch (IOException e) {
            System.out.println("An error has occurred");
            e.printStackTrace();
        }
    }

    public void getScores() {
        this.scoreContent.clear();
        File score = new File("score.txt");
        try (Scanner reader = new Scanner(score)) {
            while (reader.hasNextLine()) {
                this.scoreContent.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

        Collections.sort(this.scoreContent);

        //format scores for output
        int arrSize = scoreContent.size()*2;
        String[] formattedScores = new String[arrSize];

        for (int i = 0, j = 0; i < arrSize-1 && j < this.scoreContent.size(); i+=2, j++) {
            String[] tmp = this.scoreContent.get(j).split(",");
            formattedScores[i] = tmp[0];
            formattedScores[i+1] = tmp[1];
        }



        System.out.printf("%25s" + "\n", "High-scores:");
        System.out.printf("%3s" + "%11s" + "%8s" + "\n", "|Position|", "|Score|", "|Tag|");
        int k = 1;
        for (int i = formattedScores.length-1; i > 0; i-=2) {
            if(k<10){
                if (k == 1) {
                    System.out.printf("|%3s|" + "|%6s|" + "|%6s|"+ "\n", k + "st ------ ",formattedScores[i-1],formattedScores[i]);
                } else if (k == 2) {
                    System.out.printf("|%3s|" + "|%6s|" + "|%6s|"+ "\n", k + "nd ------ ",formattedScores[i-1],formattedScores[i]);
                } else if (k == 3) {
                    System.out.printf("|%3s|" + "|%6s|" + "|%6s|"+ "\n", k + "rd ------ ",formattedScores[i-1],formattedScores[i]);
                } else {
                    System.out.printf("|%3s|" + "|%6s|" + "|%6s|"+ "\n", k + "th ------ ",formattedScores[i-1],formattedScores[i]);
                }
            } else {
            System.out.printf("|%2s|" + "|%6s|" + "|%6s|"+ "\n", k + "th ----- ",formattedScores[i-1],formattedScores[i]);
        }
            k++;
        }
    }
}
