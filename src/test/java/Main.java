import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @ClassName Main
 * @Description TODO
 * @Author zhouliansheng
 * @Date 2021/3/19 14:27
 * @Version 1.0
 **/
public class Main {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://127.0.0.1:3306/ssmdemo";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, user, password);
            // 获取statement，preparedStatement
            String sql = "select * from tb_user where id=?";
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数
            preparedStatement.setLong(1, 1l);
            // 执行查询
            resultSet = preparedStatement.executeQuery();
            // 处理结果集
            while (resultSet.next()) {
                System.out.println(resultSet.getString("user_name"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getInt("age"));
                System.out.println(resultSet.getDate("birthday"));
            }
        } finally {
            // 关闭连接，释放资源
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
