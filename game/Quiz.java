package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.Normalizer;
import java.util.regex.Pattern;

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
        questions.add(new Question(Question.Type.IMAGE, "Who is the author of this album ?", "Pink Floyd", "2WT1pbYjLJciAR26yMebkH"));
        questions.add(new Question(Question.Type.IMAGE, "Who is the author of this album ?", "Daft Punk", "4m2880jivSbbyEGAKfITCa"));
        questions.add(new Question(Question.Type.IMAGE, "Who is the author of this album ?", "The Beatles", "0ETFjACtuP2ADo6LFhL6HN"));
        questions.add(new Question(Question.Type.IMAGE, "Who is the author of this album ?", "Damso", "7JJ1Zqwc0m0cDyXXodXCqb"));
        questions.add(new Question(Question.Type.IMAGE, "Who is the author of this album ?", "Daft Punk", "4m2880jivSbbyEGAKfITCa"));

        questions.add(new Question(Question.Type.AUDIO, "Who is the singer ?", "Eurythmics", "1TfqLAPs4K3s2rJMoCokcS"));
        questions.add(new Question(Question.Type.AUDIO, "Who is the singer ?", "Britney Spears", "6I9VzXrHxO9rA9A5euc8Ak"));
        questions.add(new Question(Question.Type.AUDIO, "Who is the singer ?", "Oasis", "1qPbGZqppFwLwcBC1JQ6Vr"));
        questions.add(new Question(Question.Type.AUDIO, "Who is the singer ?", "Europe", "3MrRksHupTVEQ7YbA0FsZK"));
        questions.add(new Question(Question.Type.AUDIO, "Who is the singer ?", "Scorpions", "0RdUX4WE0fO30VnlUbDVL6"));

        questions.add(new Question(Question.Type.AUDIO, "Who is the singer ?", "Francis Cabrel", "40U2kuipBVRrReD32J2lmO"));
        questions.add(new Question(Question.Type.AUDIO, "Who is the singer ?", "Jean Jacques Goldman", "55mXuPsEm6aboDIM68RM74"));
        questions.add(new Question(Question.Type.AUDIO, "Who is the singer ?", "Renaud", "7eJdx83TiKsVqAzLFBelMO"));
        questions.add(new Question(Question.Type.AUDIO, "Who is the singer ?", "Claude François", "1zcFnMc6ULvzH8npblFGvy"));
        questions.add(new Question(Question.Type.AUDIO, "Who is the singer ?", "Oldelaf", "0jJB62nm6LZ0E7KRxzENS3"));
        questions.add(new Question(Question.Type.AUDIO, "Who is the singer ?", "Christophe", "1N4ixxhbBH1ClnPdTTsRzz"));
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
