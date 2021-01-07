package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quiz {
    private static final Quiz quiz = new Quiz();
    List<Question> questions;
    Map<String, ArrayList<Integer>> history;

    public Quiz() {
        questions = new ArrayList<>();
        history = new HashMap<String, ArrayList<Integer>>();
        populateQuiz();
    }

    public static Quiz getInstance() {
        return (quiz);
    }

    public Question get(int id) {
        return (questions.get(id));
    }

    public int randomQuestionId(String channel) {
        ArrayList<Integer> possibilities = new ArrayList<Integer>();
        for (int i = 0; i < questions.size(); ++i) {
            possibilities.add(i);
        }
        if (!history.containsKey(channel)) {
            history.put(channel, new ArrayList<Integer>());
        }
        possibilities.removeAll(history.get(channel));
        if (possibilities.size() == 0) {
            history.get(channel).clear();
            for (int i = 0; i < questions.size(); ++i) {
                possibilities.add(i);
            }   
        }
        int choice = possibilities.get((int)Math.floor(possibilities.size() * Math.random()));
        history.get(channel).add(choice);
        return (choice);
    }

    public void populateQuiz() {
        // add questions here
        questions.add(new Question(Question.Type.IMAGE, "Qui est l'author de cet album?", "Lomepal", "../game/assets/images/1.jpg"));
        questions.add(new Question(Question.Type.IMAGE, "Qui est l'author de cet album?", "Damso", "../game/assets/images/2.jpg"));
        questions.add(new Question(Question.Type.IMAGE, "Qui est l'author de cet album?", "Alpha Wann", "../game/assets/images/3.jpg"));
        
    }
}
