// 代码生成时间: 2025-09-20 02:30:59
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang3.SystemUtils;

@Path("/process-manager")
public class ProcessManager {

    // 获取当前运行的进程信息
    @GET
    @Path("/processes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProcessInfo> getProcesses() {
        // 使用RuntimeMXBean获取运行中的进程信息
        java.lang.management.RuntimeMXBean runtimeMXBean = java.lang.management.ManagementFactory.getRuntimeMXBean();

        // 获取所有JVM进程名称
        List<String> jvmProcessNames = runtimeMXBean.getInputArguments().stream()
                .filter(arg -> arg.contains("-Djava.class.path=") || arg.contains("-Djava.library.path=") || arg.contains("-XX:OnOutOfMemoryError=") || arg.contains("-XX:HeapDumpPath="))
                .map(arg -> arg.split("=")[1])
                .collect(Collectors.toList());

        // 过滤出当前JVM进程的名称
        String currentJvmProcessName = jvmProcessNames.stream()
                .filter(name -> name.contains(SystemUtils.USER_NAME))
                .findFirst()
                .orElse("Unknown");

        // 返回当前JVM进程信息的列表
        return List.of(new ProcessInfo(currentJvmProcessName));
    }

    // 内部类，用于封装进程信息
    public static class ProcessInfo {
        private String processName;

        public ProcessInfo(String processName) {
            this.processName = processName;
        }

        public String getProcessName() {
            return processName;
        }

        public void setProcessName(String processName) {
            this.processName = processName;
        }
    }
}
