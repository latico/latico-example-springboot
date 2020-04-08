
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

public interface PgDemoService extends BaseService<PgDemo, Integer>  {

   //根据id查询
   public Result getOne(Integer id);

   //根据id删除
   public Result deleteOne(Integer id);

   //根据id保存
   public Result saveOne(PgDemo entity);
}
