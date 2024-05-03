package fipe.io.github.deividfortuna.fipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Response(@JsonAlias("modelos") List<VehicleModel> models) {
}
