package application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class QuoteRequest {

    @JsonProperty
    private int age;

    @JsonProperty
    private String gender;

    @JsonProperty
    private double height; // cm

    @JsonProperty
    private double weight; // kg

    @JsonProperty
    private String smoker;

    @JsonProperty
    private boolean cancer;

    @JsonProperty
    private boolean cvd;

    @Override
    public String toString() {
        return 
          "age:"     + age       +
          " gender:" + gender    +
          " height:" + height    +
          " weight:" + weight    +
          " smoker:" + smoker    +
          " cancer:" + cancer    +
          " cvd:"    + cvd;
    }

}