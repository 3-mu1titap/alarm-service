package adaptors.alarm_service.alarm.adaptor.out.mongo.document;

import adaptors.alarm_service.alarm.domain.model.AlarmType;
import adaptors.alarm_service.global.document.BaseDocument;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Document(collection = "alarm")
public class AlarmDocument extends BaseDocument {

    @Id
    private String id;
    private String userUuid;
    private String sessionUuid;
    private AlarmType alarmType;
    private String message;

    private Boolean isDeleted;

    @Builder
    public AlarmDocument(
            String userUuid,
            String sessionUuid,
            AlarmType alarmType,
            String message,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Boolean isDeleted) {
        this.userUuid = userUuid;
        this.sessionUuid = sessionUuid;
        this.alarmType = alarmType;
        this.message = message;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

}