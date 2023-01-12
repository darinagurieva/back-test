package lesson6;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GetTests extends AbstractTest {
    @Test
    void getCategoriesExample(){
        db.model.CategoriesExample example = new db.model.CategoriesExample();
        example.createCriteria().andIdEqualTo(1L);
        List<db.model.Categories> list = getCategoriesMapper().selectByExample(example);
        list.forEach(o -> assertEquals(o.getTitle(), "Food"));
    }
    @Test
    void getProducts(){
        db.model.ProductsExample example  = new db.model.ProductsExample();
        example.createCriteria().andIdNotEqualTo(5L);
        List<db.model.Products> list = getProductsMapper().selectByExample(example);
        list.forEach(o -> assertNotEquals(o.getId(), 5));
    }

}