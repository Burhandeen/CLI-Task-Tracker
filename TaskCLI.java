public class TaskCLI{
    public static void main(String[] args) {
        try{
            TaskManager manager = new TaskManager();

            if(args.length == 0){
                System.out.println("No command provided");
                return;
            }

            switch(args[0]){
                case "add":
                manager.addTask(args[1]);
                break;

                case "update":
                manager.updateTask(Integer.parseInt(args[1]), args[2]);
                break;

                case "delete":
                manager.deleteTask(Integer.parseInt(args[1]));
                break;

                case "mark-in-progress":
                manager.markstatus(Integer.parseInt(args[1]), "in-progress");
                break;

                 case "mark-done":
                    manager.markstatus(Integer.parseInt(args[1]), "done");
                    break;

                    case "list":
                    if(args.length == 1)
                        manager.listTasks(null);

                        else
                        manager.listTasks(args[1]);
                        break;

                        default:
                        System.out.println("Unknown command");
            }
                    

            } catch(Exception e){
                System.out.println("Error " + e.getMessage());
            }

        }
    }