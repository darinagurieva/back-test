package lesson4;

import lesson4.AbstractTest;
import lesson4.responses.ShoppingList.add.AddShoppingList;
import lesson4.responses.ShoppingList.get.ShoppingList;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.*;
//import org.junit.platform.commons.logging.Logger;
//import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import rest_assured.AbstractTest;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShoppingListTest extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(rest_assured.MealPlanTest.class);
    @Test
    @DisplayName("Get Shopping List and Delete")
    public void bTest() throws IOException {
        ShoppingList response = given()
                .spec(getRequestSpecification())
                .when()
                .get(getBaseUrl() + "mealplanner/" + propGlobal.get("username") + "/shopping-list")
                .then()
                .spec(getResponseSpecification())
                .extract()
                .response()
                .body()
                .as(ShoppingList.class);
        createFileResponse(response, "src/main/resources/ShoppingList.json");
        AtomicInteger sizeResponse = new AtomicInteger();
        if(!response.getAisles().isEmpty()) {
            sizeResponse.set(response.getAisles().get(0).getItems().size());
        }
        double cost = 0;
        if(sizeResponse.get() > 0){
            for (int i = 0; i < sizeResponse.get(); i++) {
                prop.put("idItems" + i, response.getAisles().get(0).getItems().get(i).getId().toString());
                cost += response.getAisles().get(0).getItems().get(i).getCost();
                assertFalse(response.getAisles().get(0).getItems().get(i).getPantryItem(), "pantryItem no false");
                deleteList(i);
            }
            logger.info("Delete all Shopping List: OK");
        }else  logger.info("Delete Shopping List: No Shopping List");
        assertEquals(cost, response.getCost(), "Проверка общей стоимости 'cost'");
    }

    @RepeatedTest(3)
    @DisplayName("Add to Shopping List")
    public void aTest() throws IOException {
        AddShoppingList response = given()
                .spec(getRequestMealPlanTest())
                .body(getBodyFromFile("src/main/resources/bodyAddShoppingList.json"))
                .when()
                .post(getBaseUrl() + "mealplanner/" + propGlobal.get("username") + "/shopping-list/items")
                .then()
                .spec(getResponseSpecification())
                .extract()
                .response()
                .body()
                .as(AddShoppingList.class);
        createFileResponse(response, "src/main/resources/addShoppingList.json");
        assertEquals(response.getName(), "baking powder");
        assertEquals(response.getMeasures().getMetric().getUnit(), "pkg");
        assertFalse(response.getPantryItem());
    }
    private void deleteList(int numberList) {
        given()
                .spec(getRequestMealPlanTest())
                .when()
                .delete(getBaseUrl() + "mealplanner/" + propGlobal.get("username") + "/shopping-list/items/" + prop.get("idItems" + numberList))
                .then()
                .spec(getResponseSpecification());
    }


}