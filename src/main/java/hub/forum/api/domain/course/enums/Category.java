package hub.forum.api.domain.course.enums;

public enum Category {
    TECHNOLOGY("technology"),
    ADMINISTRATION("administration"),
    DESIGN("design"),
    BUSINESS("business"),
    MARKETING("marketing"),
    FINANCE("finance");

    private String category;

    Category(String category) {
        this.category = category;
    }
}
