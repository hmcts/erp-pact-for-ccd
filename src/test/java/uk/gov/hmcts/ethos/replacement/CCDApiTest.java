package uk.gov.hmcts.ethos.replacement;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;

@ExtendWith(PactConsumerTestExt.class)
@ExtendWith(SpringExtension.class)
@PactTestFor(providerName = "CCDService", port = "8888")
@SpringBootTest({
        // overriding provider address
        "CCDService.ribbon.listOfServers: localhost:8888"
})
public class CCDApiTest {
    @Autowired
    private CCDApi ccdApi;

    @Pact(state = "Create case in CCD", provider = "ccd-data-store-api", consumer = "ethos-repl-docmosis-service")
    RequestResponsePact createCasePact(PactDslWithProvider builder) throws IOException {
        String Uri = String.format("/caseworkers/%s/jurisdictions/%s/case-types/%s/cases", "18", "EMPLOYMENT", "Manchester_Dev");
        String json = FileUtils.readFileToString(new File("src/test/resources/caseDetailsEng1Test1.json"), "UTF-8");
        // @formatter:off
        return builder
                .given("provider creates a new Case")
                .uponReceiving("a request to create Case")
                .path(Uri)
                .method("POST")
                .body(json)
                .willRespondWith()
                .status(201)
                .matchHeader("Content-Type", "application/json")
                .body(new PactDslJsonBody()
                    .includesStr("case_type_id", "Manchester_Dev"))
                .toPact();
        // @formatter:on
    }

    @Test
    @PactTestFor(pactMethod = "createCasePact")
    void verifyCreateCase() throws IOException {
        String json = FileUtils.readFileToString(new File("src/test/resources/caseDetailsEng1Test1.json"), "UTF-8");
        int responseStatus = ccdApi.createCase(json);
        Assert.assertEquals(201, responseStatus);
    }

}
