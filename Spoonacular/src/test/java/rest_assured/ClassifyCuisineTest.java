package rest_assured;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ClassifyCuisineTest extends AbstractTest {
    @Test
    public void noAuthNegativeTest() {
        Response response = getResponseNoAuth();
        assertThat(response.getStatusCode(), is(401));
        assertThat(response.getStatusLine(), containsString("Unauthorized"));
        assertTimeout(Duration.ofMillis(500), () -> {
            getResponseNoAuth();
            return "result";
        });
    }

    @Test
    public void italianTunaPastaTest() {
        Response response = getResponseTitle("Italian Tuna Pasta", "en");
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getStatusLine(), containsString("OK"));
        assertFalse(response.getBody().jsonPath().getList("cuisines").isEmpty(), "cuisines is empty");
        assertTrue((Float) response.getBody().jsonPath().get("confidence") < 1.01, "confidence > 1");
        assertTrue(response.getBody().jsonPath().getList("cuisines").contains("Mediterranean"), "cuisines not contains Mediterranean");
        assertTrue(response.getBody().jsonPath().getList("cuisines").contains("European"), "cuisines not contains European");
    }

    @Test
    public void gyrosTest() {
        Response response = getResponseTitle("Gyros", "en");
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getStatusLine(), containsString("OK"));
        assertFalse(response.getBody().jsonPath().getList("cuisines").isEmpty(), "cuisines is empty");
        assertTrue((Float) response.getBody().jsonPath().get("confidence") < 1.01, "confidence > 1");
        assertTrue(response.getBody().jsonPath().getList("cuisines").contains("Greek"), "cuisines not contains Greek");
    }

    @Test
    public void negativeTest() {
        Response response = getResponseTitle("BLT Pizza", "ru");
        assertThat(response.getStatusCode(), is(500));
        assertThat(response.getStatusLine(), containsString("Internal Server Error"));
        assertTimeout(Duration.ofMillis(500), () -> {
            getResponseTitle("BLT Pizza", "ru");
            return "result";
        });
    }
    @Test
    public void dinnerTonightGrilledRomescoStylePorkTest(){
        Response response = getResponseTitle("Dinner Tonight: Grilled Romesco-Style Pork", "en");
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getStatusLine(), containsString("OK"));
        assertFalse(response.getBody().jsonPath().getList("cuisines").isEmpty(), "cuisines is empty");
        assertTrue((Float) response.getBody().jsonPath().get("confidence") < 1.01, "confidence > 1");
        assertTrue(response.getBody().jsonPath().getList("cuisines").contains("Spanish"), "cuisines not contains Spanish");
    }

    private Response getResponseTitle(String title, String language) {
        return given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", language)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", title)
                .when()
                .post(getBaseUrl() + "recipes/cuisine");
    }

    private Response getResponseNoAuth() {
        return given()
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "BLT Pizza")
                .when()
                .post(getBaseUrl() + "recipes/cuisine");
    }
}