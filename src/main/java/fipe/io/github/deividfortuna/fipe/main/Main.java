package fipe.io.github.deividfortuna.fipe.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fipe.io.github.deividfortuna.fipe.models.Response;
import fipe.io.github.deividfortuna.fipe.models.VehicleModel;
import fipe.io.github.deividfortuna.fipe.models.VehicleModelVariation;
import fipe.io.github.deividfortuna.fipe.models.Vehicles;
import fipe.io.github.deividfortuna.fipe.services.ConsumeApi;
import fipe.io.github.deividfortuna.fipe.services.DataConverter;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
	private DataConverter dataConverter = new DataConverter();
	private Scanner scanner = new Scanner(System.in);
	private ObjectMapper objectMapper = new ObjectMapper();

	private final String ADDRESS = "https://parallelum.com.br/fipe/api/v1/";
	private final String BRANDS = "/marcas/";
	private final String MODELS = "/modelos/";
	private final String YEARS = "/anos/";


	public void showMenu () throws JsonProcessingException {
		List<String> choices = new ArrayList<>(
				Arrays.asList("Carros", "Motos", "Caminhoes")
		);

		System.out.println("\nEscolha uma das opções: ");
		choices.forEach(System.out::println);
		var choice = scanner.nextLine();

		Optional<String> clientChoice = choices.stream()
				.filter(c -> c.toLowerCase().contains(choice))
				.findFirst();

		if (clientChoice.isEmpty()) {
			throw new IllegalArgumentException("A opção selecionada foi inválida!");
		}

		var json = ConsumeApi.getData(ADDRESS +
				clientChoice.get().toLowerCase() + BRANDS);

		List<Vehicles> jsonList = objectMapper.readValue(
				json, new TypeReference<List<Vehicles>>() {}
		);

		jsonList.stream()
				.sorted(Comparator.comparing(Vehicles::code))
				.forEach(System.out::println);

		System.out.println("\nDigite o código da marca para filtrar: ");
		var brandCode = scanner.nextInt();

		var brandJson = ConsumeApi.getData(ADDRESS +
				clientChoice.get().toLowerCase() + BRANDS +
				brandCode + MODELS);

		Response response = objectMapper.readValue(brandJson, Response.class);
		List<VehicleModel> modelsList = response.models();
		var models = modelsList.stream().sorted(Comparator.comparing(VehicleModel::code));

		System.out.println("\n");
		models.forEach(System.out::println);

		System.out.println("\nDigite o código do modelo que deseja ver:");
		var vehicleModel = scanner.nextInt();

		var modelJson = ConsumeApi.getData(
				ADDRESS + clientChoice.get().toLowerCase()
				+ BRANDS + brandCode + MODELS + vehicleModel + YEARS
		);

		List<VehicleModelVariation> yearsList = objectMapper.readValue(
				modelJson, new TypeReference<List<VehicleModelVariation>>() {}
		);

		yearsList.stream()
				.sorted(Comparator.comparing(VehicleModelVariation::date).reversed())
				.limit(5)
				.forEach(System.out::println);
	}
}
