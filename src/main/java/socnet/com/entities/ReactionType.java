package socnet.com.entities;

public enum ReactionType {
    LIKE("fa-thumbs-up"),
    DISLIKE("fa-thumbs-down");

    private final String iconClass;

    ReactionType(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getIconClass() {
        return iconClass;
    }

    public String getDisplayName() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
