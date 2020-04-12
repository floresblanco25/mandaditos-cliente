package com.mandaditos.cliente;
import android.app.*;
import android.location.*;
import android.os.*;
import android.view.*;
import com.google.android.gms.common.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.mandaditos.cliente.*;

import com.mandaditos.cliente.R;
public class AddressPickerFr extends Fragment implements OnMapReadyCallback,LocationListener
{

	@Override
	public void onLocationChanged(Location location)
	{
			//Move the camera to the user's location and zoom in!
			gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 100.0f));
	}

	@Override
	public void onStatusChanged(String p1, int p2, Bundle p3)
	{
		// TODO: Implement this method
	}

	@Override
	public void onProviderEnabled(String p1)
	{
		// TODO: Implement this method
	}

	@Override
	public void onProviderDisabled(String p1)
	{
		// TODO: Implement this method
	}
	

	
	private MapView mapView;
    private GoogleMap gmap;
	
	
	
	
	
	
	
	@Override
	public void onMapReady(GoogleMap p1)
	{
		gmap = p1;
        gmap.setMinZoomPreference(12);
		gmap.setMyLocationEnabled(true);
		
		
		LocationManager locationManager = (LocationManager)
			getActivity().getSystemService(getActivity().LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		Location location = locationManager.getLastKnownLocation(locationManager
																 .getBestProvider(criteria, false));
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		
		
		
        LatLng sv = new LatLng(latitude, longitude);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(sv));
	}
	
	
	
	
	
	
	
	
	//Constructor
	public static AddressPickerFr newInstance()
	{
        AddressPickerFr fragment = new AddressPickerFr();
        return fragment;
    }
	
	private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
	
	
	
	
	
	

	
	@Override
	public void onCreate(Bundle savedInstanceState)
{
super.onCreate(savedInstanceState);
	
}











@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
{
	View v = inflater.inflate(R.layout.addresspicker, container, false);
	Bundle mapViewBundle = null;
	if (savedInstanceState != null) {
		mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
	}

	mapView = v.findViewById(R.id.map_view);
	mapView.onCreate(mapViewBundle);
	mapView.getMapAsync(this);
	
	
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
