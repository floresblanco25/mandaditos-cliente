package com.mandaditos.cliente;
import android.app.*;
import android.location.*;
import android.os.*;
import android.view.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.mandaditos.cliente.*;
import java.io.*;
import java.util.*;
import com.mandaditos.cliente.R;
import android.widget.*;
import android.view.View.*;






public class mandaditosMainFr extends Fragment implements OnMapReadyCallback
{
	private MapView mapView;
    private GoogleMap gmap;
	private EditText edPartida,edDestino;
	private String address_A;
	private LatLng mLatLng_A,mLatLng_B;
	private Fragment fragmentToOpen;
	private String where=null;
	
	
	
	
	
	
	
	
	//map ready
	@Override
	public void onMapReady(GoogleMap p1)
	{
		gmap = p1;
        gmap.setMinZoomPreference(12);
		gmap.setMyLocationEnabled(true);
		
		
		LocationManager locationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
		Criteria criteria = new Criteria();

		Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
		if (location != null)
		{
			gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

			CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
				.zoom(20)                   // Sets the zoom
				.bearing(90)                // Sets the orientation of the camera to east
				.build();                   // Creates a CameraPosition from the builder
			gmap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));       
		}
		
		if(mLatLng_A != null) {
			gmap.addMarker(new MarkerOptions().position(mLatLng_A));

				}
	}

	
	
	
	
	
	
	
	
	
	
	
//Constructor
	public static mandaditosMainFr newInstance(String address, LatLng mLatLng_A, String where)
	{
		mandaditosMainFr fragnent = new mandaditosMainFr();
		Bundle args = new Bundle();
		args.putString("address", address);
		args.putParcelable("latlngA", mLatLng_A);
		fragnent.setArguments(args);
		return fragnent;
	}
	private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//get bundle
	@Override
	public void onCreate(Bundle savedInstanceState)
{
address_A = getArguments().getString("address");
mLatLng_A = getArguments().getParcelable("latlngA");
super.onCreate(savedInstanceState);
	
}














//content view
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState)
{
	View v = inflater.inflate(R.layout.mandaditos_main, container, false);
	Bundle mapViewBundle = null;
	if (savedInstanceState != null) {
		mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
	}
	
	edPartida = v.findViewById(R.id.mandaditosmainEditText1);
	edDestino = v.findViewById(R.id.mandaditosmainEditText2);
	mapView = v.findViewById(R.id.map_view);
	mapView.onCreate(mapViewBundle);
	mapView.getMapAsync(this);
	
	
	
	
	
	
	
	
	
	
//get address from addresspicker
	edPartida.setText(address_A+where);
	
	
	
	
	
	
//ed click 
	edPartida.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View p1)
			{
				if (savedInstanceState != null)
				{
					fragmentToOpen = getFragmentManager().getFragment(savedInstanceState, "current");
				}
				else
				{
					FragmentManager manager = getFragmentManager();
					final FragmentTransaction transaction= manager.beginTransaction();

					fragmentToOpen = addressPickerFr.newInstance("partida");


					try
					{
						transaction.replace(R.id.launcherFrameLayout, fragmentToOpen, "current");
						transaction.commit();	}
					catch (Exception e)
					{
						e.printStackTrace();	}
				}
				
			}
		});
	edDestino.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View p1)
			{
				if (savedInstanceState != null)
				{
					fragmentToOpen = getFragmentManager().getFragment(savedInstanceState, "current");
				}
				else
				{
					FragmentManager manager = getFragmentManager();
					final FragmentTransaction transaction= manager.beginTransaction();

					fragmentToOpen = addressPickerFr.newInstance("destino");


					try
					{
						transaction.replace(R.id.launcherFrameLayout, fragmentToOpen, "current");
						transaction.commit();	}
					catch (Exception e)
					{
						e.printStackTrace();	}
				}
				
			}
		});

	
	
	return v;
	
}





@Override
public void onSaveInstanceState(Bundle outState)
{
	super.onSaveInstanceState(outState);
	Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
	if (mapViewBundle == null) {
		mapViewBundle = new Bundle();
		outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
	}

	mapView.onSaveInstanceState(mapViewBundle);
}

@Override
public void onResume()
{
	super.onResume();
	mapView.onResume();
}

@Override
public void onStart()
{
	super.onStart();
	mapView.onStart();
}

@Override
public void onStop()
{
	super.onStop();
	mapView.onStop();
}

@Override
public void onPause()
{
	super.onPause();
	mapView.onPause();
}

@Override
public void onDestroy()
{
	super.onDestroy();
	mapView.onDestroy();
}

@Override
public void onLowMemory()
{
	super.onLowMemory();
	mapView.onLowMemory();
}
	









	
}
