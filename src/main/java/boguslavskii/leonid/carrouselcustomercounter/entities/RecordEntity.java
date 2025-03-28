package boguslavskii.leonid.carrouselcustomercounter.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Map;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDate date;

    private String attraction;

    @ElementCollection
    @CollectionTable(name = "operator_hourly_counts", joinColumns = @JoinColumn(name = "record_id"))
    @MapKeyColumn(name = "hour")
    @Column(name = "count")
    private Map<String, Integer> hourlyCountsOperator;

    @ElementCollection
    @CollectionTable(name = "admin_hourly_counts", joinColumns = @JoinColumn(name = "record_id"))
    @MapKeyColumn(name = "hour")
    @Column(name = "count")
    private Map<String, Integer> hourlyCountsAdmin;

    public RecordEntity(User user, LocalDate date, String attraction, Map<String, Integer> hourlyCountsOperator, Map<String, Integer> hourlyCountsAdmin){
        this.user = user;
        this.date = date;
        this.attraction = attraction;
        this.hourlyCountsAdmin = hourlyCountsAdmin;
        this.hourlyCountsOperator = hourlyCountsOperator;
    }
}
