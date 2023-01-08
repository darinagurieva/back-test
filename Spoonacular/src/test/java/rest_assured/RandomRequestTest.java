package rest_assured;

import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RandomRequestTest extends AbstractTest{
    @Test
    @DisplayName("korean")
    public void aTest(){
        prop.put("number", Integer.toString(random.nextInt(25) + 3));

        Response response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "Korean")
                .queryParam("number", prop.get("number"))
                .when()
                .get(getBaseUrl() + "recipes/complexSearch");

        List<HashMap<String, Object>> responseJson = response.getBody().jsonPath().getList("results");
        int randomFromList = random.nextInt(responseJson.size());
        prop.put("idRecipe", (responseJson.get(randomFromList).get("id")).toString());

        assertThat(response.getStatusCode(), is(200));
        assertNotNull(response.getBody());
        assertEquals(response.getBody().jsonPath().get("number").toString(), prop.get("number"), "Number check");
        assertEquals((Integer) response.getBody().jsonPath().get("number"), responseJson.size(), "Number eql leng");
    }
    @Test
    @DisplayName("get Recipe Information")
    public void bTest(){
        Response response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get(getBaseUrl() + "recipes/" + prop.get("idRecipe") + "/information");
        prop.put("title", response.getBody().jsonPath().get("title"));
        assertThat(response.getStatusCode(), is(200));
        assertTrue(response.getStatusLine().contains("OK"), "Status not OK");
        assertEquals(response.getBody().jsonPath().get("id").toString(), prop.get("idRecipe"), "Number check");
    }
    @Test
    @DisplayName("classify Cuisine")
    public void cTest(){
        Response response = given()
                .queryParam("apiKey", getApiKey())
                .formParam("title", prop.get("title"))
                .contentType("application/x-www-form-urlencoded")
                .post(getBaseUrl() + "recipes/cuisine");
        assertThat(response.getStatusCode(), is(200));
        assertTrue(response.getStatusLine().contains("OK"), "Status not OK");
        assertNotNull(response.getBody());
        assertTrue((Float)response.getBody().jsonPath().get("confidence") < 1.01 , "confidence > 1");
    }
}