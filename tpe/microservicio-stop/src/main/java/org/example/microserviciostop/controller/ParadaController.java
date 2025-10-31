import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stops")
public class ParadaController {
    private final ParadaService paradaService;

    public ParadaController(ParadaService paradaService){
        this.paradaService = paradaService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Parada>> getAllParadas(){
        List<Parada> paradas = stopService.getAll();
        if (paradas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(paradas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parada> getParadaById(@PathVariable("id") Long id) {
        Parada parada = ParadaService.findById(id);
        if (parada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(parada);
    }

    @PostMapping("")
    public ResponseEntity<Parada> save(@RequestBody Parada parada) {
        Parada paradaNew = paradaService.save(parada);
        return ResponseEntity.ok(paradaNew);
    }

    @GetMapping("/byUser/{id}")
    public ResponseEntity<List<Parada>> getTripsByUserId(@PathVariable("id") Long id) {
        List<Parada> paradas = paradaService.byUserId(id);
        return ResponseEntity.ok(paradas);
    }
}

