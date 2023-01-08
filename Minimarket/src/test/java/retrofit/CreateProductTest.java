package retrofit;

import com.github.javafaker.Faker;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit.dto.Product;
import retrofit.utils.RetrofitUtils;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;


public class CreateProductTest extends AbstractTest {
    Product product = null;
    Faker faker = new Faker();
    int id;

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }

    @Test
    void createProductInFoodCategoryTest() throws IOException {
        Response<Product> response = productService.createProduct(product)
                .execute();
        RetrofitUtils.createFileResponse(response.body(), "src/main/resources/retrofitResponse/createProductResponse.json");

        assert response.body() != null;
        id =  response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

    }


    @AfterEach
    void tearDown() throws IOException {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}