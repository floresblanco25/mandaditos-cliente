package com.mandaditos.cliente;

import android.app.*;
import android.content.res.*;
import android.os.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import butterknife.*;
import com.mandaditos.cliente.*;

public class SignupActivity extends AppCompatActivity
 {
    private static final String TAG = "SignupActivity";

	EditText _nameText;
	EditText _emailText;
	EditText _passwordText;
	Button _signupButton;
	TextView _loginLink;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);

		
		_nameText = findViewById(R.id.input_name);
		_emailText = findViewById(R.id.input_email);
		_passwordText = findViewById(R.id.input_password);
		_signupButton = findViewById(R.id.btn_signup);
		_loginLink = findViewById(R.id.link_login);
		
        _signupButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					signup();
				}
			});

        _loginLink.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// Finish the registration screen and return to the Login activity
					finish();
				}
			});
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
																 R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Registrando...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
			new Runnable() {
				public void run() {
					// On complete call either onSignupSuccess or onSignupFailed 
					// depending on success
					onSignupSuccess();
					// onSignupFailed();
					progressDialog.dismiss();
				}
			}, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "No se pudo crear la cuenta", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("Escribe al menos 3 caracteres");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Ingresa una dirección de correo válida");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Escribe de 4 a 10 caracteres");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
	}
	
