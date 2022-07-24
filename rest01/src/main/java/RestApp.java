import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/") // Ponemos el mapeo directamente a la raíz con eso evitamos tener la parte del servlet en el path
public class RestApp extends Application {


}
