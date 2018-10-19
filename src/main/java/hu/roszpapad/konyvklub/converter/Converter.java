package hu.roszpapad.konyvklub.converter;

public interface Converter<T,P> {

    P toDTO(T entity);
    T toEntity(P dto);
}
