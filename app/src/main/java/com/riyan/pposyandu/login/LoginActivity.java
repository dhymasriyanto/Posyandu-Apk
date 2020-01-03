package com.riyan.pposyandu.login;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.riyan.pposyandu.MainActivity;
import com.riyan.pposyandu.R;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.edtusername);
        password = (EditText) findViewById(R.id.editTextPassword);
        btnlogin =(Button) findViewById(R.id.btnLogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameKey = username.getText().toString();
                String passwordKey = password.getText().toString();

                if (usernameKey.equals("admin") && passwordKey.equals("123456789")){
                    Toast.makeText(getApplicationContext(), " Login Sukses",
                            Toast.LENGTH_SHORT);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(intent);
                    finish();
                }else {
                    // jika login gagal
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Username atau password salah")
                            .setNegativeButton("Retry", null).create().show();
                }

            }
        });
    }
}
