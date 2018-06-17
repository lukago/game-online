package pl.zzpwj.game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.zzpwj.game.engine.FieldState;
import pl.zzpwj.game.engine.ShotResult;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerBoardDto {
    private FieldState[][] fieldStates;
    private ShotResult[][] shotResults;
}
