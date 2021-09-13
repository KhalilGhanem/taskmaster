package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Todo type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Todos")
@Index(name = "byTeam", fields = {"teamID"})
public final class Todo implements Model {
  public static final QueryField ID = field("Todo", "id");
  public static final QueryField TASK_TITLE = field("Todo", "task_title");
  public static final QueryField TASK_BODY = field("Todo", "task_body");
  public static final QueryField TASK_STATE = field("Todo", "task_state");
  public static final QueryField TEAM = field("Todo", "teamID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String task_title;
  private final @ModelField(targetType="String", isRequired = true) String task_body;
  private final @ModelField(targetType="String", isRequired = true) String task_state;
  private final @ModelField(targetType="Team") @BelongsTo(targetName = "teamID", type = Team.class) Team team;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getTaskTitle() {
      return task_title;
  }
  
  public String getTaskBody() {
      return task_body;
  }
  
  public String getTaskState() {
      return task_state;
  }
  
  public Team getTeam() {
      return team;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Todo(String id, String task_title, String task_body, String task_state, Team team) {
    this.id = id;
    this.task_title = task_title;
    this.task_body = task_body;
    this.task_state = task_state;
    this.team = team;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Todo todo = (Todo) obj;
      return ObjectsCompat.equals(getId(), todo.getId()) &&
              ObjectsCompat.equals(getTaskTitle(), todo.getTaskTitle()) &&
              ObjectsCompat.equals(getTaskBody(), todo.getTaskBody()) &&
              ObjectsCompat.equals(getTaskState(), todo.getTaskState()) &&
              ObjectsCompat.equals(getTeam(), todo.getTeam()) &&
              ObjectsCompat.equals(getCreatedAt(), todo.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), todo.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTaskTitle())
      .append(getTaskBody())
      .append(getTaskState())
      .append(getTeam())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Todo {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("task_title=" + String.valueOf(getTaskTitle()) + ", ")
      .append("task_body=" + String.valueOf(getTaskBody()) + ", ")
      .append("task_state=" + String.valueOf(getTaskState()) + ", ")
      .append("team=" + String.valueOf(getTeam()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TaskTitleStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Todo justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Todo(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      task_title,
      task_body,
      task_state,
      team);
  }
  public interface TaskTitleStep {
    TaskBodyStep taskTitle(String taskTitle);
  }
  

  public interface TaskBodyStep {
    TaskStateStep taskBody(String taskBody);
  }
  

  public interface TaskStateStep {
    BuildStep taskState(String taskState);
  }
  

  public interface BuildStep {
    Todo build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep team(Team team);
  }
  

  public static class Builder implements TaskTitleStep, TaskBodyStep, TaskStateStep, BuildStep {
    private String id;
    private String task_title;
    private String task_body;
    private String task_state;
    private Team team;
    @Override
     public Todo build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Todo(
          id,
          task_title,
          task_body,
          task_state,
          team);
    }
    
    @Override
     public TaskBodyStep taskTitle(String taskTitle) {
        Objects.requireNonNull(taskTitle);
        this.task_title = taskTitle;
        return this;
    }
    
    @Override
     public TaskStateStep taskBody(String taskBody) {
        Objects.requireNonNull(taskBody);
        this.task_body = taskBody;
        return this;
    }
    
    @Override
     public BuildStep taskState(String taskState) {
        Objects.requireNonNull(taskState);
        this.task_state = taskState;
        return this;
    }
    
    @Override
     public BuildStep team(Team team) {
        this.team = team;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String taskTitle, String taskBody, String taskState, Team team) {
      super.id(id);
      super.taskTitle(taskTitle)
        .taskBody(taskBody)
        .taskState(taskState)
        .team(team);
    }
    
    @Override
     public CopyOfBuilder taskTitle(String taskTitle) {
      return (CopyOfBuilder) super.taskTitle(taskTitle);
    }
    
    @Override
     public CopyOfBuilder taskBody(String taskBody) {
      return (CopyOfBuilder) super.taskBody(taskBody);
    }
    
    @Override
     public CopyOfBuilder taskState(String taskState) {
      return (CopyOfBuilder) super.taskState(taskState);
    }
    
    @Override
     public CopyOfBuilder team(Team team) {
      return (CopyOfBuilder) super.team(team);
    }
  }
  
}
