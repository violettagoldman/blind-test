package game;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.Normalizer;
import java.util.regex.Pattern;

import game.tracks.Playlist;
import jaco.mp3.player.MP3Player;
import org.apache.commons.lang3.StringUtils;

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
       // questions.add(new Question(Question.Type.IMAGE, "Who is the author of this album ?", "Lomepal", "../game/assets/images/1.jpg"));
       // questions.add(new Question(Question.Type.IMAGE, "Who is the author of this album ?", "Damso", "../game/assets/images/2.jpg"));
        questions.add(new Question(Question.Type.IMAGE, "Who is the author of this album ?", "Alpha Wann", "../game/assets/images/3.jpg"));
        questions.add(new Question(Question.Type.AUDIO, "Who is the singer ?", "Eurythmics", "1TfqLAPs4K3s2rJMoCokcS"));
        questions.add(new Question(Question.Type.AUDIO, "Who is the singer ?", "Indochine", "2UC5XnHA1Wn9FjQmbjNca9"));

    }

    public static boolean checkAnswer(String reply, String solution) {
            // In lowercase
        reply = reply.toLowerCase();
        solution = solution.toLowerCase();

            // Removal of accents
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        String strTempReply = Normalizer.normalize(reply, Normalizer.Form.NFD);
        reply = pattern.matcher(strTempReply).replaceAll("");

        String strTempSolution = Normalizer.normalize(solution, Normalizer.Form.NFD);
        solution = pattern.matcher(strTempSolution).replaceAll("");
        
            // Removal of special characters
        String regex = "[^a-z0-9œæ\\s]";

        reply = reply.replaceAll(regex, "");
        solution = solution.replaceAll(regex, "");
        
            // Get percentage and compare
        double distance = StringUtils.getJaroWinklerDistance(reply, solution);

        if (distance < 0.95) {
            return false;
        }

        return true;
    }
}
