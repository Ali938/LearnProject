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
import learn.coleo.com.learnproject.constants.Constants;
import learn.coleo.com.learnproject.server.ServerClass;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Context context = this;
        username = findViewById(R.id.username_editText_id);
        submit = findViewById(R.id.submit_button_id);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ServerClass.hasConnection(context)) {
                    submit.setEnabled(false);
                    String usernameString = username.getText().toString().trim();
                    if (!usernameString.isEmpty()) {
                        ServerClass.checkPhone(context, usernameString);
                    } else {
                        Toast.makeText(context, "enter phone", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context,"check connection",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void enable() {
        submit.setEnabled(true);
    }

    public void goCode(String phone) {
        Intent intent = new Intent(this, CodeActivity.class);
        intent.putExtra(MainActivity.USERNAME_DATA, phone);
        startActivityForResult(intent, Constants.SUBMIT_CODE);
    }

}
