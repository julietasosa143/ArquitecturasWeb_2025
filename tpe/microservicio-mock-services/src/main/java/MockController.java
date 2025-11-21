import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MockController {
    @GetMapping("/connect")
    public Map<String, Object> connectToMaps(
            @RequestParam float x,
            @RequestParam float y
    ) {
        return Map.of(
                "message", "Conectando con Google Maps... Búsqueda simulada",
                "status", "OK"
        );
    }
}
