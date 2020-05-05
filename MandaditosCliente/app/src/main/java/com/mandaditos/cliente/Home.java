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
import com.mandaditos.cliente.mLoginFolder.*;
import com.mandaditos.cliente.mUtilities.*;
import java.util.*;

import android.Manifest;
import android.support.v7.widget.Toolbar;
public class Home extends AppCompatActivity
{

	private mHomeModel enviarpedido;
	private RequestPermissionHandler mRequestPermissionHandler;
	

	

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
		//toolbar
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		TextView title =toolbar.findViewById(R.id.toolbarmainTitle);
		String Usuario =loadData(Home.this,"name");
		title.setText("Bienvenido "+Usuario+", ¿En que podemos ayudarte?");
		
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
	private static final String SHARED_PREFS = "sharedPrefs";

	public static void saveData(Context context, String key,String data) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, data);
		editor.apply();
	}

	public static String loadData(Context context,String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
		String text = sharedPreferences.getString(key,"");
		return text;
	}
		
}
