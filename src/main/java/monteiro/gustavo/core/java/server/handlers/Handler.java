package monteiro.gustavo.core.java.server.handlers;

import io.undertow.server.HttpServerExchange;

public interface Handler {
	void getAll(HttpServerExchange exchange);
	void create(HttpServerExchange exchange);
	void getOne(HttpServerExchange exchange, Long id);
	void updateOne(HttpServerExchange exchange, Long id);
	void deleteOne(HttpServerExchange exchange, Long id);
}
