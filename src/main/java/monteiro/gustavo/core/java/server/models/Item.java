package monteiro.gustavo.core.java.server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	private Long Id;
	private String name;
	private Integer price;
}
