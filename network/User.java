package network;

public class User {
    String name;
    String channel;
    String avatar;
    int score;

    public User(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
        this.score = 0;
        this.channel = "";
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    };

    public void incrementScore() {
        this.score++;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

}
