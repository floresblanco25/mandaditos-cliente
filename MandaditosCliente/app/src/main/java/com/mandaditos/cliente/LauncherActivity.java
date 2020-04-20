package com.mandaditos.cliente;
import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.widget.*;
import com.google.android.gms.maps.model.*;

public class LauncherActivity extends AppCompatActivity implements addressPickerFr.addressPickerListener,
mandaditosMainFr.mandaditosMainFrListener
{

	@Override
	public void setIsPartida(Boolean isPartidaaa)
	{
		addresspicker.setIsPartida(isPartidaaa);
	}


	@Override
	public void sentAddress(CharSequence input,Boolean isPartidaaa, MarkerOptions markerOpt)
	{
		if(isPartidaaa==true){
			m1=markerOpt;
		mandaditos.setPartidaAddress(input);
		addresspicker.setFieldText("");
		mandaditos.setMarker_partida(m1);
		addresspicker.clearMarkers();
		}
		if(isPartidaaa==false){
			m2=markerOpt;
			mandaditos.setDestinoAddress(input);
			addresspicker.setFieldText("");
			mandaditos.setMarker_destino(m2);
			addresspicker.clearMarkers();
			mandaditos.setDistance();
		}
	}
	

	
	
	private static Context mContext;
	mandaditosMainFr mandaditos;
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
