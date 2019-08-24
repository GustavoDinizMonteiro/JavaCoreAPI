package monteiro.gustavo.core.java.server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.undertow.server.HttpServerExchange;

public class Parsers {
	public static String getUrlParam(HttpServerExchange exchange, String field) {
		return exchange.getQueryParameters().get(field).getFirst();
	}

	public static String extractBody(HttpServerExchange exchange) {
		StringBuilder builder = new StringBuilder();

		try {
			exchange.startBlocking();
			var reader = new BufferedReader(new InputStreamReader(exchange.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}
}
