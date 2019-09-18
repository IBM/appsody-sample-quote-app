package application;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Quote {

    private static Logger logger = Logger.getLogger(Quote.class.getName());

    @Value("${DACADOO_URL}")
    String dacadooUrl;

    @Value("${DACADOO_APIKEY}")
    String dacadooApikey;

    @PostMapping("/quote")
    public QuoteResponse score(@RequestBody QuoteRequest request) {

        logger.info("Received request - " + request.toString());

        // Build health score request
        HealthScoreRequest hs = new HealthScoreRequest();
        HealthScoreRequest.MetricHealthModel mhm = hs.getMhm();
        HealthScoreRequest.Smoking smk = hs.getSmk();

        mhm.setAge(request.getAge());
        mhm.setHgt(request.getHeight());
        mhm.setWgt(request.getWeight());
        
        if (request.getGender().equalsIgnoreCase("male")) {
            mhm.setSex(1);
        }

        if (request.getSmoker().contains("former")) {
            smk.setEvr(1);
        } else if (request.getSmoker().contains("current")) {
            smk.setNow(1);
        }

        if (request.isCancer()) {
            mhm.setCnr(1);
        }

        if (request.isCvd()) {
            mhm.setCvd(1);
        }

        hs.setClip(true);

        logger.info("Calling dacadoo health score API (" + dacadooUrl + "): " + hs.toString());

        // Invoke dacadoo health score api

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-dacadoo-Key", dacadooApikey);      
        HttpEntity<HealthScoreRequest> hsRequestEntity = new HttpEntity<>(hs, headers);
        ResponseEntity<HealthScoreResponse> hsResponseEntity = restTemplate.exchange(dacadooUrl, HttpMethod.POST, hsRequestEntity, HealthScoreResponse.class);

        int score = -1;
        String basis = "";
        HttpStatus httpCode = hsResponseEntity.getStatusCode();
        if (httpCode.equals(HttpStatus.OK)) {
            HealthScoreResponse hsResponse = hsResponseEntity.getBody();
            score = hsResponse.getScr();
            if (hsResponse.getSubscores() != null) {
                basis = "Dacadoo Health Score API";
            } else {
                basis = "mocked backend computation";
            }
        }

        logger.info("API response code - " + httpCode + " score - " + score + " basis - " + basis);

        // Set quote based on score.  This is for demonstration purposes only and is not intended to reflect a realistic mapping of scores to quotes.
        int quote = 0;
        if (score > 800)
            quote = 30;
        else if (score > 600)
            quote = 50;
        else if (score > 400)
            quote = 70;
        else
            quote = 100;

        QuoteResponse response = new QuoteResponse();
        response.setQuotedAmount(quote);
        response.setBasis(basis);
        return response;         
    }

}
