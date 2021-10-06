package cz.mendelu

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory

object DatabaseConnector {
    var sqlSessionFactory:SqlSessionFactory
    init{
        val JDBC_URL = "jdbc:postgresql://ziggy.db.elephantsql.com:5432/lzraqmlm"
        val JDBC_USER = "lzraqmlm"
        val JDBC_PASSWORD = "7Xv0a6xVEA3rdBj9_WPQ9EckQSLxqOWB"
        val JDBC_DRIVER = "org.postgresql.Driver"
        val ds = UnpooledDataSource(JDBC_DRIVER, JDBC_URL, JDBC_USER, JDBC_PASSWORD)
        val environment = Environment("dev", JdbcTransactionFactory(), ds)
        val config = Configuration(environment)
        config.addMapper(PersonMapper::class.java)
        sqlSessionFactory = SqlSessionFactoryBuilder().build(config)
    }
}