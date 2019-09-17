package uk.gov.hmcts.ethos.replacement.model.items;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import uk.gov.hmcts.ethos.replacement.model.types.ReconsiderationType;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ReconsiderationTypeItem {

    @JsonProperty("id")
    private String id;
    @JsonProperty("value")
    private ReconsiderationType value;
}
