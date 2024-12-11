package adaptors.alarm_service.alarm.adaptor.in.consumer.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ConsumerCreateReviewVo {

    private String menteeUuid;
    private String mentorUuid;
    private String mentoringName;
    @JsonProperty("nickName")
    private String menteeNickName;

}