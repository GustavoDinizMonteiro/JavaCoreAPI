package monteiro.gustavo.core.java.server;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.util.Methods;

public class App {
	public static void main(String[] args) {
		Undertow.builder().addHttpListener(8080, "localhost")
				.setHandler(Handlers.path().addPrefixPath("/api", Handlers.routing()
						.add(Methods.GET,"/item", exchange -> {
							exchange.getResponseSender().send("Get all");
						})
						.add(Methods.POST,"/item", exchange -> {
							exchange.getResponseSender().send("post");
						})
						.add(Methods.GET,"/item/{itemId}", exchange -> {
							exchange.getResponseSender().send("Get one"
									+exchange.getQueryParameters().get("itemId").getFirst());
						})
						.add(Methods.PUT,"/item/{itemId}", exchange -> {
							exchange.getResponseSender().send("put");
						})
						.add(Methods.DELETE,"/item/{itemId}", exchange -> {
							exchange.getResponseSender().send("Delete");
						})
				)).build().start();
	}
}
