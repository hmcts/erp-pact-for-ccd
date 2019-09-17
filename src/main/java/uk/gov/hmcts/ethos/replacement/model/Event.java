package uk.gov.hmcts.ethos.replacement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
public class Event {
    @JsonProperty("id")
    private String eventId;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("description")
    private String description;

}
