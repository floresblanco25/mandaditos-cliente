package com.mandaditos.cliente.mLoginFolder;

import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v7.app.*;
import android.view.*;
import android.widget.*;
import com.google.android.gms.tasks.*;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;
import com.mandaditos.cliente.*;

import com.mandaditos.cliente.R;

public class SignupActivity extends AppCompatActivity
 {
    private static final String TAG = "SignupActivity";

	EditText nameEd;
	EditText emailEd;
	EditText passwordEd;
	Button signupButt;
	TextView loginLink;
	FirebaseAuth mFirebaseAuth;
	DatabaseReference mDataBase;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
		mFirebaseAuth = FirebaseAuth.getInstance();
		mDataBase = FirebaseDatabase.getInstance().getReference();

		
		nameEd = findViewById(R.id.input_name);
		emailEd = findViewById(R.id.input_email);
		passwordEd = findViewById(R.id.input_password);
		signupButt = findViewById(R.id.btn_signup);
		loginLink = findViewById(R.id.link_login);
		
        signupButt.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					final String email = emailEd.getText().toString();
					String pwd = passwordEd.getText().toString();
					if(email.isEmpty()){
						emailEd.setError("Please enter email id");
						emailEd.requestFocus();
					}
					else  if(pwd.isEmpty()){
						passwordEd.setError("Please enter your password");
						passwordEd.requestFocus();
					}
					else  if(email.isEmpty() && pwd.isEmpty()){
						Toast.makeText(SignupActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
					}
					else  if(!(email.isEmpty() && pwd.isEmpty())){
						mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
								@Override
								public void onComplete(@NonNull Task<AuthResult> task) {
									if(!task.isSuccessful()){
										Toast.makeText(SignupActivity.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
									}
									else {
										
													  
													  
										FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
										String email = mFirebaseUser.getEmail().toString();
										int index = email.indexOf('@');
										email = email.substring(0, index);
										String userId = email.toLowerCase();
										mUser user = new mUser();
										user.setNombre(nameEd.getText().toString());
										user.setMUserId(mFirebaseUser.getUid().toString());
										mDataBase.child("Usuarios/" + userId + "/Perfil").setValue(user);
										startActivity(new Intent(SignupActivity.this,Home.class));
									}
								}
							});
					}
					else{
						Toast.makeText(SignupActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

					}
				}
			});

        loginLink.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
    }

	}
	
