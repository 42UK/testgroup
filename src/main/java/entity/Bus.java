package entity;

import java.util.Comparator;

public class Bus implements Comparable<Bus> {
    private final String number;
    private final String model;
    private final int mileage;

    private Bus(Builder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.mileage = builder.mileage;
    }

    public String getNumber() {
        return number;
    }

    public static class Builder {
        private String number;
        private String model;
        private int mileage;

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setMileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            return new Bus(this);
        }
    }

    @Override
    public int compareTo(Bus other) {
        return this.number.compareTo(other.number); // Сортировка по номеру
    }

    // Компараторы для различных критериев сортировки
    public static Comparator<Bus> mileageComparator = Comparator.comparingInt(b -> b.mileage);

    public static Comparator<Bus> modelComparator = Comparator.comparing(b -> b.model);
    public static Comparator<Bus> numberComparator = Comparator.comparing(b -> b.number);
    public static Bus fromString(String line) {
        String[] parts = line.split(",");
        return new Bus.Builder()
                .setNumber(parts[0])
                .setModel(parts[1])
                .setMileage(Integer.parseInt(parts[2]))
                .build();
    }

    @Override
    public String toString() {
        return "Bus{" +
               "number='" + number + '\'' +
               ", model='" + model + '\'' +
               ", mileage=" + mileage +
               '}';
    }
}
