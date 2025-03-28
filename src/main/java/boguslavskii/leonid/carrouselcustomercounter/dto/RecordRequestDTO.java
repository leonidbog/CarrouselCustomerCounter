package boguslavskii.leonid.carrouselcustomercounter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class RecordRequestDTO {
    private String date; // Ожидается строка в ISO-формате
    private String attraction;
    private Map<String, Integer> hourlyCountsOperator;
    private Map<String, Integer> hourlyCountsAdmin;

}
