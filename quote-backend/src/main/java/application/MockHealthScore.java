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
        HealthScoreRequest.MetricHealthModel mhm = request.getMhm();
        HealthScoreRequest.Smoking smk = request.getSmk();
        int score = 1000; // base score
        double bmi = mhm.getWgt() / Math.pow(mhm.getHgt() / 100, 2);
        System.out.println("BMI " + bmi);
        if (bmi >= 30)
            score = score - 200;
        if (smk.getNow() == 1 || smk.getEvr() == 1)
            score = score - 200;
        if (mhm.getCnr() == 1 || mhm.getCvd() == 1)
            score = score - 200;
        HealthScoreResponse hsResponse = new HealthScoreResponse();
        hsResponse.setScr(score);
        return new ResponseEntity<HealthScoreResponse>(hsResponse, HttpStatus.OK);
    }
}