package fipe.io.github.deividfortuna.fipe.services;

public interface IDataConverter {
	<T> T getData(String json, Class<T> tClass);
}
