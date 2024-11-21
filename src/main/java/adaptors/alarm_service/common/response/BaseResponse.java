package adaptors.alarm_service.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import static adaptors.alarm_service.common.response.BaseResponseStatus.SUCCESS;

public record BaseResponse<T>(HttpStatusCode httpStatus, Boolean isSuccess, String message, int code, T result) {

    /**
     * 1. Return 객체가 필요한 경우 -> 성공
     *
     * @param result
     */
    public BaseResponse(T result) {
        this(HttpStatus.OK, true, SUCCESS.getMessage(), SUCCESS.getCode(), result);
    }

    /**
     * 2. Return 객체가 필요 없는 경우 -> 성공
     */

    public BaseResponse() {
        this(HttpStatus.OK, true, SUCCESS.getMessage(), SUCCESS.getCode(), null);
    }

    /**
     * 3. 요청에 실패한 경우
     *
     * @param status
     */
    public BaseResponse(BaseResponseStatus status) {
        this(status.getHttpStatusCode(), false, status.getMessage(), status.getCode(), null);
    }

}