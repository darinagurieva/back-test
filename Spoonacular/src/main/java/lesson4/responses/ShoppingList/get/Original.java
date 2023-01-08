package lesson4.responses.ShoppingList.get;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "amount",
        "unit"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Original {

    @JsonProperty("amount")
    public Double amount;
    @JsonProperty("unit")
    public String unit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}