package com.mandaditos.cliente;
import android.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import com.mandaditos.cliente.mUtilities.*;
import java.util.*;

import android.Manifest;
public class Home extends AppCompatActivity
{

	private mHomeModel enviarpedido;
	private RequestPermissionHandler mRequestPermissionHandler;

	

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
		
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
	
		
}
