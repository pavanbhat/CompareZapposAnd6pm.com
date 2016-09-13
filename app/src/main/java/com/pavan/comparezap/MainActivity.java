package com.pavan.comparezap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button submit;
    EditText searchText;
    static TextView displayZapposResults;
    static TextView display6pmResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = (EditText)findViewById(R.id.search_text);
        submit = (Button)findViewById(R.id.submit);
        displayZapposResults = (TextView)findViewById(R.id.zapposView) ;
        display6pmResults = (TextView)findViewById(R.id.pmView) ;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Hi",Toast.LENGTH_LONG).show();
                String searchString = searchText.getText().toString();
                displayZapposResults.setText(searchString.toLowerCase());
                DownloadTasks downloadTasks = new DownloadTasks();
                downloadTasks.execute("https://api.zappos.com/Search?term="+searchString+"&key=b743e26728e16b81da139182bb2094357c31d331", "https://api.6pm.com/Search?term="+searchString+"&key=524f01b7e2906210f7bb61dcbe1bfea26eb722eb");
            }
        });



    }
}
