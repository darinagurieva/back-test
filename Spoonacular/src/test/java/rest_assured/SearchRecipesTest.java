package rest_assured;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.*;
//import org.junit.platform.commons.logging.Logger;
//import org.junit.platform.commons.logging.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SearchRecipesTest extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(SearchRecipesTest.class);
    @Test
    public void minCholesterolMaxVitaminB12minFolateTest(){
        prop.put("number", Integer.toString(random.nextInt(10) + 3));
        prop.put("offset", Integer.toString(random.nextInt(1000) + 1));
        prop.put("minCholesterol", "1");
        prop.put("maxVitaminB12", "2.0");
        prop.put("minFolate", "100");
        Response response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("number", prop.get("number"))
                .queryParam("offset", prop.get("offset"))
                .queryParam("minCholesterol", prop.get("minCholesterol"))
                .queryParam("maxVitaminB12", prop.get("maxVitaminB12"))
                .queryParam("minFolate", prop.get("minFolate"))
                .when()
                .get(getBaseUrl() + "recipes/complexSearch");
        assertThat(response.getStatusCode(), is(200));
        assertNotNull(response.getBody());
        int numberFromResponse = response.getBody().jsonPath().get("number");
        int numberFromProp = Integer.parseInt((String) prop.get("number"));
        assertEquals(numberFromResponse, numberFromProp, "Number check");
        List<HashMap<String, Object>> resultsJson = response.getBody().jsonPath().getList("results");
        int sizeResponse = resultsJson.size();
        if(sizeResponse > 0){
            logger.info("minCholesterolMaxVitaminB12minFolateTest: Всего найдено " + sizeResponse + " рецептов");
            assertEquals(numberFromResponse, sizeResponse, "Number eql leng");
            for (int i = 0; i < sizeResponse; i++) {
                HashMap<String, Object> nutritionJson = (HashMap<String, Object>) resultsJson.get(i).get("nutrition");
                List<HashMap<String, Object>> nutrientsList = (List<HashMap<String, Object>>) nutritionJson.get("nutrients");
                assertEquals(nutrientsList.get(0).get("name"), "Cholesterol", "Проверка название Cholesterol");
                Float amount = (Float) nutrientsList.get(1).get("amount");
                Float maxVitaminB12 = Float.parseFloat((String) prop.get("maxVitaminB12"));
                assertTrue(amount < maxVitaminB12, "Проверка содержания Витамина B12 (amount < maxVitaminB12)");
                assertEquals(nutrientsList.get(2).get("unit"), "µg", "Проверяем единицу измерения 'µg'");
            }
        }else logger.info("minCholesterolMaxVitaminB12minFolateTest: No test - рецептов не найдено");
    }
    @Test
    public void titleMatchTest(){
        prop.put("number", Integer.toString(random.nextInt(30) + 3));
        Response response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("number", prop.get("number"))
                .queryParam("titleMatch", propGlobal.get("titleMatch"))
                .when()
                .get(getBaseUrl() + "recipes/complexSearch");
        assertThat(response.getStatusCode(), is(200));
        assertNotNull(response.getBody());
        int numberFromResponse = response.getBody().jsonPath().get("number");
        int numberFromProp = Integer.parseInt((String) prop.get("number"));
        assertEquals(numberFromResponse, numberFromProp, "Number check");
        List<HashMap<String, String>> resultsJson = response.getBody().jsonPath().get("results");
        assertTrue(numberFromResponse >= resultsJson.size(), "Number >= length");
        for (HashMap<String, String> stringHashMap : resultsJson) {
            assertTrue(stringHashMap.get("title").toLowerCase().contains((CharSequence) propGlobal.get("titleMatch")));
        }
    }
    @Test
    public void dietTest(){
        prop.put("number", Integer.toString(random.nextInt(69) + 3));
        Response response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("number", prop.get("number"))
                .queryParam("diet", "Ketogenic")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch");
        assertThat(response.getStatusCode(), is(200));
        assertNotNull(response.getBody());
        int numberFromResponse = response.getBody().jsonPath().get("number");
        int numberFromProp = Integer.parseInt((String) prop.get("number"));
        assertEquals(numberFromResponse, numberFromProp, "Number check");
        List<HashMap<String, String>> resultsJson = response.getBody().jsonPath().get("results");
        int sizeResponse = resultsJson.size();
        if(sizeResponse > 0) {
            logger.info("dietTest: Всего найдено " + sizeResponse + " рецептов");
            assertEquals(numberFromResponse, sizeResponse, "Number eql leng");
        }else logger.info("dietTest: No test - рецептов не найдено");
    }
    @Test
    public void maxCaffeineNoSearchTest(){
        prop.put("number", Integer.toString(random.nextInt(20) + 3));
        prop.put("offset", Integer.toString(random.nextInt(3000) + 1));
        Response response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("number", prop.get("number"))
                .queryParam("maxCaffeine", "-2")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch");
        assertThat(response.getStatusCode(), is(200));
        assertNotNull(response.getBody());
        int numberFromResponse = response.getBody().jsonPath().get("number");
        int numberFromProp = Integer.parseInt((String) prop.get("number"));
        assertEquals(numberFromResponse, numberFromProp, "Number check");
        List<HashMap<String, String>> resultsJson = response.getBody().jsonPath().get("results");
        int sizeResponse = resultsJson.size();
        assertEquals(0, sizeResponse, "No Search Recipes");
        assertEquals(response.getBody().jsonPath().get("totalResults").toString(), "0", "No totalResults");
    }
    @Test
    public void addRecipeInformationTest(){
        prop.put("number", Integer.toString(random.nextInt(30) + 3));
        Response response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("number", prop.get("number"))
                .queryParam("titleMatch", propGlobal.get("titleMatch"))
                .queryParam("ignorePantry", "True")
                .queryParam("maxReadyTime", "40")
                .queryParam("addRecipeInformation", "True")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch");
        assertThat(response.getStatusCode(), is(200));
        assertNotNull(response.getBody());
        int numberFromResponse = response.getBody().jsonPath().get("number");
        int numberFromProp = Integer.parseInt((String) prop.get("number"));
        assertEquals(numberFromResponse, numberFromProp, "Number check");
        List<HashMap<String, String>> resultsJson = response.getBody().jsonPath().get("results");
        assertTrue(numberFromResponse >= resultsJson.size(), "Number >= length");
        for (HashMap<String, String> stringHashMap : resultsJson) {
            String title = stringHashMap.get("title");
            logger.info("addRecipeInformationTest - Title: " + title);
            assertTrue(title.toLowerCase().contains((CharSequence) propGlobal.get("titleMatch")));
        }
    }
}