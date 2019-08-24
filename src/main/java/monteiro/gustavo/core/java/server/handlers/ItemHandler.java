package monteiro.gustavo.core.java.server.handlers;

import com.google.gson.Gson;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import monteiro.gustavo.core.java.server.models.Item;
import monteiro.gustavo.core.java.server.repository.impls.ItemJDBC;
import monteiro.gustavo.core.java.server.util.Parsers;

public class ItemHandler implements Handler {

	@Override
	public void getAll(HttpServerExchange exchange) {
		exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
		exchange.getResponseSender().send(new Gson().toJson(new ItemJDBC().list()));
	}

	@Override
	public void create(HttpServerExchange exchange) {
		var body = Parsers.extractBody(exchange);
		var item = new Gson().fromJson(body, Item.class);
		
		exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
		exchange.getResponseSender().send(new Gson().toJson(new ItemJDBC().create(item)));
	}

	@Override
	public void getOne(HttpServerExchange exchange, Long id) {
		exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
		exchange.getResponseSender().send(new Gson().toJson(new ItemJDBC().getOne(id)));
	}

	@Override
	public void updateOne(HttpServerExchange exchange, Long id) {
		var body = Parsers.extractBody(exchange);
		var item = new Gson().fromJson(body, Item.class);
		
		exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
		exchange.getResponseSender().send(new Gson().toJson(new ItemJDBC().update(id, item)));
	}

	@Override
	public void deleteOne(HttpServerExchange exchange, Long id) {
		exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
		exchange.getResponseSender().send(new Gson().toJson(new ItemJDBC().delete(id)));
	}

}
