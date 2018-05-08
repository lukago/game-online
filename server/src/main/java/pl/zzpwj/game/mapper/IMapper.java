package pl.zzpwj.game.mapper;

public interface IMapper<E, D> {

    D mapToDto(E entity);

    E mapToEntity(D dto);
}
