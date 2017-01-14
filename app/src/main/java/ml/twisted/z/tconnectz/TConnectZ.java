package ml.twisted.z.tconnectz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.rabbitmq.client.*;

import java.io.IOException;
import android.widget.*;

public class TConnectZ extends Activity {
	
	private EditText messageEditText;
	private Button publishButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		messageEditText = (EditText) findViewById(R.id.text);
		publishButton = (Button) findViewById(R.id.publish);
        setContentView(R.layout.activity_tconnect_z);
    }
    private final static String QUEUE_NAME = "MinelantisEx";

    public static void ReceiveMessage(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("37.187.149.175");
        factory.setUsername("USERNAME");
        factory.setPassword("PASSWORD");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");

            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

    private final static String USERNAME = "bla";
    private final static String PASSWORD = "blah";
}
