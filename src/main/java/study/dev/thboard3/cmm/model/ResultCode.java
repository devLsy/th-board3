package study.dev.thboard3.cmm.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResultCode {

    SUCCESS("1", "성공"),
    FAIL("2", "실패"),
    DUPLICATE("3", "중복");

    private final String code;
    private final String msg;

    /**
     * parameter로 넘어온 코드와 enum class의 코드값 일치 여부 체크
     * @param code
     * @return
     */
    public static boolean valueOfCode(String code) {

        if (code != null) {
            for (ResultCode resultCode : ResultCode.values()) {
                if (code.equals(resultCode.getCode())) {
                    return true;
                }
            }
        }
        return false;
    }

}
