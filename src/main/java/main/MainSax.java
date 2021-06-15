package main;

public class MainSax {
    public static void main(String[] args) {
        StudentsSaxBuilder saxBuilder = new StudentsSaxBuilder();
        saxBuilder.buildSetStudents("src\\main\\resources\\students.xml");
        System.out.println(saxBuilder.getStudents());
    }
}
