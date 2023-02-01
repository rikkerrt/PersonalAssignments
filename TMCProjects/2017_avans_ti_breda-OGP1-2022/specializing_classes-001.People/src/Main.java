public class Main {
    public static void main(String[] args) {
        Person person = new Person("John Doe", 35);
        person.printPerson();
        
        Student student = new Student("John Doe", 35, 1337);
        student.printStudent();
    }
}
