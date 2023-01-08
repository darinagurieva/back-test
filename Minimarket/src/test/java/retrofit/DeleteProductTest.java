package retrofit;

import com.github.javafaker.Faker;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import retrofit.dto.Product;
import retrofit.utils.RetrofitUtils;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteProductTest extends AbstractTest {
    Faker faker = new Faker();

    @Test
    void  deleteProductTest() throws IOException {
        int id = getIntID();
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
    int getIntID() throws IOException {
        Product product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
        Response<Product> response = productService.createProduct(product)
                .execute();
        RetrofitUtils.createFileResponse(response.body(), "src/main/resources/retrofitResponse/createProductResponse.json");
        assert response.body() != null;
        return response.body().getId();
    }
}