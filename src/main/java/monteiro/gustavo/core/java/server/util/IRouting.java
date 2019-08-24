package monteiro.gustavo.core.java.server.util;

import io.undertow.server.RoutingHandler;
import monteiro.gustavo.core.java.server.handlers.Handler;

public interface IRouting {
	IRouting resource(String resource);
	IRouting handler(Handler handler);
	RoutingHandler build();
}
