package adaptors.alarm_service.global.document;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
public abstract class BaseDocument {

    @CreatedDate
    @Field("created_at")
    protected LocalDateTime createdAt; // 최초 생성일

    @LastModifiedDate
    @Field("updated_at")
    protected LocalDateTime updatedAt; // 마지막 수정일

}