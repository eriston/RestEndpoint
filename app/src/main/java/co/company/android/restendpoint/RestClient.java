package co.company.android.restendpoint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RestClient extends Activity {
    Button button;
    TextView textarea;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textarea = (TextView)findViewById(R.id.my_textarea);
        button = (Button)findViewById(R.id.my_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryEndpoint();
            }
        });
    }

    private void queryEndpoint() {
        new QueryTask(this.getApplicationContext(), findViewById(android.R.id.content))
                .execute("https://jsonplaceholder.typicode.com/comments");
    }
}