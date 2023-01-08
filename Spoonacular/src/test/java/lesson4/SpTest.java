package lesson4;

import io.restassured.RestAssured;
import lesson4.AbstractTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class SpTest extends AbstractTest {

    @BeforeAll
    static void setUp(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    @Test
    void fiveGetReqs() {
        given().spec(getRequestSpecificationGet("maxReadyTime", "30"))
                .queryParam("maxReadyTime", "30")
                .queryParam("sort", "protein")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);

        given().spec(getRequestSpecificationGet("maxCarbs", "15"))
                .queryParam("maxCarbs", "15")
                .queryParam("maxFat", "8")
                .queryParam("minProtein", "30")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);

        given().spec(getRequestSpecificationGet("titleContains", "Chicken"))
                .queryParam("titleContains", "Chicken")
                .queryParam("sort", "calories")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);

        given().spec(getRequestSpecificationGet("maxReadyTime", "45"))
                .queryParam("maxReadyTime", "45")
                .queryParam("includeIngredients", "tomato")
                .queryParam("type", "soup")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);

        given().spec(getRequestSpecificationGet("diet", "Ketogenic"))
                .queryParam("diet", "Ketogenic")
                .queryParam("type", "dessert")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }

    @Test
    void fivePostReqs() {
        given().spec(getRequestSpecificationClassifyCuisine("en", "Burrito"))
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);

        given().spec(getRequestSpecificationClassifyCuisine("en", "pelmeni"))
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);

        given().spec(getRequestSpecificationClassifyCuisine("en", "shrimp 3ox"))
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);

        given().spec(getRequestSpecificationClassifyCuisine("en", "tom yam"))
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);

        given().spec(getRequestSpecificationClassifyCuisine("en", "Butter chicken"))
                .when()
                .post(getBaseUrl()+"recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }

    }
