package main;

public class MainStax {
    public static void main(String[] args) {
        StudentsStaxBuilder staxBuilder = new StudentsStaxBuilder();
        staxBuilder.buildSetStudents("src\\main\\resources\\students.xml");
        System.out.println(staxBuilder.getStudents());
    }
}
