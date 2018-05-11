package pl.zzpwj.game.mapper;

import org.springframework.stereotype.Component;
import pl.zzpwj.game.dto.UserDto;
import pl.zzpwj.game.model.User;

@Component
public class UserMapper implements IMapper<User, UserDto> {

    @Override
    public UserDto mapToDto(User entity) {
        return new UserDto(entity.getLogin(), entity.getEmail(), "");
    }

    @Override
    public User mapToEntity(UserDto dto) {
        return new User(dto.getLogin(), dto.getEmail(), dto.getPassword());
    }
}
