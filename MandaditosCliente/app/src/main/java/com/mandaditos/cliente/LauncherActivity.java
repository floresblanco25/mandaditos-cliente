package com.mandaditos.cliente;
import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.widget.*;
import com.google.android.gms.maps.model.*;
import java.text.*;
import java.util.*;

public class LauncherActivity extends AppCompatActivity implements addressPickerFr.addressPickerListener,
mandaditosMainFr.mandaditosMainFrListener,checkout.checkoutListener
{

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


	

	@Override
	public void setIsPartida(Boolean isPartidaaa)
	{
		addresspicker.setIsPartida(isPartidaaa);
	}


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
	

	
	
	private static Context mContext;
	mandaditosMainFr mandaditos;
	private checkout checkoutFr;
    FrameLayout container;
	private addressPickerFr addresspicker;
	MarkerOptions m1,m2;
	

	

	
	
	
	//onCreate
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher);
		mandaditos = mandaditosMainFr.newInstance();
		addresspicker = addressPickerFr.newInstance();
		checkoutFr = checkout.newInstance();
		
		
		
		
		
		
		
		
		
		
		
		
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
				transaction.add(R.id.launcherFrameLayout, mandaditos, mandaditosMainFr.tag);
				transaction.show(mandaditos);
				transaction.add(R.id.launcherFrameLayout,addresspicker, addressPickerFr.tag);
				transaction.hide(addresspicker);
				transaction.add(R.id.launcherFrameLayout,checkoutFr, checkout.tag);
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
	
}
