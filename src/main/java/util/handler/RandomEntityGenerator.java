package util.handler;

import entity.Bus;
import entity.Student;
import entity.User;

import java.util.Random;

public class RandomEntityGenerator {
    private static final Random random = new Random();
    private static final String[] busModels = {"Model A", "Model B", "Model C"};
    private static final String[] userNames = {"Alice", "Bob", "Charlie"};
    private static final String[] userEmails = {"alice@example.com", "bob@example.com", "charlie@example.com"};
    private static final String[] groupNumbers = {"Group 1", "Group 2", "Group 3"};

    public static Bus createRandomBus() {
        String number = "Bus" + random.nextInt(1000);
        String model = busModels[random.nextInt(busModels.length)];
        int mileage = random.nextInt(100000);
        return new Bus.Builder()
                .setNumber(number)
                .setModel(model)
                .setMileage(mileage)
                .build();
    }

    public static User createRandomUser() {
        String name = userNames[random.nextInt(userNames.length)];
        String email = userEmails[random.nextInt(userEmails.length)];
        String password = "password" + random.nextInt(1000);
        return new User.Builder()
                .setName(name)
                .setEmail(email)
                .setPassword(password)
                .build();
    }

    public static Student createRandomStudent() {
        String groupNumber = groupNumbers[random.nextInt(groupNumbers.length)];
        double averageScore = 2.0 + (random.nextDouble() * 2.0); // Средний балл от 2.0 до 4.0
        return new Student.Builder()
                .setGroupNumber(groupNumber)
                .setAverageScore(averageScore)
                .build();
    }
}
