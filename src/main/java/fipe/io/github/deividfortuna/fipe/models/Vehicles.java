package fipe.io.github.deividfortuna.fipe.models;


import com.fasterxml.jackson.annotation.JsonAlias;

public record Vehicles(@JsonAlias("codigo") int code,
					   @JsonAlias("nome") String brand) {

	@Override
	public String toString() {
		return "Código da marca: " + this.code + ", Marca: " + this.brand;
	}
}
