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
public class AddressPickerFr extends Fragment implements OnMapReadyCallback
{

	
	private MapView mapView;
    private GoogleMap gmap;
	
	
	
	
	
	
	
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
		
		//LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        gmap.clear();
//        markerOptions.title("Current Position");
//        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
//        markerOptions.getPosition();
//        Marker mCurrLocationMarker = gmap.addMarker(markerOptions);
//		
		
		if(gmap != null) {

			gmap.setOnMapLongClickListener(new
				GoogleMap.OnMapLongClickListener() {

					private Marker marker;
					@Override
					public void onMapLongClick (LatLng latLng){
						Geocoder geocoder =
							new Geocoder(getActivity());
						List<Address> list;
						try {
							list = geocoder.getFromLocation(latLng.latitude,
															latLng.longitude, 1);
						} catch (IOException e) {
							return;
						}
						Address address = list.get(0);
						if (marker != null) {
							marker.remove();
						}

						MarkerOptions options = new MarkerOptions()
							.title(address.getLocality())
							.position(new LatLng(latLng.latitude,
												 latLng.longitude));

						marker = gmap.addMarker(options);
					}
				});
				}
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