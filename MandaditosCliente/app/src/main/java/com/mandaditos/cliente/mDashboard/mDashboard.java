package com.mandaditos.cliente.mDashboard;
import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.widget.*;
import com.google.firebase.database.*;
import com.mandaditos.cliente.*;
import com.mandaditos.cliente.models.*;
import java.util.*;

import com.mandaditos.cliente.R;

public class mDashboard extends AppCompatActivity
{
	DatabaseReference mDataBase;
	TextView NumeroDeOrdenTv, PartidaTv,Destino,DistanciaTv,FechaEtaTv, 
	DondeRecogerDineroTv,CostoTv,EstadoDeOrdenTv;
	private Context context;
	private ProgressDialog pDialog;
	private String uId;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		uId= loadData(mDashboard.this,"mUserId");
		
		
		
		mDataBase = FirebaseDatabase.getInstance().getReference("Ordenes");
		
		//dialog 
		pDialog = new ProgressDialog(mDashboard.this);
		pDialog.setMessage("Cargando datos de los servidores..");
		pDialog.show();
		
		mDataBase.addValueEventListener(new ValueEventListener(){

				
	@Override
	public void onDataChange(DataSnapshot p1)
	{
		pDialog.dismiss();
		if(p1.exists()){
			
			List<MandaditosDataModel> ordersList = new ArrayList<MandaditosDataModel>();
            for (DataSnapshot postSnapshot : p1.getChildren()) {
				MandaditosDataModel m = new MandaditosDataModel();
				m.setMUserId(postSnapshot.child("muserId").getValue().toString());
				m.setUsuario(postSnapshot.child("usuario").getValue().toString());
				m.setPartida(postSnapshot.child("partida").getValue().toString());
				m.setDestino(postSnapshot.child("destino").getValue().toString());
				m.setDistancia(postSnapshot.child("distancia").getValue().toString());
				m.setFecha(postSnapshot.child("fecha").getValue().toString());
				m.setETA(postSnapshot.child("eta").getValue().toString());
				m.setRecogerDineroEn(postSnapshot.child("recogerDineroEn").getValue().toString());
				m.setCosto(postSnapshot.child("costo").getValue().toString());
				m.setEstadoDeOrden(postSnapshot.child("estadoDeOrden").getValue().toString());
				m.setNumeroDeOrden(postSnapshot.getKey().toString());
				if(m.getMUserId().toString().matches(uId)){
					ordersList.add(m);
            }
			}
		
			
			mAdapter adapter = new mAdapter(mDashboard.this,ordersList);
			RecyclerView mRecyclerView = findViewById(R.id.dashboardRecycler);
			mRecyclerView.setHasFixedSize(true);
			LinearLayoutManager layoutManager = new LinearLayoutManager(mDashboard.this);
			mRecyclerView.setLayoutManager(layoutManager);
			mRecyclerView.setAdapter(adapter);
		}
		else{
		}
	}
	
				

	@Override
	public void onCancelled(DatabaseError p1)
	{
	}
	});
	
	
	
	
	
	
		}
		
	private  String SHARED_PREFS = "sharedPrefs";

	public  void saveData(Context context, String key,String data) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, data);
		editor.commit();
	}

	public  String loadData(Context context,String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
		String text = sharedPreferences.getString(key, "");
		return text;
	}
	
	@Override
	public void onBackPressed() {
		finishAffinity();
        startActivity(new Intent(mDashboard.this,Home.class));
    } 

}
