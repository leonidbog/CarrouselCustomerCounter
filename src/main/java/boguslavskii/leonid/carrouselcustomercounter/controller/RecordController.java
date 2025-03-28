package boguslavskii.leonid.carrouselcustomercounter.controller;

import boguslavskii.leonid.carrouselcustomercounter.dto.RecordRequestDTO;
import boguslavskii.leonid.carrouselcustomercounter.dto.RecordResponseDTO;
import boguslavskii.leonid.carrouselcustomercounter.dto.Response;
import boguslavskii.leonid.carrouselcustomercounter.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@AllArgsConstructor
public class RecordController {

    private final RecordService recordService;


    @PostMapping
    public ResponseEntity<Response> createRecord(@RequestBody RecordRequestDTO recordRequestDTO, @AuthenticationPrincipal UserDetails details) {
        RecordResponseDTO createdRecord = recordService.createRecord(recordRequestDTO, details);
        return new ResponseEntity<>(
                Response.builder()
                .success(true).
                recordId(createdRecord.getId())
                        .build(),
                HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<RecordResponseDTO>> getAllRecords() {
        List<RecordResponseDTO> records = recordService.getAllRecords();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }
    @GetMapping("test")
    public String test(){
        return "ok";
    }
}
