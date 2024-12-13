package adaptors.alarm_service.alarm.adaptor.in.consumer.vo;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class ConsumerUpdateSessionUserVo {

    private String userUuid;
    private String sessionUuid;
    private LocalDate startDate;

}