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
        questions.add(new Question(Question.Type.IMAGE, "Qui est l'author de cet album?", "Lomepal", "https://www.discobuzz.fr/9942-17567-large_default/lomepal-amina-limited-edition-trifold-triple-lp-vinyl-album-holographic-cover.jpg"));
        questions.add(new Question(Question.Type.IMAGE, "Qui est l'author de cet album?", "Lomepal", "https://www.discobuzz.fr/9942-17567-large_default/lomepal-amina-limited-edition-trifold-triple-lp-vinyl-album-holographic-cover.jpg"));
        questions.add(new Question(Question.Type.IMAGE, "Qui est l'author de cet album?", "Lomepal", "https://www.discobuzz.fr/9942-17567-large_default/lomepal-amina-limited-edition-trifold-triple-lp-vinyl-album-holographic-cover.jpg"));
        questions.add(new Question(Question.Type.IMAGE, "Qui est l'author de cet album?", "Lomepal", "https://www.discobuzz.fr/9942-17567-large_default/lomepal-amina-limited-edition-trifold-triple-lp-vinyl-album-holographic-cover.jpg"));
        questions.add(new Question(Question.Type.IMAGE, "Qui est l'author de cet album?", "Lomepal", "https://www.discobuzz.fr/9942-17567-large_default/lomepal-amina-limited-edition-trifold-triple-lp-vinyl-album-holographic-cover.jpg"));
    }
}
