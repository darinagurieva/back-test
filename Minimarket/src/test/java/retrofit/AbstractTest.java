package retrofit;

import org.junit.jupiter.api.BeforeAll;
import retrofit.api.ProductService;
import retrofit.dto.Product;
import retrofit.utils.RetrofitUtils;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class AbstractTest {
    static ProductService productService;
    @BeforeAll
    static void beforeAll() throws IOException {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }
    int getId() throws IOException {
        Response<List<Product>> response = productService.getProducts()
                .execute();
        assert response.body() != null;
        Random random = new Random();
        return response.body().get(random.nextInt(response.body().size())).getId();
    }
}