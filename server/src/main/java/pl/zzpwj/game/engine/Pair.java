package pl.zzpwj.game.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pair<T1, T2> {
    private T1 Key;
    private T2 Value;
}

