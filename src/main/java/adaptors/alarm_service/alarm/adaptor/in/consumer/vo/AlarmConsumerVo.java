package adaptors.alarm_service.alarm.adaptor.in.consumer.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AlarmConsumerVo {

    @JsonProperty("menteeUuid")
    private String userUuid;
    private String sessionUuid;

}