package rest_assured;

import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MealPlanTest extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(MealPlanTest.class);
    @RepeatedTest(3)
    @DisplayName("Add Meal Plan Template")
    public void aTest() {
        Response response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", propGlobal.get("hash"))
                .body(getBody())
                .when()
                .post(getBaseUrl() + "mealplanner/" + propGlobal.get("username") + "/templates");
        assertThat(response.getStatusCode(), is(200));
        assertEquals(response.getBody().jsonPath().get("status").toString(), "success", "Add Plan - \"success\"");
    }
    @Test
    @DisplayName("Delete Meal Plan Template")
    public void bTest() {
        Response response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", propGlobal.get("hash"))
                .when()
                .get(getBaseUrl() + "mealplanner/" + propGlobal.get("username") + "/templates");
        assertThat(response.getStatusCode(), is(200));
        List<HashMap<String, Object>> responseJson = response.getBody().jsonPath().getList("templates");
        if (!responseJson.isEmpty()) {
            for (int i = 0; i < responseJson.size(); i++) {
                prop.put("idPlan" + i, responseJson.get(i).get("id").toString());
                assertEquals("My new meal plan template", responseJson.get(i).get("name").toString());
                deletePlane(i);
            }
        } else  logger.info("Delete Meal Plan: No Meal Plan Template");
    }

    private void deletePlane(int numberPlane) {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("hash", propGlobal.get("hash"))
                .delete(getBaseUrl() + "mealplanner/" + propGlobal.get("username") + "/templates/" + prop.get("idPlan" + numberPlane))
                .then()
                .statusCode(200);
    }

    private String getBody(){
        return "{\"name\": \"My new meal plan template\",\"items\": [{\"day\": 1,\"slot\": 1,\"position\": 0,\"type\": \"RECIPE\",\"value\": {\"id\": 296213,\"servings\": 2,\"title\":\"Spinach Salad with Roasted Vegetables and Spiced Chickpea\",\"imageType\": \"jpg\"}},{\"day\": 2,\"slot\": 1,\"position\": 0,\"type\": \"PRODUCT\",\"value\": {\"id\": 183433,\"servings\": 1,\"title\": \"Ahold Lasagna with Meat Sauce\",\"imageType\": \"jpg\"}},{\"day\": 3,\"slot\": 1,\"position\": 0,\"type\": \"MENU_ITEM\",\"value\": { \"id\": 378557, \"servings\": 1, \"title\": \"Pizza 73 BBQ Steak Pizza, 9\", \"imageType\": \"png\" } }, { \"day\": 4, \"slot\": 1, \"position\": 0, \"type\": \"CUSTOM_FOOD\", \"value\": { \"id\": 348, \"servings\": 1, \"title\": \"Aldi Spicy Cashews - 30g\", \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/cashews.jpg\" } }, { \"day\": 5, \"slot\": 1, \"position\": 0, \"type\": \"INGREDIENTS\", \"value\": { \"ingredients\": [ { \"name\": \"1 banana\" }, { \"name\": \"coffee\", \"unit\": \"cup\", \"amount\": \"1\", \"image\": \"https://spoonacular.com/cdn/ingredients_100x100/brewed-coffee.jpg\" } ] } } ], \"publishAsPublic\": false }";
    }
}