package learn.coleo.com.learnproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import learn.coleo.com.learnproject.R;
import learn.coleo.com.learnproject.server.ServerClass;

public class CodeActivity extends AppCompatActivity {

    private EditText password;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        Bundle extra = getIntent().getExtras();
        final String phone = extra.getString(MainActivity.USERNAME_DATA);

        final Context context = this;
        password = findViewById(R.id.password_editText_id);
        submit = findViewById(R.id.submit_button_id);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ServerClass.hasConnection(context)) {
                    submit.setEnabled(false);
                    String usernameString = password.getText().toString().trim();
                    if (!usernameString.isEmpty()) {
                        ServerClass.sendCode(context, phone, usernameString);
                    } else {
                        Toast.makeText(context, "enter phone", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "check connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void wrongCode() {
        submit.setEnabled(true);
        Toast.makeText(this, "wrong code", Toast.LENGTH_LONG).show();
    }

    public void goMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
