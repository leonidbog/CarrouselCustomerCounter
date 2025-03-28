package boguslavskii.leonid.carrouselcustomercounter.service;

import boguslavskii.leonid.carrouselcustomercounter.dto.RecordRequestDTO;
import boguslavskii.leonid.carrouselcustomercounter.dto.RecordResponseDTO;
import boguslavskii.leonid.carrouselcustomercounter.entities.RecordEntity;
import boguslavskii.leonid.carrouselcustomercounter.entities.User;
import boguslavskii.leonid.carrouselcustomercounter.repository.RecordRepository;
import boguslavskii.leonid.carrouselcustomercounter.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;


    public RecordResponseDTO createRecord(RecordRequestDTO requestDTO, UserDetails details) {
        User user = userRepository.findByUsername(details.getUsername()).get();
        LocalDate recordDate = LocalDate.parse(requestDTO.getDate());
        RecordEntity recordEntity = new RecordEntity(user, recordDate,
                requestDTO.getAttraction(),
                requestDTO.getHourlyCountsOperator(),
                requestDTO.getHourlyCountsAdmin()
        );
        RecordEntity savedRecord = recordRepository.save(recordEntity);
        return mapToResponseDTO(savedRecord);
    }

    public List<RecordResponseDTO> getAllRecords() {
        return recordRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private RecordResponseDTO mapToResponseDTO(RecordEntity recordEntity) {

        return new RecordResponseDTO(
                recordEntity.getId(),
                recordEntity.getDate().toString(),
                recordEntity.getAttraction(),
                recordEntity.getUser().getUsername(),
                recordEntity.getHourlyCountsAdmin(),
                recordEntity.getHourlyCountsOperator()
        );
    }
}