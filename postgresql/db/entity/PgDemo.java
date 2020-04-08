
import lombok.Data;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pg_demo")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class PgDemo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auto_id")
	private Integer autoId;

	private String id;

	private String name;

	private String price;

}
