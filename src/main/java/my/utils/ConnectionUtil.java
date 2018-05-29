package my.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工厂类
 */
public class ConnectionUtil {

    public static Connection getConnection(String host,Integer port,String username,String password,String virtualHost) throws IOException, TimeoutException {
        //connection工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        //factory.setVirtualHost("/zx");
        // 通过工厂获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

    public static Connection getConnection(String host,Integer port) throws IOException, TimeoutException {
        return getConnection(host,port,"guest","guest",null);
    }

    public static Connection getConnection(String host) throws IOException, TimeoutException {
        return getConnection(host,5672);
    }
}
