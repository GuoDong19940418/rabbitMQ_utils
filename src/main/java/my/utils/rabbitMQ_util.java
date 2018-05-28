package my.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class rabbitMQ_util {

    /**
     * 消息生成
     */
    public static void producer(String queueName,String message,
                         String host,String username,
                         String password,Integer port){

        Connection connection = null;
        Channel channel = null;
        try {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost(host);
        factory.setUsername(username);
        factory.setPassword(password);
         factory.setPort(port);
        //创建一个新的连接
        connection = factory.newConnection();
        //创建一个通道
        channel = connection.createChannel();
        //  声明一个队列
        channel.queueDeclare(queueName, true, false, false, null);
        //发送消息到队列中
        channel.basicPublish("", queueName, null, message.getBytes("UTF-8"));
        }catch (Exception e){

        }finally {
            //关闭通道和连接
            try {
                channel.close();
            }catch (Exception e){

            }

            try {
                connection.close();
            }catch (Exception e){

            }
        }

    }
}
