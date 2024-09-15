package group5.eeet2580_project.dto.response;

import group5.eeet2580_project.entity.SpraySession;
import group5.eeet2580_project.entity.User;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SpraySessionResponse implements Data {
    private Long id;

    private Long order_id;

    private List<String> sprayers;

    private LocalDate date;

    private SpraySession.TimeSlot timeSlot;

    public SpraySessionResponse(SpraySession spraySession) {
        this.id = spraySession.getId();
        this.order_id = spraySession.getOrder().getId();
        this.sprayers = spraySession.getSprayers().stream().map(User::getUsername).collect(Collectors.toList());
        this.date = spraySession.getDate();
        this.timeSlot = spraySession.getTimeSlot();
    }
}