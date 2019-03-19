package learn.coleo.com.learnproject;

import android.app.ActivityOptions;
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
                if (ServerClass.hasConnection(context)) {
//                String usernameString = username.getText().toString().trim();
//                String passwordString = password.getText().toString().trim();
//                if (!usernameString.isEmpty()) {
//                    if (!passwordString.isEmpty()) {
//                        ServerClass.login(context, usernameString, passwordString);
//                    } else {
//                        Toast.makeText(context, "enter password", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(context, "enter username", Toast.LENGTH_SHORT).show();
//                }
                    ServerClass.login(context, "afshari9978", "12345678");
                }else{
                    Toast.makeText(context,"check connection",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void nextPage(String name){
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                username , getString(R.string.username_transition));
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(MainActivity.USERNAME_DATA,name);
        startActivity(intent , options.toBundle());
        finish();
    }


}
