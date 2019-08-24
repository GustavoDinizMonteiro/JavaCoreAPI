package monteiro.gustavo.core.java.server.handlers;

import com.google.gson.Gson;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import monteiro.gustavo.core.java.server.repository.impls.ItemJDBC;

public class ItemHandler implements Handler {

	@Override
	public void getAll(HttpServerExchange exchange) {
		exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
		exchange.getResponseSender().send(new Gson().toJson(new ItemJDBC().list()));
	}

	@Override
	public void create(HttpServerExchange exchange) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getOne(HttpServerExchange exchange, Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOne(HttpServerExchange exchange, Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOne(HttpServerExchange exchange, Long id) {
		// TODO Auto-generated method stub
		
	}

}
