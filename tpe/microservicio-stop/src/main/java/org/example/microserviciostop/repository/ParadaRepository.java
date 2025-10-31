@Repository
public interface ParadaRepository extends JpaRepository<Parada, Long>{

    List<Parada> findByUserId(Long id);
}