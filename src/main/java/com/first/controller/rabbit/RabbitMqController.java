package com.first.controller.rabbit;

import com.rabbitmq.client.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

@Controller
@RequestMapping("/rabbit")
public class RabbitMqController {

    @RequestMapping("/product")
    @ResponseBody
    public void product() throws IOException, TimeoutException {
        customer();
        final String EXCHANGE_NAME = "logs";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");//fanout表示分发，所有的消费者得到同样的队列信息
        //分发消息
        for(int i = 0;i< 5;i++){
            String message = "Hello Word" + i;
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
            System.out.println("EmitLog Sent '" + message + "'");
        }
        channel.close();
        connection.close();

    }
    public void customer() throws IOException, TimeoutException {
        final String EXCHANGE_NAME = "logs";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //生产一个随机的队列名称
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName,EXCHANGE_NAME,"");//对队列进行绑定
        System.out.println("ReceiveLogs1 Waiting for messages");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                String message = new String(body,"UTF-8");
                System.out.println("ReceiveLogs1 Received '" + message + "'");

            }
        };
        channel.basicConsume(queueName,false,consumer);
    }
}
