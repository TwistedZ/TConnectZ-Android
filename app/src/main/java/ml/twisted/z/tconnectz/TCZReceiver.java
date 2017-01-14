package ml.twisted.z.tconnectz;

import com.rabbitmq.client.*;
import java.io.*;
import java.util.concurrent.*;

public class TCZReceiver
{
	private static final String USERNAME = "bla";
    private static final String PASSWORD = "blah";
	private static final String QUEUE = "Minelantis";
	private static final String EXCHANGE = "MinelantisEx";
	private static Connection connection = null;
	private static TCZReceiver instance = null;
	
	private TCZReceiver() {
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("37.187.149.175");
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        try
		{
			connection = factory.newConnection();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (TimeoutException e)
		{
			e.printStackTrace();
		}
	}
	
	public TCZReceiver getInstance() {
		instance = (instance == null) ? new TCZReceiver() : instance;
		return instance;
	}
	
	public static void ReceiveMessage(String[] argv) throws Exception {
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
			throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");

            }
        };
        channel.basicConsume(QUEUE, true, consumer);
    }
}
