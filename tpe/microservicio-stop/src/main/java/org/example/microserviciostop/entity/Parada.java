@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parada{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column
    private String direccion;
    @Column
    private String nombre;

    //para localizacion
    @Column
    private  int x;
    @Column
    private int y;

    public Parada(Long id, String direccion, String nombre, int x, int y) {
        this.id = id;
        this.direccion = direccion;
        this.nombre = nombre;
        this.x = x;
        this.y = y;
    }
}