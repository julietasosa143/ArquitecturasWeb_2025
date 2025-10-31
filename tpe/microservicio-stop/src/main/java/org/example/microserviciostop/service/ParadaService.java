@Service
public class ParadaService{
    private ParadaRepository paradaRepository;

    public ParadaService(ParadaRepository paradaRepository){

        this.paradaRepository = paradaRepository;

    }
    public List<Parada> getAll() { return paradaRepository.findAll();}

    public Parada save(Parada parada){
        Parada paradaNew;
        paradaNew = paradaRepository.save(parada);
        return paradaNew;
    }
    public void delete(Parada parada){

        bikeRepository.delete(parada);
    }
    public Parada findById(Long id) { return bikeRepository.findById(id).orElse(null);}

    public Parada update(Parada parada) { return paradaRepository.save(parada);}

    public List<Parada> byUserId(Long id) { return userRepository.findByUserId(id); }
}