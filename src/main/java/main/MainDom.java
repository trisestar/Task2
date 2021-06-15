package main;

public class MainDom {
    public static void main(String[] args) {
        StudentsDomBuilder domBuilder = new StudentsDomBuilder();
        domBuilder.buildSetStudents("src\\main\\resources\\students.xml");
        System.out.println(domBuilder.getStudents());
    }
}
