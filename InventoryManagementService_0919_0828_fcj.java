// 代码生成时间: 2025-09-19 08:28:18
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Inventory Management Service REST API
 */
@Path("/inventory")
public class InventoryManagementService {

    private final Map<Integer, String> inventory = new HashMap<>();
    private int idCounter = 0;

    /**
     * Initialize the inventory with a default item.
     */
    public InventoryManagementService() {
        addInventoryItem("Item1", 10);
    }

    /**
     * Adds a new inventory item with specified quantity.
     *
     * @param item Item name
     * @param quantity Item quantity
     * @return Response with the item ID or error message.
     */
    @POST
    @Path("/add/{item}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addInventoryItem(@PathParam("item\) String item, int quantity) {
        if (inventory.containsKey(++idCounter)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Item ID already exists.")
                    .build();
        }
        inventory.put(idCounter, item + " - Quantity: " + quantity);
        return Response.status(Response.Status.CREATED)
                .entity("Item added with ID: " + idCounter)
                .build();
    }

    /**
     * Retrieves an inventory item by ID.
     *
     * @param id Item ID
     * @return Response with the item details or error message.
     */
    @GET
    @Path("/item/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventoryItem(@PathParam("id\) int id) {
        String item = inventory.get(id);
        if (item == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Item not found.")
                    .build();
        }
        return Response.ok().entity(item).build();
    }

    /**
     * Updates an inventory item quantity by ID.
     *
     * @param id Item ID
     * @param quantity New quantity
     * @return Response with success message or error message.
     */
    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInventoryItem(@PathParam("id\) int id, int quantity) {
        String item = inventory.get(id);
        if (item == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Item not found.")
                    .build();
        }
        inventory.put(id, item.replace("Quantity: " + item.split(" - ")[1].split(":")[1], "Quantity: " + quantity));
        return Response.ok().entity("Item quantity updated.").build();
    }
}
