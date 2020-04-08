
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgDemoRepository extends BaseRepository<PgDemo, Integer>{
    
}
