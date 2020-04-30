package com.mandaditos.cliente.Mandaditos;
import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.widget.*;
import com.google.android.gms.maps.model.*;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;
import com.mandaditos.cliente.*;
import java.util.*;

import com.mandaditos.cliente.R;
import android.util.*;

public class MandaditosActivity extends AppCompatActivity implements MandaditosAddressPick.Listener,
MandaditosMain.Listener,MandaditosCkeckout.Listener
{

	DatabaseReference mDataBase;    
	private static Context mContext;
	MandaditosMain mandaditos;
	private MandaditosCkeckout checkoutFr;
    FrameLayout container;
	private MandaditosAddressPick addresspicker;
	MarkerOptions m1,m2;
	FirebaseAuth mFirebaseAuth;
	
	
	
	
	//send to realtimedb
	@Override
	public void onGatherAllData(String addressA, String addressB, String date, String eta, String totalMoney, String totalDist, String whereGetMoney)
	{
		FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
		String email = mFirebaseUser.getEmail().toString();
		int index = email.indexOf('@');
		email = email.substring(0, index);
		Map<String, Object> orderMap = new HashMap<>();
		orderMap.put("Partida", addressA);
		orderMap.put("Destino",addressB);
		orderMap.put("Fecha", date);
		orderMap.put("ETA",eta);
		orderMap.put("Costo", totalMoney);
		orderMap.put("Distancia",totalDist);
		orderMap.put("Partida", addressA);
		orderMap.put("Recoger dinero en",whereGetMoney);
		orderMap.put("Marcador de partida", m1);
		orderMap.put("Marcador de destino",m2);
		mDataBase.child("Usuarios/"+email+"/Ordenes/Mandaditos").push().setValue(orderMap);
		Log.wtf("User is",email);
		
		
		
	}
	


	
	
	
	
	
	//set eta
	@Override
	public void sentETA(CharSequence eta)
	{
		checkoutFr.setETAText(eta);
	}
	


	
	
	
	
	
	
	
	
	//km and cost
	private String kmCostFormat;
	@Override
	public void onDistanceKm(double km)
	{
		double pricePerKm = 0.40;
		double conversionToDollars = km*pricePerKm;
		if(km<=7){
			kmCostFormat = String.format("%.2f", 2.00+0.65);
		}
		if(km>=7.1){
			kmCostFormat = String.format("%.2f", conversionToDollars+0.99);
		}
		if(km>=20){
			kmCostFormat = String.format("%.2f", conversionToDollars+1.99);
		}
		
		
		String kmFormated = String.format("%.1f",km);
		checkoutFr.setTotalCost("$ " + kmCostFormat);
		
		checkoutFr.setTotalKm(kmFormated + " kilómetros");

	}


	
	
	
	
	
	
	
	
	
	

	//partida?
	@Override
	public void setIsPartida(Boolean isPartidaaa)
	{
		addresspicker.setIsPartida(isPartidaaa);
	}


	
	
	
	
	
	
	
	
	
	
	//addresses
	@Override
	public void sentAddress(CharSequence address,Boolean isPartidaaa, MarkerOptions markerOpt)
	{
		if(isPartidaaa==true){
			m1=markerOpt;
		mandaditos.setPartidaAddress(address);
		addresspicker.setFieldText("");
		mandaditos.setMarker_partida(m1);
		addresspicker.clearMarkers();
		checkoutFr.setAddressA(address);
		}
		if(isPartidaaa==false){
			m2=markerOpt;
			mandaditos.setDestinoAddress(address);
			addresspicker.setFieldText("");
			mandaditos.setMarker_destino(m2);
			addresspicker.clearMarkers();
			mandaditos.setDistance();
			checkoutFr.setAddressB(address);

		}
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	
	
	
	//onCreate
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher);
		mandaditos = MandaditosMain.newInstance();
		addresspicker = MandaditosAddressPick.newInstance();
		checkoutFr = MandaditosCkeckout.newInstance();
		mDataBase = FirebaseDatabase.getInstance().getReference();  
		mFirebaseAuth = FirebaseAuth.getInstance();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Fragments
		if (savedInstanceState != null)
		{
			getFragmentManager().getFragment(savedInstanceState, "current");
		}
		else
		{
			FragmentManager manager = getFragmentManager();
			final FragmentTransaction transaction= manager.beginTransaction();
			
			try{
				transaction.add(R.id.launcherFrameLayout, mandaditos, MandaditosMain.tag);
				transaction.show(mandaditos);
				transaction.add(R.id.launcherFrameLayout,addresspicker, MandaditosAddressPick.tag);
				transaction.hide(addresspicker);
				transaction.add(R.id.launcherFrameLayout,checkoutFr, MandaditosCkeckout.tag);
				transaction.hide(checkoutFr);
				transaction.commit();	
				}
			
				catch (Exception e){
				e.printStackTrace();	}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//cntxt
	public static Context contxt(){
		return mContext;
	}
	
	
	
	
	
	//how to retrieve data from db
//	mDataBase.child("Personas").addValueEventListener(new ValueEventListener(){
//
//	@Override
//	public void onDataChange(DataSnapshot p1)
//	{
//		if(p1.exists()){
//			String textRetrieved = p1.child("nombre").getValue().toString();
//			checkoutFr.setAddressA(textRetrieved);
	
	//or String textRetrieved = p1.child("Marcador de destino/position/latitude").getValue().toString();
//		}
//		else{
//			checkoutFr.setAddressA(("error"));
//		}
//		// TODO: Implement this method
//	}
//
//	@Override
//	public void onCancelled(DatabaseError p1)
//	{
//		// TODO: Implement this method
//	}
//	});
	
	
}
