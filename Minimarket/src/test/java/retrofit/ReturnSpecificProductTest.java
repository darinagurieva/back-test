package retrofit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit.api.ProductService;
import retrofit.dto.Product;
import retrofit.utils.RetrofitUtils;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturnSpecificProductTest extends AbstractTest {
    @BeforeAll
    static void beforeAll() throws IOException {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @Test
    void  getProductByIdTest() throws IOException {
        int id = getId();
        Response<Product> response = productService.getProductById(id)
                .execute();
        assert  response.body() != null;
        RetrofitUtils.createFileResponse(response.body(), "src/main/resources/retrofitResponse/getProductById.json");
        assertEquals(response.body().getId(), id);

    }
}