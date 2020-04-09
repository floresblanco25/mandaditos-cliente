package com.mandaditos.cliente;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import butterknife.*;
import com.mandaditos.cliente.*;

public class LoginActivity extends AppCompatActivity
{
	private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    EditText _emailText;
	EditText _passwordText;
	Button _loginButton;
	TextView _signupLink;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
		_loginButton = findViewById(R.id.btn_login);
		_signupLink = findViewById(R.id.link_signup);
		_passwordText = findViewById(R.id.input_password);
		_emailText = findViewById(R.id.input_email);
        ButterKnife.inject(this);

		Intent intent = new Intent(getApplicationContext(), Home.class);
		startActivityForResult(intent, RESULT_OK);
        _loginButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					login();
				}
			});

        _signupLink.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// Start the Signup activity
					Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
					startActivityForResult(intent, REQUEST_SIGNUP);
				}
			});
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
																 R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
			new Runnable() {
				public void run() {
					// On complete call either onLoginSuccess or onLoginFailed
					onLoginSuccess();
					// onLoginFailed();
					progressDialog.dismiss();
				}
			}, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
				
				
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
		Intent intent = new Intent(getApplicationContext(), Home.class);
		startActivityForResult(intent, RESULT_OK);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "No se puede iniciar sesión", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Ingrese un correo válido");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Ingrese una contraseña entre 4 a 10 caracteres");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
