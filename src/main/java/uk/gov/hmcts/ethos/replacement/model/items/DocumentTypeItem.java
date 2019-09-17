package uk.gov.hmcts.ethos.replacement.model.items;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import uk.gov.hmcts.ethos.replacement.model.types.DocumentType;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DocumentTypeItem {

    @JsonProperty("id")
    private String id;
    @JsonProperty("value")
    private DocumentType value;
}
