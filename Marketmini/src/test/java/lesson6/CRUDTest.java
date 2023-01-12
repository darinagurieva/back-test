package lesson6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class CRUDTest extends AbstractTest{
    @ParameterizedTest
    @ValueSource(strings = { "1test1", "2test2", "3test3" })
    void myTest(String title){
        db.model.Categories categories = new db.model.Categories();
        categories.setTitle(title);
        getCategoriesMapper().insert(categories);
        getSession().commit();

        assertTitle().forEach(o -> assertTrue(o.getTitle().contains("test"), "Error Create"));

        String newTitle = updateTitle(getRandomInt());
        assertTitle().forEach(o -> assertEquals(o.getTitle(), newTitle, "Error Update"));

        deleteFromDB(newTitle);
        assertTitle().forEach(o -> assertFalse(o.getTitle().contains("test"), "Error Delete"));
    }

    private String updateTitle(int newRandomNameTitle) {
        db.model.CategoriesExample example = new db.model.CategoriesExample();
        example.createCriteria().andTitleLike("%test%");
        List<db.model.Categories> list = getCategoriesMapper().selectByExample(example);
        db.model.Categories categories = list.get(0);
        String randomTitle = newRandomNameTitle + "test" + newRandomNameTitle;
        categories.setTitle(randomTitle);
        getCategoriesMapper().updateByPrimaryKey(categories);
        getSession().commit();
        return randomTitle;
    }

    private List<db.model.Categories> assertTitle() {
        db.model.CategoriesExample example = new db.model.CategoriesExample();
        example.createCriteria().andTitleLike("%test%");
        List<db.model.Categories> list = getCategoriesMapper().selectByExample(example);
        list.forEach(o -> assertTrue(o.getTitle().contains("test")));
        return list;

    }

    private void deleteFromDB(String title) {
        db.model.CategoriesExample example = new db.model.CategoriesExample();
        example.createCriteria().andTitleEqualTo(title);
        getCategoriesMapper().deleteByExample(example);
        getSession().commit();
    }

    private int getRandomInt() {
        Random random = new Random();
        return random.nextInt(1000);
    }
}