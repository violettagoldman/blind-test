package game;

public class Question {
    public enum Type {
        IMAGE,
        AUDIO,
    }

    private Type type;
    private String question;
    private String answer;
    private String media;

    public Question(Type type, String question, String answer, String media) {
        this.type = type;
        this.question = question;
        this.answer = answer;
        this.media = media;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getMedia() {
        return this.media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

}
