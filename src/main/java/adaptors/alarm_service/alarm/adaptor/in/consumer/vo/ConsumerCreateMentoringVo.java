package adaptors.alarm_service.alarm.adaptor.in.consumer.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ConsumerCreateMentoringVo {

    private String mentorUuid;
    @JsonProperty("name")
    private String mentoringName;

}