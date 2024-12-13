package adaptors.alarm_service.alarm.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AlarmType {

    SESSION_REGISTER("세션 등록"),
    MENTORING_REGISTER("멘토링 등록"),
    REVIEW_REGISTER("리뷰 등록"),
    PAY_SESSION("세션 결제"),
    SESSION_CONFIRM("세션 확정");

    private final String alarmType;

    @JsonValue
    public String getAlarmType() {
        return alarmType;
    }

    @JsonCreator
    public static AlarmType fromString(String value) {
        for (AlarmType alarmType : AlarmType.values()) {
            if (alarmType.alarmType.equals(value)) {
                return alarmType;
            }
        }
        throw new IllegalArgumentException("Unknown alarm type: " + value);
    }

}