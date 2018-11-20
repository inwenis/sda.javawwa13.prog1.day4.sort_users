import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = Main.class.getClassLoader();
        File file = new File(classLoader.getResource("users.csv").getFile());
        Path path = Paths.get(file.getAbsolutePath());
        List<String> allLInes = Files.readAllLines(path);
        List<User> users = allLInes
                .stream()
                .skip(1)
                .filter(x -> !x.isEmpty())
                .map(x -> {
                    String[] split = x.split(",");
                    User user = new User();
                    //id,first_name,last_name,email,gender,ip_address,age,insurance_number
                    user.id = Integer.parseInt(split[0]);
                    user.first_name = split[1];
                    user.last_name = split[2];
                    user.email = split[3];
                    user.gender = split[4];
                    user.ip_address = split[5];
                    user.age = Integer.parseInt(split[6]);
                    user.insurance_number = split[7];
                    return user;
                })
                .collect(Collectors.toList());

        users.forEach(Main::printUser);
    }

    private static void printUser(User user) {
        String line = String.format("%-12s %-12s %2d", user.first_name, user.last_name, user.age);
        System.out.println(line);
    }
}
