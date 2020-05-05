package com.mandaditos.cliente;
import android.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;
import com.mandaditos.cliente.mLoginFolder.*;
import com.mandaditos.cliente.mUtilities.*;
import java.util.*;

import android.Manifest;
import android.support.v7.widget.Toolbar;
import android.util.*;
public class Home extends AppCompatActivity
{

	private mHomeModel enviarpedido;
	private RequestPermissionHandler mRequestPermissionHandler;

	private FirebaseAuth mFirebaseAuth;

	private DatabaseReference mDataBase;


	private TextView title;
	

	

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
		//toolbar
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		title =toolbar.findViewById(R.id.toolbarmainTitle);
		//TODO load data from firestore database 
		//aqui obtenemos el nombre del usuario
		mFirebaseAuth = FirebaseAuth.getInstance();
		mDataBase = FirebaseDatabase.getInstance().getReference();
		FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
		String userId = mFirebaseUser.getUid().toString();
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Usuarios/" + userId + "/Perfil").child("nombre");
		ref.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) {
					String t = dataSnapshot.getValue(String.class);
					setNombreUsuario(t);
					
				}

				@Override
				public void onCancelled(DatabaseError databaseError) {

				}
			});

			
			
		
		
		RecyclerView mRecyclerView = findViewById(R.id.recycler_services);
		GridLayoutManager mGridLayoutManager = new GridLayoutManager(Home.this, 2);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		layoutManager.scrollToPosition(0);
		mRecyclerView.setLayoutManager(mGridLayoutManager);
		mRequestPermissionHandler = new RequestPermissionHandler();
		mCheckPermission();
		
		
		
		
		List< mHomeModel > mServicesList = new ArrayList<>();
		
		
		
		
		
		//Servicios
        enviarpedido = new mHomeModel(getResources().getString(R.string.enviar_un_pedido), getResources().getString(R.string.enviar_un_pedido_descripcion),
									 R.drawable.box);
		mHomeModel dashboard = new mHomeModel("Ordenes en proceso","Aquíencontraras tus pedidos",R.drawable.box);
									 
									 
									 
									 
		//add services					 
        mServicesList.add(enviarpedido);
		mServicesList.add(dashboard);

		
		
		mHomeAdapter myAdapter = new mHomeAdapter(Home.this, mServicesList);
		mRecyclerView.setAdapter(myAdapter);
		
		}
		
	//Permissions
	private void mCheckPermission(){
		mRequestPermissionHandler.requestPermission(this, new String[] {
				Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
			}, 123, new RequestPermissionHandler.RequestPermissionListener() {
				@Override
				public void onSuccess() {
				}

				@Override
				public void onFailed() {
				}
			});

	}

	@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
										   @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mRequestPermissionHandler.onRequestPermissionsResult(requestCode, permissions,
															 grantResults);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	
	
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if(id == R.id.itemLogout){
		FirebaseAuth.getInstance().signOut();
		finishAffinity();
        startActivity(new Intent(Home.this,LoginActivity.class));
    }
    return super.onOptionsItemSelected(item);
}
	private final String SHARED_PREFS = "sharedPrefs";

	public void saveData(Context context, String key,String data) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, data);
		editor.commit();
	}

	public String loadData(Context context,String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
		String text = sharedPreferences.getString(key,"");
		return text;
	}
	public void erase(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.clear();
		editor.commit();
	}
	private void setNombreUsuario(String t)
	{
		
		String usuario = t;
		try{
		int index = usuario.indexOf(' ');
		usuario = usuario.substring(0, index);
		}catch(Exception e){Log.e("Couldent split name",e.toString());}
		
		
		title.setText("Hola "+usuario+", ¿En que podemos ayudarte?");
		
	}
	
		
}
