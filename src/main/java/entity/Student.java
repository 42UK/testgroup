package entity;

import java.util.Comparator;

public class Student implements Comparable<Student> {
    private String groupNumber;
    private double averageScore;
    private String recordBookNumber;

    private Student(Builder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageScore = builder.averageScore;
        this.recordBookNumber = builder.recordBookNumber;
    }

    public static class Builder {
        private String groupNumber;
        private double averageScore;
        private String recordBookNumber;

        public Builder setGroupNumber(String groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder setAverageScore(double averageScore) {
            this.averageScore = averageScore;
            return this;
        }

        public Builder setRecordBookNumber(String recordBookNumber) {
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public static Student fromString(String line) {
        String[] parts = line.split(",");
        return new Student.Builder()
                .setGroupNumber(parts[0])
                .setAverageScore(Double.parseDouble(parts[1]))
                .build();
    }

    @Override
    public int compareTo(Student other) {
        return this.groupNumber.compareTo(other.groupNumber); // Сортировка по номеру группы
    }

    // Компаратор для сортировки по среднему баллу
    public static Comparator<Student> averageScoreComparator = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            return Double.compare(s1.averageScore, s2.averageScore);
        }
    };
    @Override
    public String toString() {
        return "Student{" +
               "groupNumber='" + groupNumber + '\'' +
               ", averageScore=" + averageScore +
               '}';
    }
}