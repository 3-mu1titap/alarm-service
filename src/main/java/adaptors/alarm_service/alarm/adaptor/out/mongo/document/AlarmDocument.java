package adaptors.alarm_service.alarm.adaptor.out.mongo.document;

import adaptors.alarm_service.alarm.domain.model.AlarmType;
import adaptors.alarm_service.global.document.BaseDocument;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@Document(collection = "alarm")
public class AlarmDocument extends BaseDocument {

    @Id
    private String id;
    private String uuid;
    private String senderUuid;
    private String receiverUuid;
    private AlarmType alarmType;
    private String senderMessage;
    private String receiverMessage;
    private LocalDateTime triggerDate;

    private Boolean isDeleted;

    @Builder
    public AlarmDocument(
            String id,
            String uuid,
            String senderUuid,
            String receiverUuid,
            AlarmType alarmType,
            String senderMessage,
            String receiverMessage,
            LocalDateTime triggerDate,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Boolean isDeleted) {
        this.id = id;
        this.uuid = uuid;
        this.senderUuid = senderUuid;
        this.receiverUuid = receiverUuid;
        this.alarmType = alarmType;
        this.senderMessage = senderMessage;
        this.receiverMessage = receiverMessage;
        this.triggerDate = triggerDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

}