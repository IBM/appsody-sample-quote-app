package application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockHealthScore {

    @PostMapping("/mockscore")
    public ResponseEntity<HealthScoreResponse> score(@RequestBody HealthScoreRequest request) {
        HealthScoreResponse hsResponse = new HealthScoreResponse();
        hsResponse.setScr(1000);
        return new ResponseEntity<HealthScoreResponse>(hsResponse, HttpStatus.OK);
    }
}