package entity;

import java.util.Comparator;

public class Bus implements Comparable<Bus> {
    private String number;
    private String model;
    private int mileage;

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
    public static Comparator<Bus> mileageComparator = new Comparator<Bus>() {
        @Override
        public int compare(Bus b1, Bus b2) {
            return Integer.compare(b1.mileage, b2.mileage);
        }
    };

    public static Comparator<Bus> modelComparator = new Comparator<Bus>() {
        @Override
        public int compare(Bus b1, Bus b2) {
            return b1.model.compareTo(b2.model);
        }
    };
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
