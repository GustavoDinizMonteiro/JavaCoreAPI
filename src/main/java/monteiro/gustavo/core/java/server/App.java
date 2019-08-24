package monteiro.gustavo.core.java.server;

import io.undertow.Handlers;
import io.undertow.Undertow;
import monteiro.gustavo.core.java.server.handlers.ItemHandler;
import monteiro.gustavo.core.java.server.util.Routing;

public class App {
	public static void main(String[] args) {
		Undertow.builder().addHttpListener(8080, "localhost")
				.setHandler(Handlers.path().addPrefixPath("/api", 
						new Routing().resource("item").handler(new ItemHandler()).build()
				)).build().start();
	}
}
