package monteiro.gustavo.core.java.server.repository.interfaces;

import java.util.Collection;

import monteiro.gustavo.core.java.server.models.Item;

public interface ItemDAO {
	Collection<Item> list();
	Item create(Item item);
}
