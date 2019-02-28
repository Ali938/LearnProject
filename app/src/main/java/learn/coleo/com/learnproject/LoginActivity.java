package learn.coleo.com.learnproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Context context = this;
        username = findViewById(R.id.username_editText_id);
        password = findViewById(R.id.password_editText_id);
        submit = findViewById(R.id.submit_button_id);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameString = username.getText().toString().trim();
                if (!usernameString.isEmpty()) {
                    Toast.makeText(context, "welcome " + usernameString, Toast.LENGTH_SHORT).show();
                    nextPage();
                }else{
                    Toast.makeText(context, "enter username", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void nextPage(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}
