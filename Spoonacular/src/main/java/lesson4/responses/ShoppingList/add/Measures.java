package lesson4.responses.ShoppingList.add;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "original",
        "metric",
        "us"
})
@Data
public class Measures {

    @JsonProperty("original")
    public Original original;
    @JsonProperty("metric")
    public Metric metric;
    @JsonProperty("us")
    public Us us;

}