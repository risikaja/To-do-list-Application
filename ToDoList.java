import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
public class ToDoList {

    Scanner scan = new Scanner(System.in);
    private LinkedList<Task> unfinishedTasks;
    private LinkedList<Task> finishedTasks;
    private Queue<Task> priorityTasks;

    public ToDoList(){
        unfinishedTasks = new LinkedList<>();
        finishedTasks = new LinkedList<>();
        priorityTasks = new PriorityQueue<>();
        loadTasks("listOfTasks.dat");
    }

    public void addTask() {
        System.out.println("Write the title: ");
        String title = scan.nextLine();
        System.out.println("Write the description: ");
        String description = scan.nextLine();
        System.out.println("Enter the priority (1-3): ");
        int priority = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter the due date (YYYY-MM-DD): ");
        String dueDateString = scan.nextLine();
        Date dueDate = null;
        try {
            dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Task will not have a due date.");
        }

        Task newTask = new Task(title, description, priority, dueDate);
        unfinishedTasks.add(newTask);
        priorityTasks.add(newTask);
        saveTasks("listOfTasks.dat");
        System.out.println("Task was added successfully");
        System.out.println("---------------------------");
    }


    public void markAsComplete() {
        if (unfinishedTasks.isEmpty()) {
            System.out.println("There are no unfinished tasks!");
            return;
        }

        System.out.println("Enter the title of the task to mark as complete: ");
        String title = scan.nextLine();
        boolean taskFound = false;

        for (Task task : unfinishedTasks) {
            if (task.getTitle().equals(title)) {
                task.setCompleted(true);
                finishedTasks.add(task);
                unfinishedTasks.remove(task);
                priorityTasks.remove(task);
                System.out.println("Task marked as complete!");
                System.out.println("---------------------------");
                taskFound = true;
                break;
            }
        }

        if (!taskFound) {
            System.out.println("Task not found!");
        }

        saveTasks("listOfTasks.dat");
    }


    public void printOrderTasks() {
        if (priorityTasks.isEmpty()) {
            System.out.println("There are no unfinished tasks!");
            return;
        }


        PriorityQueue<Task> tempQueue = new PriorityQueue<>(priorityTasks);

        System.out.println("Tasks in priority order:");
        while (!tempQueue.isEmpty()) {
            Task task = tempQueue.poll();
            System.out.println(task.toString());
        }
    }

    public void unfinishedTasks(){
        if(unfinishedTasks.isEmpty()){
            System.out.println("There are no unfinished tasks!");
            return;
        }
        System.out.println("Unfinished Tasks:");
        for (Task task : unfinishedTasks){
            System.out.println(task.toString());
        }
    }

    public void finishedTasks(){
        if (finishedTasks.isEmpty()){
            System.out.println("There are no finished tasks!");
            return;
        }
        System.out.println("Finished Tasks:");
        for(Task task : finishedTasks){
            System.out.println(task.toString());
        }
    }

    public void removeTask(){
        if (unfinishedTasks.isEmpty()){
            System.out.println("There are no unfinished tasks!");
            return;
        }
        System.out.println("Enter the title of the task to be removed: ");
        String title = scan.nextLine();
        for (Task task : unfinishedTasks){
            if (task.getTitle().equals(title)) {
                unfinishedTasks.remove(task);
                priorityTasks.remove(task);
                System.out.println("Task was removed successfully!");
                System.out.println("------------------------------");
                break;
            }
            else {
                System.out.println("Task was not found!");
            }
        }
        saveTasks("listOfTasks.dat");
    }

   public void searchUnfinishedTask(){
       if (unfinishedTasks.isEmpty()){
           System.out.println("There are no unfinished tasks!");
           return;
       }

       System.out.println("Enter the title: ");
       String title = scan.nextLine();

       boolean foundTask = false;
       for (Task task : unfinishedTasks){
           if (task.getTitle().equals(title)){
               System.out.println(task.toString());
               foundTask = true;
               break;
           }
       }

       if (!foundTask) {
           System.out.println("Task was not found!");
       }
   }

    public void searchFinishedTask(){
        if (finishedTasks.isEmpty()){
            System.out.println("There are no finished tasks!");
            return;
        }

        System.out.println("Enter the title: ");
        String title = scan.nextLine();

        boolean foundTask = false;
        for (Task task : finishedTasks){
            if (task.getTitle().equals(title)){
                System.out.println(task.toString());
                foundTask = true;
                break;
            }
        }

        if (!foundTask) {
            System.out.println("Task was not found!");
        }
    }

    public void editTask(){
        if (unfinishedTasks.isEmpty()){
            System.out.println("There are no unfinished tasks!");
            return;
        }

        System.out.println("Enter the title of the task to edit: ");
        String title = scan.nextLine();
        boolean taskFound = false;

        for (Task task : unfinishedTasks){
            if (task.getTitle().equals(title)){

                System.out.println("Editing Task: " + task.toString());
                System.out.println("Enter new title: ");
                String newTitle = scan.nextLine();
                task.setTitle(newTitle);

                System.out.println("Enter new description: ");
                String newDescription = scan.nextLine();
                task.setDescription(newDescription);

                System.out.println("Enter new priority: ");
                int newPriority = scan.nextInt();
                scan.nextLine();
                task.setPriority(newPriority);

                System.out.println("Enter new due date (YYYY-MM-DD): ");
                String newDueDateString = scan.nextLine();
                try {
                    Date newDueDate = new SimpleDateFormat("yyyy-MM-dd").parse(newDueDateString);
                    task.setDueDate(newDueDate);
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Keeping current due date.");
                }

                taskFound = true;
                System.out.println("Task updated successfully!");
                System.out.println("--------------------------");
                break;

            }
            if (!taskFound) {
                System.out.println("Task not found!");
            }
        }
        saveTasks("listOfTasks.dat");
    }

    public void sortTasksByDueDate() {
        if (unfinishedTasks.isEmpty()) {
            System.out.println("There are no unfinished tasks!");
            return;
        }

        PriorityQueue<Task> queue = new PriorityQueue<>(unfinishedTasks);

        System.out.println("Unfinished Tasks sorted by due date:");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll().toString());
        }
    }

    public void countUnfinishedTasks(){
        if(unfinishedTasks.isEmpty()){
            System.out.println("There are no unfinished tasks!");
            return;
        }
        int unfinishedTasksCount = unfinishedTasks.size();
        System.out.println("There are: " + unfinishedTasksCount + " tasks unfinished!");
    }

    public void countFinishedTasks(){
        if(finishedTasks.isEmpty()){
            System.out.println("There are no finished tasks");
            return;
        }
        int finishedTasksCount = finishedTasks.size();
        System.out.println("There are: " + finishedTasksCount + " tasks finished!");
    }

    public void filterTasksByPriority() {
        System.out.println("Enter the priority:");
        int priority = scan.nextInt();
        int count = 0;
        for (Task task : unfinishedTasks) {
            if (task.getPriority() == priority) {
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No tasks found with priority " + priority);
            return;
        }

        System.out.println("Tasks with priority " + priority + ":");
        for (Task task : unfinishedTasks) {
            if (task.getPriority() == priority) {
                System.out.println(task.toString());
            }
        }
    }

    public void removeDuplicateTasks() {
        if (unfinishedTasks.isEmpty()) {
            System.out.println("There are no tasks to remove!");
            return;
        }

        Set<String> uniqueTitles = new HashSet<>();
        List<Task> tempList = new LinkedList<>();

        for (Task task : unfinishedTasks) {
            if (uniqueTitles.add(task.getTitle())) {
                tempList.add(task);
            }
        }
        unfinishedTasks = new LinkedList<>(tempList);

        saveTasks("listOfTasks.dat");
    }

    public void menu(){
        System.out.println("--------To-Do-List--------");
        System.out.println("-----------Menu-----------");
        System.out.println("--------------------------");
        System.out.println(" 1. Add task");
        System.out.println(" 2. Remove task");
        System.out.println(" 3. Mark task as complete");
        System.out.println(" 4. Sort tasks by priority");
        System.out.println(" 5. Sort tasks by due date");
        System.out.println(" 6. Print unfinished tasks");
        System.out.println(" 7. Print finished tasks");
        System.out.println(" 8. Search unfinished tasks");
        System.out.println(" 9. Search finished tasks");
        System.out.println("10. Edit task");
        System.out.println("11. Count unfinished tasks");
        System.out.println("12. Count finished tasks");
        System.out.println("13. Filter tasks by priority");
        System.out.println("14. Remove duplicate tasks");
        System.out.println(" 0. Exit");
    }

    public void toDoList(){
        boolean condition = true;
        while(condition){
            menu();
            System.out.println("Choose an option: ");
            int option = scan.nextInt();
            scan.nextLine();
            switch (option){
                case 0:
                    condition = false;
                    System.out.println("Good bye");
                    break;
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    markAsComplete();
                    break;
                case 4:
                    printOrderTasks();
                    break;
                case 5:
                    sortTasksByDueDate();
                    break;
                case 6:
                    unfinishedTasks();
                    break;
                case 7:
                    finishedTasks();
                    break;
                case 8:
                    searchUnfinishedTask();
                    break;
                case 9:
                    searchFinishedTask();
                    break;
                case 10:
                    editTask();
                    break;
                case 11:
                    countUnfinishedTasks();
                    break;
                case 12:
                    countFinishedTasks();
                    break;
                case 13:
                    filterTasksByPriority();
                    break;
                case 14:
                    removeDuplicateTasks();
                    break;
                default:
                    System.out.println("Choose a valid option: ");
            }
        }
    }

    public void saveTasks(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(new ArrayList<>(unfinishedTasks));
            out.writeObject(new ArrayList<>(finishedTasks));
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadTasks(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            unfinishedTasks = new LinkedList<>((List<Task>) in.readObject());
            finishedTasks = new LinkedList<>((List<Task>) in.readObject());
            priorityTasks = new PriorityQueue<>(unfinishedTasks);
            System.out.println("Tasks loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

}