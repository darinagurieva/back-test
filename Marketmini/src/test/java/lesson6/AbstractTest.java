package lesson6;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;

public class AbstractTest {
    private static SqlSession session = null;

    @BeforeAll
    static void initTest() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
    }
    @AfterAll
    static void afterTest(){
        session.close();
    }

    public static db.dao.CategoriesMapper getCategoriesMapper(){
        return session.getMapper(db.dao.CategoriesMapper.class);
    }

    public static db.dao.ProductsMapper getProductsMapper(){
        return session.getMapper(db.dao.ProductsMapper.class);
    }


    public SqlSession getSession() {
        return session;
    }
}