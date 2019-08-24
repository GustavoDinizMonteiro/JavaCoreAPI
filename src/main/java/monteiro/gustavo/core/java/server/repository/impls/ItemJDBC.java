package monteiro.gustavo.core.java.server.repository.impls;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

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
	        
	        var items = new ArrayList<Item>();
	        while(rs.next()) 
	        	items.add(extractItem(rs));
	        
	        return items;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Item create(Item item) {
		try {
			var con = new Connector().getConnection();
	        var ps = con.prepareStatement("INSERT INTO items (name, price) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
	        
	        ps.setString(1, item.getName());
	        ps.setInt(2, item.getPrice());
	        
	        ps.execute();
	        var rs = ps.getGeneratedKeys();
	        rs.next();
	        item.setId(rs.getLong(1));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return item;
	}

	private Item extractItem(ResultSet rs) throws SQLException {
		var item = new Item();
		item.setId(rs.getLong("id"));
		item.setName(rs.getString("name"));
		item.setPrice(rs.getInt("price"));
		return item;
	}

}
