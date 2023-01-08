package lesson4.request;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "unit",
        "amount",
        "image"
})
@Data
public class Ingredient {

    @JsonProperty("name")
    public String name;
    @JsonProperty("unit")
    public String unit;
    @JsonProperty("amount")
    public String amount;
    @JsonProperty("image")
    public String image;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


}