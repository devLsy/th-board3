package study.dev.thboard3.model.enu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResultCode {

    SUCCESS("1", "성공"),
    FAIL("2", "실패"),
    DUPLICATE("3", "데이터가 중복됩니다."),
    NOT_EXIST("4", "데이터가 존재하지 않습니다.");

    private final String code;
    private final String msg;

}
