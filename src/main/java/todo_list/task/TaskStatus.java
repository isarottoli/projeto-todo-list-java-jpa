package todo_list.task;

public enum TaskStatus {
    TODO("TO-DO"),
    DOING("Doing"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private final String displayName;

    TaskStatus(String displayName) {
      this.displayName = displayName;
    }

    public String getDisplayName() {
      return displayName;
    }
}
