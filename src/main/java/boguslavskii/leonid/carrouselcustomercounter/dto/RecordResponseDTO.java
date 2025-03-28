package boguslavskii.leonid.carrouselcustomercounter.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class RecordResponseDTO {
    private Long id;
    private String date; // Ожидается строка в ISO-формате
    private String attraction;
    private String username;
    private Map<String, Integer> hourlyCountsOperator;
    private Map<String, Integer> hourlyCountsAdmin;

    public RecordResponseDTO() {
    }

    public RecordResponseDTO(Long id, String date, String attraction, String username, Map<String, Integer> hourlyCountsAdmin, Map<String, Integer> hourlyCountOperator) {
        this.id = id;
        this.date = date;
        this.attraction = attraction;
        this.username = username;
        this.hourlyCountsAdmin = hourlyCountsAdmin;
        this.hourlyCountsOperator = hourlyCountOperator;
    }

}