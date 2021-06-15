package main;
public class Builder {
    private long studentId;
    private String name;

    private Builder() {
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("studentId=").append(studentId);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    static class StudentBuilder {
        private Builder student;

        private StudentBuilder(){
            student = new Builder();
        }

        StudentBuilder studentId(long studentId) {
            student.setStudentId(studentId);
            return this;
        }

        StudentBuilder name(String name) {
            student.setName(name);
            return this;
        }

        Builder build(){
            return student;
        }
    }
}