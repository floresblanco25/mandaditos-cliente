package com.mandaditos.cliente;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import java.util.*;

public class Home extends AppCompatActivity
{

	private ServicesModel enviarpedido;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
		
		RecyclerView mRecyclerView = findViewById(R.id.recycler_services);
		GridLayoutManager mGridLayoutManager = new GridLayoutManager(Home.this, 2);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		layoutManager.scrollToPosition(0);
		mRecyclerView.setLayoutManager(mGridLayoutManager);
		
		
		
		
		
		List< ServicesModel > mServicesList = new ArrayList<>();
		
		
		
		
		
		//Servicios
        enviarpedido = new ServicesModel(getResources().getString(R.string.enviar_un_pedido), getResources().getString(R.string.enviar_un_pedido_descripcion),
									 R.drawable.box);
									 
									 
									 
									 
									 
        mServicesList.add(enviarpedido);
		
		
		
		ServicesAdapter myAdapter = new ServicesAdapter(Home.this, mServicesList);
		mRecyclerView.setAdapter(myAdapter);
		
		}
		
}
