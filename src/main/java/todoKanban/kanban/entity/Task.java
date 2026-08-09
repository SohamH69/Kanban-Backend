package todoKanban.kanban.entity;
import jakarta.persistence.*;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String color;
    private String status;
    private Integer position;
    private boolean locked = true;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Task(){}

    //----Getters & Setters----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title){ this.title = title;}

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getStatus() { return status; }
    public void setStatus(String status){ this.status = status; }

    public Integer getPosition() { return position; }
    public void setPosition(Integer position){ this.position = position; }

    public boolean getLocked() { return locked; }
    public void setLocked(boolean locked) { this.locked = locked; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
