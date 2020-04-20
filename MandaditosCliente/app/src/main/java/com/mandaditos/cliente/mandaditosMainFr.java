package com.mandaditos.cliente;
import android.app.*;
import android.content.*;
import android.location.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.mandaditos.cliente.*;
import java.util.*;

import com.mandaditos.cliente.R;
import android.graphics.*;






public class mandaditosMainFr extends Fragment implements OnMapReadyCallback
{
	private MapView mapView;
    private GoogleMap gmap;
	private TextView edPartida,edDestino,distanceTxt;
	public static String tag ="mandaditosmain";
	private mandaditosMainFrListener listener;
	private MarkerOptions m1,m2;
	
	
	
	
	
	
	
	
	

	//interface
	public interface mandaditosMainFrListener {
		void setIsPartida(Boolean isPartidaaa);
    }
	
	
	
	
	
	
	
	
	
	
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
		
	}

	
	
	
	
	
	
	
	
	
	
	
//Constructor
	public static mandaditosMainFr newInstance()
	{
		mandaditosMainFr fragnent = new mandaditosMainFr();
		return fragnent;
	}
	private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//get bundle
	@Override
	public void onCreate(Bundle savedInstanceState)
{
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
	
	edPartida = v.findViewById(R.id.ed1);
	edDestino = v.findViewById(R.id.ed2);
	distanceTxt = v.findViewById(R.id.mandaditos_distance);
	mapView = v.findViewById(R.id.map_view);
	mapView.onCreate(mapViewBundle);
	mapView.getMapAsync(this);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//ed click 
	edPartida.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View p1)
			{
				
				listener.setIsPartida(true);
					FragmentManager manager = getFragmentManager();
					final FragmentTransaction transaction= manager.beginTransaction();
						transaction.show(getFragmentManager().findFragmentByTag(addressPickerFr.tag))
						.hide(getFragmentManager().findFragmentByTag(tag))
						.commit();	
						}
		});
		
		
	edDestino.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View p1)
				{
					listener.setIsPartida(false);
				FragmentManager manager = getFragmentManager();
				final FragmentTransaction transaction= manager.beginTransaction();
				transaction.show(getFragmentManager().findFragmentByTag(addressPickerFr.tag))
					.hide(getFragmentManager().findFragmentByTag(tag))
					.commit();	
							}
				
		});

	
	
	return v;
	
}













//Voids
	public void setPartidaAddress(CharSequence newText) {
			edPartida.setText(newText);
		}
	public void setDestinoAddress(CharSequence newText){
		edDestino.setText(newText);
	}
	public void setMarker_partida(MarkerOptions markerOpt){
		m1=markerOpt;
		gmap.addMarker(m1);
	}
	public void setMarker_destino(MarkerOptions markerOpt){
		m2=markerOpt;
		gmap.addMarker(m2);
	}
	public void setDistance(){
			LatLng distance1=  m1.getPosition();
			LatLng distance2 = m2.getPosition();
			
			Location location1 = new Location("Partida");
			location1.setLatitude(distance1.latitude);
			location1.setLongitude(distance1.longitude);
			
			Location location2 = new Location("Destino");
			location2.setLatitude(distance2.latitude);
			location2.setLongitude(distance2.longitude);
			
			float distance =location1.distanceTo(location2);
		String kmDistance = String.format("%.2f", (distance/1000)*1.14);
		// Add a thin red line from London to New York.
		PolylineOptions line = new PolylineOptions()
										.add(distance1, distance2)
										.width(5)
										.color(Color.RED);
										
										
										
		gmap.addPolyline(line);
		distanceTxt.setText(String.valueOf(kmDistance)+" km");
		
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
	
	@Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

	@Override
	public void onAttach(Context context)
	{
		super.onAttach(context);
        if (context instanceof mandaditosMainFrListener) {
            listener = (mandaditosMainFrListener) context;
        } else {
            throw new RuntimeException(context.toString()
									   + " must implement FragmentAListener");
        }
	}








	
}
