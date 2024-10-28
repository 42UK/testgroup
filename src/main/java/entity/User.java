package entity;

import java.util.Comparator;

public class User implements Comparable<User> {
    private final String name;
    private final String password;
    private final String email;

    private User(Builder builder) {
        this.name = builder.name;
        this.password = builder.password;
        this.email = builder.email;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {
        private String name;
        private String password;
        private String email;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static User fromString(String line) {
        String[] parts = line.split(",");
        return new User.Builder()
                .setName(parts[0])
                .setEmail(parts[1])
                .setPassword(parts[2])
                .build();
    }

    @Override
    public int compareTo(User other) {
        return this.name.compareTo(other.name); // Сортировка по имени
    }

    // Компаратор для сортировки по почте
    public static Comparator<User> emailComparator = Comparator.comparing(u -> u.email);

    @Override
    public String toString() {
        return "User{" +
               "name='" + name + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
