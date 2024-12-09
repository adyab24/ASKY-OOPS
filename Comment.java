public class Comment {
    private final User user;
    private final String text;

    public Comment(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }
}
