package my.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 生产者
 */
public class Send {

    public static void send(String queueName,String message) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection(queueName);
        // 创建通道
        Channel channel = connection.createChannel();

        // 声明队列 (不存在则创建)
        channel.queueDeclare(queueName, true, false, false, null);

        // 发送消息
        //String message = "Hello World2";
        channel.basicPublish("", queueName, null, message.getBytes());

        // 关闭通道和连接
        channel.close();
        connection.close();
    }
}
