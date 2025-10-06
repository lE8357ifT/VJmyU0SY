// 代码生成时间: 2025-10-06 18:26:39
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
# FIXME: 处理边界情况
import javax.ws.rs.core.Response;
import java.net.URL;

@Path("/url-validator")
public class URLValidatorService {
# FIXME: 处理边界情况

    // Validates the URL and returns the result.
    // If the URL is valid, it returns a 200 status with a success message.
# 添加错误处理
    // Otherwise, it returns a 400 status with an error message.
    @GET
    public Response validateURL(@QueryParam("url") String urlString) {
        try {
            // Try to create a URL object to check if the string is a valid URL
            URL url = new URL(urlString);
            // Open a connection to ensure the URL is reachable
            url.openConnection().connect();
            return Response.ok("The URL is valid and reachable.", MediaType.TEXT_PLAIN).build();
        } catch (Exception e) {
            // Catch any exceptions that indicate an invalid or unreachable URL
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The URL is invalid or unreachable.")
                    .type(MediaType.TEXT_PLAIN).build();
# 改进用户体验
        }
    }
}
