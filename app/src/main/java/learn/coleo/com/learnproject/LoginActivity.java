package learn.coleo.com.learnproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import learn.coleo.com.learnproject.server.ServerClass;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Context context = this;
        username = findViewById(R.id.username_editText_id);
        password = findViewById(R.id.password_editText_id);
        Button submit = findViewById(R.id.submit_button_id);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameString = username.getText().toString().trim();
                String passwordString = password.getText().toString().trim();
                if (!usernameString.isEmpty()) {
                    if (!passwordString.isEmpty()) {
                        ServerClass.login(context, usernameString, passwordString);
                    } else {
                        Toast.makeText(context, "enter password", Toast.LENGTH_SHORT).show();
                    }
                } else {
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
