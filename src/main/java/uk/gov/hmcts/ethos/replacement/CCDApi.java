package uk.gov.hmcts.ethos.replacement;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "CCDService")
public interface CCDApi {
    @RequestMapping(method = RequestMethod.POST, path = "/caseworkers/18/jurisdictions/EMPLOYMENT/case-types/Manchester_Dev/cases")
    int createCase(@RequestBody String json);
}
