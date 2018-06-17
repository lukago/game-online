package pl.zzpwj.game.mapper;

import org.springframework.stereotype.Component;
import pl.zzpwj.game.dto.PlayerBoardDto;
import pl.zzpwj.game.dto.UserDto;
import pl.zzpwj.game.engine.PlayerBoard;
import pl.zzpwj.game.model.User;

@Component
public class PlayerBoardMapper implements IMapper<PlayerBoard, PlayerBoardDto> {

    @Override
    public PlayerBoardDto mapToDto(PlayerBoard entity) {
        return new PlayerBoardDto(entity.getBoardState(),entity.getShotResults());
    }

    @Override
    public PlayerBoard mapToEntity(PlayerBoardDto dto) {
        return new PlayerBoard(dto.getFieldStates(),dto.getShotResults());
    }
}
