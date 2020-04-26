package com.mandaditos.cliente.Mandaditos;

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
import com.mandaditos.cliente.Mandaditos.*;
import android.content.*;
public class MandaditosAddressPick extends Fragment implements OnMapReadyCallback
{
	private MapView mapView;
    private GoogleMap gmap;
	private Button okbtn;
	private EditText edAddress;
	private MarkerOptions marker;
	public static String tag ="addresspicker";
	private Boolean isPartidaa;
	
	
	
	
	
	
	
	
	private Listener listener;
	//interface
	public interface Listener {
        void sentAddress(CharSequence input,Boolean isPartidaaa, MarkerOptions markerOpt);
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
		//marker
		gmap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

				@Override
				public void onMapClick(LatLng point) {
					gmap.clear();
					marker = new MarkerOptions().position(point);
					gmap.addMarker(marker);
				}
			});
			
			
	}






	
	
	
	
	


	//Constructor
	public static MandaditosAddressPick newInstance()
	{
        MandaditosAddressPick fragment = new MandaditosAddressPick();
        return fragment;
    }

	private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";








	
	
	//get args
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

	}










	//set view
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.address_picker, container, false);
		Bundle mapViewBundle = null;
		if (savedInstanceState != null) {
			mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
		}
		edAddress = v.findViewById(R.id.address_pickerEditText);
		mapView = v.findViewById(R.id.map_view_picker);
		mapView.onCreate(mapViewBundle);
		mapView.getMapAsync(this);
		

		
		
		
		
		
		
		
		
		
		
		
		//button ok
		okbtn = v.findViewById(R.id.address_pickerButtonNext);
		okbtn.setOnClickListener(new OnClickListener(){

				private MandaditosMain fragmentToOpen;

				

				@Override
				public void onClick(View p1)
				{
					CharSequence input = edAddress.getText();
					listener.sentAddress(input,isPartidaa,marker);
					FragmentManager manager = getFragmentManager();
					final FragmentTransaction transaction= manager.beginTransaction();
					transaction.show(getFragmentManager().findFragmentByTag(MandaditosMain.tag))
						.hide(getFragmentManager().findFragmentByTag(tag))
						.commit();	
						}
			});



			
			
			
			
			
			
		//end
		return v;

	}





	
	
	
	
	
	//instance
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

	
	
	
	
	
	
	
	
	
	
	
	//voids
	public void setIsPartida(Boolean answer){
		isPartidaa = answer;
	}
	public void setFieldText(CharSequence text){
		edAddress.setText(text);
	}
	public void clearMarkers(){
		gmap.clear();
	}

	
	
	
	//cycles
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
        if (context instanceof Listener) {
            listener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString()
									   + " must implement FragmentAListener");
        }
	}










}
