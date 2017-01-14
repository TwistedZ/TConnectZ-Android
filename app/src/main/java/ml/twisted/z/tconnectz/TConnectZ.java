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
    

    
}
