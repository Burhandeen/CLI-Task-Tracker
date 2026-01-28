//import java.time.LocalDate;
import java.time.LocalDateTime;
public class Task {
    public int id;
    public String description;
    public String status; //todo, in-progress, completed
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;


    //constructor initializing all fields
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = "todo";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
}
