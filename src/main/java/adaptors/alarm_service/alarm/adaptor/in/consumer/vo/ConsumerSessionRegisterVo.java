package adaptors.alarm_service.alarm.adaptor.in.consumer.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ConsumerSessionRegisterVo {

    private String menteeUuid;
    private String mentorUuid;
    @JsonProperty("nickName")
    private String menteeNickName;
    private String mentoringName;

}