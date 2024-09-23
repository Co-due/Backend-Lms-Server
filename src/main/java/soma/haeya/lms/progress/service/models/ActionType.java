package soma.haeya.lms.progress.service.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActionType {
    HELP(0), COMPLETE(1);

    private final int value;
}
