package game;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private static final Quiz quiz = new Quiz();
    List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
        populateQuiz();
    }

    public static Quiz getInstance() {
        return (quiz);
    }

    public Question get(int id) {
        return (questions.get(id));
    }

    public int randomQuestionId() {
        return ((int)Math.floor(questions.size() * Math.random()));
    }

    public void populateQuiz() {
        // add questions here
        questions.add(new Question(Question.Type.IMAGE, "Qui est l'author de cet album?", "Lomepal", "../game/assets/images/1.jpg"));
        questions.add(new Question(Question.Type.IMAGE, "Qui est l'author de cet album?", "Damso", "../game/assets/images/2.jpg"));
        questions.add(new Question(Question.Type.IMAGE, "Qui est l'author de cet album?", "Alpha Wann", "../game/assets/images/3.jpg"));
        
    }
}
