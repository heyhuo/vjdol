package com.valcano.vjdol.dbutil;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 工具类中异常要抛出
 */
public class MybatisUtil {

    private static SqlSessionFactory factory = null;

    public static SqlSession getSqlSession() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        if (factory == null)
            factory = new SqlSessionFactoryBuilder().build(is);
        return factory.openSession();
    }
}
