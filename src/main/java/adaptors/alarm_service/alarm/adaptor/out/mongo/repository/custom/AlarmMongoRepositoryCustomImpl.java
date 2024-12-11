package adaptors.alarm_service.alarm.adaptor.out.mongo.repository.custom;

import adaptors.alarm_service.alarm.adaptor.out.mongo.document.AlarmDocument;
import adaptors.alarm_service.alarm.adaptor.out.mongo.mapper.AlarmDocumentMapper;
import adaptors.alarm_service.alarm.application.port.in.dto.rest.RestReadAlarmDto;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmReadQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Criteria.*;

@Repository
@RequiredArgsConstructor
public class AlarmMongoRepositoryCustomImpl implements AlarmMongoRepositoryCustom {

    private final MongoTemplate mongoTemplate;
    private final AlarmDocumentMapper alarmDocumentMapper;

    @Override
    public AlarmReadQueryDto findAlarm(RestReadAlarmDto readAlarmDto) {

        String userUuid = readAlarmDto.getUserUuid();
        String uuid = readAlarmDto.getUuid();

        Query query = new Query(
                new Criteria().andOperator(
                        where("uuid").is(uuid),
                        where("isDeleted").is(false),
                        new Criteria().orOperator(
                                where("senderUuid").is(userUuid),
                                where("receiverUuid").is(userUuid)
                        )
                )
        );

        return alarmDocumentMapper.toReadQueryDto(Objects.requireNonNull(
                mongoTemplate.findOne(query, AlarmDocument.class)
        ));
    }

    @Override
    public Slice<AlarmReadQueryDto> findAlarmsByUserUuid(Pageable pageable, String userUuid) {

        // 카운트 쿼리 생성
        Query countQuery = new Query(
                new Criteria().andOperator(
                        where("isDeleted").is(false),
                        new Criteria().orOperator(
                                where("senderUuid").is(userUuid),
                                where("receiverUuid").is(userUuid)
                        )
                )
        );

        // 조회 쿼리 생성 (페이징 및 정렬 포함)
        Query query = new Query(
                new Criteria().andOperator(
                        where("isDeleted").is(false),
                        new Criteria().orOperator(
                                where("senderUuid").is(userUuid),
                                where("receiverUuid").is(userUuid)
                        )
                )
        ).with(Sort.by(Sort.Direction.DESC, "createdAt"))
                .with(pageable);

        List<AlarmDocument> alarmDocuments = mongoTemplate.find(query, AlarmDocument.class);

        List<AlarmReadQueryDto> alarmReadQueryDtos = alarmDocuments.stream()
                .map(alarmDocumentMapper::toReadQueryDto)
                .toList();

        // 다음 페이지 존재 여부 확인
        long totalCount = mongoTemplate.count(countQuery, AlarmDocument.class);
        boolean hasNext = (long) (pageable.getPageNumber() + 1) * pageable.getPageSize() < totalCount;

        return new SliceImpl<>(alarmReadQueryDtos, pageable, hasNext);
    }

}