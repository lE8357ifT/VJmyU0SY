// 代码生成时间: 2025-09-19 19:07:33
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

// 使用Jersey框架创建库存管理系统的RESTful服务
@Path("/items")
public class InventoryManagementSystem {

    // 使用HashMap模拟数据库存储
    private Map<String, Item> inventory = new HashMap<>();

    // 添加新商品
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItem(Item item) {
        try {
            if (item == null || item.getName() == null || item.getName().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Item name cannot be null or empty.").build();
            }
            inventory.put(item.getId(), item);
            return Response.status(Response.Status.CREATED).entity(item).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding item: " + e.getMessage()).build();
        }
    }

    // 获取商品信息
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {
        return Response.ok(inventory.values()).build();
    }

    // 获取单个商品信息
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") String id) {
        Item item = inventory.get(id);
        if (item == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Item not found.").build();
        }
        return Response.ok(item).build();
    }

    // 更新商品信息
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateItem(@PathParam("id\) String id, Item item) {
        try {
            if (item == null || item.getName() == null || item.getName().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Item name cannot be null or empty.").build();
            }
            if (!inventory.containsKey(id)) {
                return Response.status(Response.Status.NOT_FOUND).entity("Item not found.").build();
            }
            inventory.put(id, item);
            return Response.ok(item).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating item: " + e.getMessage()).build();
        }
    }

    // 删除商品
    @DELETE
    @Path("/{id}")
    public Response deleteItem(@PathParam("id\) String id) {
        try {
            if (!inventory.containsKey(id)) {
                return Response.status(Response.Status.NOT_FOUND).entity("Item not found.").build();
            }
            inventory.remove(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting item: " + e.getMessage()).build();
        }
    }
}

// 商品类，用于表示库存中的商品
class Item {
    private String id;
    private String name;
    private int quantity;

    public Item() {
    }

    public Item(String id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}