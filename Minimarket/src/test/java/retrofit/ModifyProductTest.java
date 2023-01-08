package retrofit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit.dto.Product;
import retrofit.utils.RetrofitUtils;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModifyProductTest extends AbstractTest {
    Product product = null;

    @BeforeEach
    void setUp() throws IOException {

        product = new Product()
                .withId(getId())
                .withTitle("ModifyProduct")
                .withCategoryTitle("Electronic")
                .withPrice(1);             ;

    }

    @Test
    void  ModifyProductTest() throws IOException {
        Response<Product> response = productService.modifyProduct(product)
                .execute();
        assert  response.code() == 200;
        assert response.body() != null;
        RetrofitUtils.createFileResponse(response.body(), "src/main/resources/retrofitResponse/ModifyProductResponse.json");
        assertEquals(response.body().getTitle(), "ModifyProduct");
    }
}