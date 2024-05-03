package fipe.io.github.deividfortuna.fipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Map;

public record VehicleModel(@JsonAlias("codigo") int code,
						   @JsonAlias("nome") String name) {

	@Override
	public String toString() {
		return "Código: " + code + ", " + name;
	}
}
