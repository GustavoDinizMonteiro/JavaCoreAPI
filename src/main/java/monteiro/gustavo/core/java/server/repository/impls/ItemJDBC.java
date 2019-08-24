package monteiro.gustavo.core.java.server.repository.impls;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import monteiro.gustavo.core.java.server.Connector;
import monteiro.gustavo.core.java.server.models.Item;
import monteiro.gustavo.core.java.server.repository.interfaces.ItemDAO;

public class ItemJDBC implements ItemDAO {
	private final String SELECT = "SELECT * FROM items";
	private final String INSERT = "INSERT INTO items (name, price) VALUES (?, ?)";
	private final String GET_ONE = "SELECT * FROM items WHERE id=?";
	private final String UPDATE = "UPDATE items SET name=?, price=? WHERE id=?";
	private final String DELETE = "DELETE FROM items WHERE id=?";

	@Override
	public Collection<Item> list() {
		try {
			var con = new Connector().getConnection();
			var stmt = con.createStatement();
	        var rs = stmt.executeQuery(SELECT);
	        
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
	        var ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
	        
	        ps.setString(1, item.getName());
	        ps.setInt(2, item.getPrice());
	        
	        ps.execute();
	        item.setId(getID(ps));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return item;
	}

	@Override
	public Item getOne(Long id) {
		var con = new Connector().getConnection();
		try {
			var ps = con.prepareStatement(GET_ONE);
			ps.setLong(1, id);
			var rs = ps.executeQuery();
			rs.next();
			return extractItem(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Item update(Long id, Item item) {
		try {
			var con = new Connector().getConnection();
			var ps = con.prepareStatement(UPDATE);
			ps.setString(1, item.getName());
			ps.setInt(2, item.getPrice());
			ps.setLong(3, id);
			
			var success = ps.executeUpdate();
			if (success == 1) return item;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return item;
	}

	@Override
	public Boolean delete(Long id) {
		try {
			var con = new Connector().getConnection();
			var ps = con.prepareStatement(DELETE);
			ps.setLong(1, id);
			return ps.executeUpdate() == 1;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	private Long getID(PreparedStatement ps) throws SQLException {
		var rs = ps.getGeneratedKeys();
		rs.next();
		return rs.getLong(1);
	}
	
	private Item extractItem(ResultSet rs) throws SQLException {
		var item = new Item();
		item.setId(rs.getLong("id"));
		item.setName(rs.getString("name"));
		item.setPrice(rs.getInt("price"));
		return item;
	}
}
