package application;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class QuoteTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test 
    public void testScoreEndpoint() {
        // Set up the request to the quote endpoint.
        QuoteRequest request = new QuoteRequest();
        request.setAge(38);
        request.setGender("male");
        request.setSmoker("former");
        request.setWeight(54.42);
        request.setHeight(176.03);
        // Invoke the assessment endpoint and check the quoted amount based on the mocked health score response.
        ResponseEntity<QuoteResponse> responseEntity = this.restTemplate.postForEntity("/quote", request, QuoteResponse.class);
        assertEquals("Incorrect quote returned", 50, responseEntity.getBody().getQuotedAmount());
  }

}