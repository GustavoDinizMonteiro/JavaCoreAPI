package monteiro.gustavo.core.java.server.repository.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import monteiro.gustavo.core.java.server.Connector;
import monteiro.gustavo.core.java.server.models.Item;
import monteiro.gustavo.core.java.server.repository.interfaces.ItemDAO;

public class ItemJDBC implements ItemDAO {

	@Override
	public Collection<Item> list() {
		try {
			var con = new Connector().getConnection();
			var stmt = con.createStatement();
	        var rs = stmt.executeQuery("SELECT * FROM items");
	        
	        var items = new HashSet<Item>();
	        while(rs.next()) 
	        	items.add(extractItem(rs));
	        
	        return items;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	private Item extractItem(ResultSet rs) throws SQLException {
		var item = new Item();
		item.setId(rs.getLong("id"));
		item.setName(rs.getString("name"));
		item.setPrice(rs.getInt("price"));
		return item;
	}

}
