import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
public class TaskManager {
    private List<Task> tasks;



    //constructor
    public TaskManager() throws IOException{
        this.tasks = new ArrayList<>();
        loadTasks();
    }

    private void loadTasks() throws IOException{
        String json = FileUtil.readFile();
        if(json.equals("[]")) return;

        // very simple json parsing
        json = json.substring(1, json.length() - 1); // remove [ and ]
        if(json.isEmpty()) return;

        String[] items = json.split("},\\s*\\{");
        for(String item : items){
            item = item.replace("{", "").replace("}", "");

            Map<String, String> map = new HashMap<>();

            for(String pair: item.split(",")){
                String[] keyValue = pair.split(":", 2);
                map.put(keyValue[0].replace("\"", ""), keyValue[1].replace("\"", ""));
            }

            Task task = new Task(Integer.parseInt(map.get("id")),
                                 map.get("description")
            );
            task.status = map.get("status");
            task.createdAt = LocalDateTime.parse(map.get("createdAt"));
            task.updatedAt = LocalDateTime.parse(map.get("updatedAt"));
            tasks.add(task);
        }
    }

    private void saveTasks() throws IOException{
        StringBuilder sb = new StringBuilder("[");
        for(Task task : tasks){
            sb.append("{")
            .append("\"id\":\"").append(task.id).append("\",")
            .append("\"description\":\"").append(task.description).append("\",")
            .append("\"status\":\"").append(task.status).append("\",")
            .append("\"createdAt\":\"").append(task.createdAt).append("\",")
            .append("\"updatedAt\":\"").append(task.updatedAt).append("\"")
            .append("},");


        }

        if (!tasks.isEmpty()){
            sb.append("]");
            FileUtil.writeFile(sb.toString());

        }
    }

        public void addTask(String description) throws IOException{
            int id = tasks.isEmpty() ? 1 : tasks.get(tasks.size() - 1).id + 1;
            tasks.add(new Task(id, description));
            saveTasks();
            System.out.println("Task added successfully (ID: " + id + ")");

        }

        public void updateTask(int id, String desc) throws IOException{
            for (Task t : tasks) {
            if (t.id == id) {
                t.description = desc;
                t.updatedAt = LocalDateTime.now();
                saveTasks();
                System.out.println("Task updated");
                return;
            }
        }
        System.out.println("Task not found");
        }

        public void deleteTask(int id) throws IOException{
            tasks.removeIf(t -> t.id == id);
            saveTasks();
            System.out.print("Task deleted");
        }

        public void markstatus(int id, String status) throws IOException{
            for (Task task : tasks){
                if(task.id == id){
                    task.status = status;
                    task.updatedAt = LocalDateTime.now();
                    saveTasks();
                    System.out.println("Task marked as " + status);
                    return;
                }
            }
            System.out.println("Task not found");

        }

        public void listTasks(String filter){
            for (Task task : tasks){
                if(filter == null || task.status.equals(filter)){
                    System.out.println( task.id + ".[" + task.status + "] " + task.description);

                }
            }
        }




    }



    
