package com.mandaditos.cliente.mLoginFolder;

import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.google.android.gms.tasks.*;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;
import com.mandaditos.cliente.*;

import com.mandaditos.cliente.R;

public class LoginActivity extends AppCompatActivity
{
	private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
	FirebaseAuth mFirebaseAuth;
	DatabaseReference mDataBase;
	
    private FirebaseAuth.AuthStateListener mAuthStateListener;
	
    EditText emailText;
	EditText passwordText;
	Button loginButton;
	TextView signupLink;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
		mFirebaseAuth = FirebaseAuth.getInstance();
		
		loginButton = findViewById(R.id.btn_login);
		signupLink = findViewById(R.id.link_signup);
		passwordText = findViewById(R.id.input_password);
		emailText = findViewById(R.id.input_email);

		
		
		
		mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null ){
					String email = mFirebaseUser.getEmail().toString();
					int index = email.indexOf('@');
					email = email.substring(0, index);
					Log.wtf("User is",email.toString());
                    Toast.makeText(LoginActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, Home.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };
		
		
        loginButton.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					
						String email = emailText.getText().toString();
						String pwd = passwordText.getText().toString();
						if(email.isEmpty()){
							emailText.setError("Please enter email id");
							emailText.requestFocus();
						}
						else  if(pwd.isEmpty()){
							passwordText.setError("Please enter your password");
							passwordText.requestFocus();
						}
						else  if(email.isEmpty() && pwd.isEmpty()){
							Toast.makeText(LoginActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
						}
						else  if(!(email.isEmpty() && pwd.isEmpty())){
							mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
									@Override
									public void onComplete(@NonNull Task<AuthResult> task) {
										if(!task.isSuccessful()){
											Toast.makeText(LoginActivity.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
										}
										else{
											//aqui obtenemos el nombre del usuario
											mFirebaseAuth = FirebaseAuth.getInstance();
											mDataBase = FirebaseDatabase.getInstance().getReference();
											FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
											String userId = mFirebaseUser.getUid().toString();
											DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Usuarios/" + userId + "/Perfil").child("nombre");
											ref.addListenerForSingleValueEvent(new ValueEventListener() {
													@Override
													public void onDataChange(DataSnapshot dataSnapshot) {
														String Usuario = dataSnapshot.getValue(String.class);
														saveData(LoginActivity.this,"name",Usuario);
													}

													@Override
													public void onCancelled(DatabaseError databaseError) {

													}
												});
											mUser user = new mUser();
											user.setmUserId(mFirebaseUser.getUid().toString());
											saveData(LoginActivity.this,"mUserId",user.getmUserId());
											Intent intToHome = new Intent(LoginActivity.this,Home.class);
											startActivity(intToHome);
										}
									}
								});
						}
						else{
							Toast.makeText(LoginActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

						}

					}
			});

        signupLink.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
					startActivityForResult(intent, REQUEST_SIGNUP);
				}
			});
    }


    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

	@Override
	protected void onStart()
	{
		super.onStart();
		mFirebaseAuth.addAuthStateListener(mAuthStateListener);
	}

	
	private static final String SHARED_PREFS = "sharedPrefs";

	public void saveData(Context context, String key,String data) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, data);
		editor.commit();
	}

	public String loadData(Context context,String key,String data) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
		String text = sharedPreferences.getString(key, data);
		return text;
	}
	
	
	
}
