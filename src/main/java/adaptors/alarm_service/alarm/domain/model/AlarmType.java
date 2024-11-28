package adaptors.alarm_service.alarm.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AlarmType {

    SESSION("세션"),
    PAYMENT("결제"),
    REVIEW("리뷰");

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