package my.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消费者
 */
public class Recv {

    public static void recv(String queueName) throws Exception {

        // 获取连接
        Connection connection = ConnectionUtil.getConnection("192.168.1.2");
        // 创建通道
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(queueName, true, false, false, null);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列
        channel.basicConsume(queueName, true, consumer);  //true 自动确认消息, 下有详解

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery(); //阻塞或轮询
            String message = new String(delivery.getBody());
            System.out.println("获取:" + message);
        }
    }
}
