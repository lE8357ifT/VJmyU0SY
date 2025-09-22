// 代码生成时间: 2025-09-22 21:27:19
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;

// TestDataGeneratorService is a RESTful service that generates test data
@Path("/testDataGenerator")
public class TestDataGeneratorService {

    // Generates a random integer between 1 and 100
    @GET
    @Path("/generateRandomInteger")
    @Produces(MediaType.TEXT_PLAIN)
    public int generateRandomInteger() {
        Random random = new Random();
        int randomInt = random.nextInt(100) + 1; // Ensure the number is between 1 and 100
        return randomInt;
    }

    // Generates a random string of length 10
    @GET
    @Path("/generateRandomString\)
    @Produces(MediaType.TEXT_PLAIN)
    public String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append((char) ('a' + random.nextInt(26))); // Generates a random lower case letter
        }
        return sb.toString();
    }

    // Generates a random boolean value
    @GET
    @Path("/generateRandomBoolean\)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean generateRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
