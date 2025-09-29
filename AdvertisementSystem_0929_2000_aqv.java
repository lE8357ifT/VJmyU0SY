// 代码生成时间: 2025-09-29 20:00:57
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AdvertisementService Resource provides RESTful services for the Advertisement System.
 */
@Path("/advertisement")
public class AdvertisementService {

    // Using ConcurrentHashMap for thread-safe operations.
    private ConcurrentHashMap<Integer, Advertisement> advertisements = new ConcurrentHashMap<>();

    // Counter for generating unique advertisement IDs.
    private int idCounter = 0;

    /**
     * Adds a new advertisement to the system.
     * @param ad The advertisement to add.
     * @return The created advertisement.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Advertisement createAdvertisement(Advertisement ad) {
        if (ad == null || ad.getContent() == null || ad.getContent().isEmpty()) {
            throw new BadRequestException("Advertisement content cannot be empty.");
        }

        ad.setId(idCounter++); // Assign a unique ID.
        advertisements.put(ad.getId(), ad); // Store the advertisement.
        return ad; // Return the created advertisement.
    }

    /**
     * Retrieves an advertisement by its ID.
     * @param id The ID of the advertisement.
     * @return The advertisement if found, otherwise returns null.
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Advertisement getAdvertisement(@PathParam("id") int id) {
        return advertisements.get(id);
    }

    /**
     * Updates an existing advertisement.
     * @param id The ID of the advertisement to update.
     * @param ad The updated advertisement.
     * @return The updated advertisement.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Advertisement updateAdvertisement(@PathParam("id") int id, Advertisement ad) {
        if (ad == null || ad.getContent() == null || ad.getContent().isEmpty()) {
            throw new BadRequestException("Advertisement content cannot be empty.");
        }

        if (!advertisements.containsKey(id)) {
            throw new NotFoundException("Advertisement not found.");
        }

        ad.setId(id); // Ensure the ID is correct.
        advertisements.put(id, ad); // Update the advertisement.
        return ad; // Return the updated advertisement.
    }

    /**
     * Deletes an advertisement by its ID.
     * @param id The ID of the advertisement to delete.
     */
    @DELETE
    @Path("/{id}")
    public void deleteAdvertisement(@PathParam("id") int id) {
        if (!advertisements.remove(id).isPresent()) {
            throw new NotFoundException("Advertisement not found.");
        }
    }
}

/**
 * Represents an advertisement entity.
 */
class Advertisement {
    private int id;
    private String content;

    public Advertisement() {
    } // Default constructor for JSON deserialization.

    public Advertisement(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Advertisement{"id": " + id + ", "content": "" + content + ""}";
    }
}

/**
 * Custom exception for bad requests.
 */
class BadRequestException extends WebApplicationException {
    public BadRequestException(String message) {
        super(message, 400);
    }
}

/**
 * Custom exception for not found resources.
 */
class NotFoundException extends WebApplicationException {
    public NotFoundException(String message) {
        super(message, 404);
    }
}