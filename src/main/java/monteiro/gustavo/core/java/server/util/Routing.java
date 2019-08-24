package monteiro.gustavo.core.java.server.util;

import io.undertow.Handlers;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import io.undertow.util.Methods;
import monteiro.gustavo.core.java.server.handlers.Handler;

public class Routing implements IRouting {
	private String resource;
	private String resourceEl;
	private Handler handler;

	@Override
	public IRouting resource(String resource) {
		this.resource = "/" + resource;
		this.resourceEl = "/" + resource + "/{id}";
		return this;
	}

	@Override
	public IRouting handler(Handler handler) {
		this.handler = handler;
		return this;
	}

	public RoutingHandler build() {
		return Handlers.routing()
				.add(Methods.GET, this.resource, exchange -> handler.getAll(exchange))
				.add(Methods.POST, this.resource, exchange -> handler.create(exchange))
				.add(Methods.GET, this.resourceEl, exchange -> handler.getOne(exchange, extractResourceId(exchange)))
				.add(Methods.PUT, this.resourceEl, exchange -> handler.updateOne(exchange, extractResourceId(exchange)))
				.add(Methods.DELETE, this.resourceEl, exchange -> handler.deleteOne(exchange, extractResourceId(exchange)));
	}
	
	private Long extractResourceId(HttpServerExchange exchange) {
		return Long.getLong(Parsers.getUrlParam(exchange, "id"));
	}

}
