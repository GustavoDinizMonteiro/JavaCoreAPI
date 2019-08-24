package monteiro.gustavo.core.java.server.util;

import io.undertow.server.HttpServerExchange;

public class Parsers {
	public static String getUrlParam(HttpServerExchange exchange, String field) {
		return exchange.getQueryParameters().get(field).getFirst();
	}
}
