package hugh.practice.dynamicds.model;

public class Order {
    private Long id;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{\"Order\":{"
                + "\"id\":"
                + id
                + ",\"userId\":"
                + userId
                + "}}";

    }
}
