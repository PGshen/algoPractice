package world.playtogether.aop.service;

import world.playtogether.aop.annotation.MyTransactional;
import world.playtogether.aop.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <project> algoPractice
 *
 * <p>
 *
 * @author penggs
 * @since 2021-02-27
 */
@MyTransactional
public class UserServiceImpl implements UserService {
    ConnectionUtils connectionUtils = new ConnectionUtils();
    public void getUser() {
        Connection conn = connectionUtils.getThreadConnection();
        String sql = "select * from mytest";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println(rs.toString());
            rs.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("service执行...");
    }
}