# JavaQuiz

First project I created to test my knowledge gained so far,
practice and improve my coding skills. 


This quiz is a console app.
Upon starting it offers a menu.

- Menu options are:
    - 1 - To start the quiz
    - 2 - To view last game score
    - 3 - To view score list (up to 20 entries)
    - END -- exits the app
    
    
    
    
- Option 1
    - Quiz starts. Score is set to 0 and player is asked to input playerTag consisting of 3 characters. Only 3 characters will be accepted, no more, no less.
    This tag is later used for top 20 list if player achieves score that is high enough.

    - The quiz starts. It lists 10 questions one by one with 4 answers each to choose from. 
    For each correct answer it awards one point to player.

    - After 10 questions the player gets a message thats different depending on achieved score and that score is stored in file score.txt 
if it is higher than lowest score in that file or that file has less than 20 scores recorded. 

- Option 2
    - Is used to check last played game. It prints last game score and player tag.

- Option 3
    - Formats and prints contents of file scores.txt 
    - Output is formatted to be more readable
    - Scores are sorted in descending order.
