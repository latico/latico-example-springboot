
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PgDemoServiceImpl implements PgDemoService {

	@Autowired
	private PgDemoRepository rep;

	@Override
	public Result getOne(Integer id){
 		PgDemo one = rep.getOne(id);
		return Result.builder().success().data(one).build();
	}

	@Override
	public Result deleteOne(Integer id){
 		rep.deleteById(id);
		return Result.builder().success().build();
	}

	@Override
	public Result saveOne(PgDemo entity){
 		PgDemo one = rep.save(entity);
		return Result.builder().success().data(one).build();
	}
}
