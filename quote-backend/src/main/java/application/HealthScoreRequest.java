package application;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HealthScoreRequest {

    @JsonProperty
    private MetricHealthModel mhm;

    @JsonProperty
    private Smoking smk;

    @JsonProperty
    private boolean clip;

    public HealthScoreRequest() {
        mhm = new MetricHealthModel();
        smk = new Smoking();
    }

    @Getter @Setter
    public class MetricHealthModel {

        @JsonProperty
        private int age;

        @JsonProperty
        private int sex;

        @JsonProperty
        private double hgt; // cm

        @JsonProperty
        private double wgt; // kg

        @JsonProperty
        private int cnr;

        @JsonProperty
        private int cvd;

    }

    @Getter @Setter
    public class Smoking {

        @JsonProperty
        private int now;

        @JsonProperty
        private int evr;

    }

    @Override
    public String toString() {
        return 
          "age:"        + mhm.age   +
          " sex:"       + mhm.sex   +
          " height:"    + mhm.hgt   +
          " weight:"    + mhm.wgt   +
          " cnr:"       + mhm.cnr   +
          " cvd:"       + mhm.cvd   +
          " smk now:"   + smk.now   +
          " evr:"       + smk.evr;
    }

}