package fipe.io.github.deividfortuna.fipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;

public record VehicleModelVariation(@JsonAlias("codigo") String date,
									@JsonAlias("nome") String name) {

	@Override
	public String toString() {
		return "Lançamento: " + date + ", modelo: " + name;
	}
}
