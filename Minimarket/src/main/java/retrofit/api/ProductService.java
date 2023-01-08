package retrofit.api;

import jdk.jfr.ContentType;
import okhttp3.ResponseBody;
import retrofit.dto.Product;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ProductService {

    @POST("products")
    Call<Product> createProduct(@Body Product createProductRequest);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);
    @PUT("products")
    Call<Product> modifyProduct(@Body Product modifyProductRequest);

    @GET("products/{id}")
    Call<Product> getProductById(@Path("id") int id);

    @GET("products")
    Call<List<Product>> getProducts();

}