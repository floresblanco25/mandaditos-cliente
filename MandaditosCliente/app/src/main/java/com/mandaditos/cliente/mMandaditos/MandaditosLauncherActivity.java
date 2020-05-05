package com.mandaditos.cliente.mMandaditos;
import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.widget.*;
import com.google.android.gms.maps.model.*;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;
import com.mandaditos.cliente.*;

import com.mandaditos.cliente.R;
import com.mandaditos.cliente.mUtilities.*;

public class MandaditosLauncherActivity extends AppCompatActivity implements MandaditosAddressPick.Listener,
MandaditosMain.Listener,MandaditosCkeckout.Listener
{

	private String kmCostFormat;
	@Override
	public void sendDatosDeViaje(double km, CharSequence eta)
	{
		checkoutFr.setETAText(eta);
		double pricePerKm = 0.40;
		double conversionToDollars = km * pricePerKm;
		if (km <= 7){
			kmCostFormat = String.format("%.2f", 2.00 + 0.65);
		}
		if (km >= 7.1){
			kmCostFormat = String.format("%.2f", conversionToDollars + 0.99);
		}
		if (km >= 20){
			kmCostFormat = String.format("%.2f", conversionToDollars + 1.99);
		}
		String kmFormated = String.format("%.1f", km);


		checkoutFr.setTotalCost("$ " + kmCostFormat);
		checkoutFr.setTotalKm(kmFormated + " kilómetros");
		addresspicker.restartInstance();
	}
	

	DatabaseReference mDataBase;    
	private static Context mContext;
	MandaditosMain mandaditos;
	private MandaditosCkeckout checkoutFr;
    FrameLayout container;
	private MandaditosAddressPick addresspicker;
	MarkerOptions m1,m2;
	FirebaseAuth mFirebaseAuth;




	@Override
	public void onGatherAllData(
		String Usuario, String Partida, String Destino, String Distancia, String Fecha, String ETA, String RecogerDineroEn, String Costo, String EstadoDeOrden, LatLng LatLngA, LatLng LatLngB){
		//push data
		String mUserId = loadData(MandaditosLauncherActivity.this,"mUserId");
			FirebaseDatabase.getInstance().getReference(DbNames.Ordenes)
			.push()
			.setValue(new MandaditosDataModel(mUserId,Usuario, Partida, Destino, Distancia, Fecha, 
												ETA, RecogerDineroEn, Costo, EstadoDeOrden, LatLngA, LatLngB));
	}




	//partida?
	@Override
	public void setIsPartida(Boolean isPartidaaa)
	{
		addresspicker.setIsPartida(isPartidaaa);
	}




	//addresses
	@Override
	public void sentAddress(CharSequence address, Boolean isPartidaaa, MarkerOptions markerOpt, LatLng mLatLng)
	{
		if (isPartidaaa == true)
		{
			m1 = markerOpt;
			mandaditos.setPartidaAddress(address);
			addresspicker.setFieldText("");
			mandaditos.setMarker_partida(m1);
			addresspicker.clearMarkers();
			checkoutFr.setAddressA(address);
			checkoutFr.setLatLngA(mLatLng);
		}
		if (isPartidaaa == false)
		{
			m2 = markerOpt;
			mandaditos.setDestinoAddress(address);
			addresspicker.setFieldText("");
			mandaditos.setMarker_destino(m2);
			addresspicker.clearMarkers();
			mandaditos.setDistance();
			checkoutFr.setAddressB(address);
			checkoutFr.setLatLngB(mLatLng);

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

			try
			{
				transaction.add(R.id.launcherFrameLayout, mandaditos, MandaditosMain.tag);
				transaction.show(mandaditos);
				transaction.add(R.id.launcherFrameLayout, addresspicker, MandaditosAddressPick.tag);
				transaction.hide(addresspicker);
				transaction.add(R.id.launcherFrameLayout, checkoutFr, MandaditosCkeckout.tag);
				transaction.hide(checkoutFr);
				transaction.commit();	
			}

			catch (Exception e)
			{
				e.printStackTrace();	}
		}

	}



	//cntxt
	public static Context contxt()
	{
		return mContext;
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
		String text = sharedPreferences.getString(key, "");
		return text;
	}
	



}
