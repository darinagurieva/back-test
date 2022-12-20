import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpTest extends AbstractTest {

    @BeforeAll
    static void setUp(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    @Test
    void fiveGetReqs() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("maxReadyTime", "30")
                .queryParam("sort", "protein")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("maxCarbs", "15")
                .queryParam("maxFat", "8")
                .queryParam("minProtein", "30")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("titleContains", "Chicken")
                .queryParam("sort", "calories")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("maxReadyTime", "45")
                .queryParam("includeIngredients", "tomato")
                .queryParam("type", "soup")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("diet", "Ketogenic")
                .queryParam("type", "dessert")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    void fivePostReqs() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Burrito")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","pelmeni")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("ingredientList","shrimp 3oz")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","tom yam")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Butter chicken")
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .statusCode(200);
    }

    }
