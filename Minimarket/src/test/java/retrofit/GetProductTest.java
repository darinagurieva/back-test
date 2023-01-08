package retrofit;

import org.junit.jupiter.api.Test;
import retrofit.dto.Product;
import retrofit.utils.RetrofitUtils;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;

public class GetProductTest extends AbstractTest {


    @Test
    void getProductTest() throws IOException {
        Response<List<Product>> response = productService.getProducts()
                .execute();
        RetrofitUtils.createFileResponse(response.body(), "src/main/resources/retrofitResponse/getProductResponse.json");
        assert response.body() != null;
        assert !response.body().isEmpty();
        response.body().forEach(product -> assertThat(product.getCategoryTitle(), anyOf(containsString("o"))));
    }
}