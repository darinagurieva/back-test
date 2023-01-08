package lesson4.responses.ShoppingList.get;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "original",
        "metric",
        "us"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Measures {

    @JsonProperty("original")
    public Original original;
    @JsonProperty("metric")
    public Metric metric;
    @JsonProperty("us")
    public Us us;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}