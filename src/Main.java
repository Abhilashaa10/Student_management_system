import java.io.*;
import java.util.*;

public class Main {
    static final String FILE_NAME = "students.dat";

    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        loadFromFile();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add");
            System.out.println("2. View");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5.Search");
            System.out.println("6.sort by name");
            System.out.println("7.sort by age");
            System.out.println("8. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 ->
                    addStudent(sc);
                case 2 ->
                    viewStudents();
                case 3 ->
                    updateStudent(sc);
                case 4 ->
                deleteStudent(sc);
                case 5 ->
                    searchStudent(sc);
                case 6 ->
                sortByName();
                case 7 ->
                sortByAge();
                
                case 8 -> { 
                    saveToFile();
                    System.out.println("Data saved. Bye!");
                    return;
                    }
                }
            }
        }
     static void saveToFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME)
            );

            oos.writeObject(students);
            oos.close();

            System.out.println("Data saved!");
        } catch (Exception e) {
            System.out.println("Error saving file");
        }
    }

    static void loadFromFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_NAME)
            );

            students = (ArrayList<Student>) ois.readObject();
            ois.close();

            System.out.println("Data loaded!");
        } catch (Exception e) {
            System.out.println("No previous data found.");
        }
    }



    static void addStudent(Scanner sc) {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Course: ");
        String course = sc.nextLine();

        students.add(new Student(id, name, age, course));
    }

    static void viewStudents() {
        for (Student s : students) {
            System.out.println(s);
        }
    }

    static void updateStudent(Scanner sc) {
        System.out.println("Enter ID to update");
        int id = sc.nextInt();

        for (Student s : students) {

            if (s.getId() == id) {
                sc.nextLine();

                System.out.println("New Name : ");
                s.setName(sc.nextLine());

                System.out.println("New Age :");
                s.setAge(sc.nextInt());
                sc.nextLine();

                System.out.println("New Course : ");
                s.setCourse(sc.nextLine());

                System.out.println("Updated!");
                return;
                
                
            }
        }
    }

    static void searchStudent(Scanner sc){
        System.out.println("Enter Id to search student : ");
        int id = sc.nextInt();

        for(Student s : students){
            if(s.getId()==id){
                System.out.println("ID Found : "+ s);
                return;
            }
        }
        System.out.println("ID not found");
    }

    static void sortByName(){
        Collections.sort(students,(s1,s2) ->
    s1.getName().compareToIgnoreCase(s2.getName())
);

viewStudents();
    }

    static void sortByAge(){
        Collections.sort(students , (s1,s2) ->
    Integer.compare(s1.getAge(),s2.getAge())
);

viewStudents();    }


    static void deleteStudent(Scanner sc) {
        System.out.println("Enter ID to delete : ");
        int id = sc.nextInt();

        boolean removed = students.removeIf(s -> s.getId() ==id);

        if (removed){
            System.out.println(" ID deleted");
        }

        else{
            System.out.println("ID not found");
        }
    }
}
    
