package com.mandaditos.cliente.mMandaditos;

import android.app.*;
import android.content.*;
import android.location.*;
import android.os.*;
import android.text.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.mandaditos.cliente.*;
import com.mandaditos.cliente.mMandaditos.*;

import com.mandaditos.cliente.R;
import android.support.v7.widget.*;

public class MandaditosAddressPick extends Fragment implements OnMapReadyCallback
{
	private MapView mapView;
    private GoogleMap gmap;
	private Button okbtn;
	private EditText edAddress;
	private MarkerOptions marker;
	public static String tag ="addresspicker";
	private Boolean isPartidaa;
	private LatLng mLatLng;
	
	
	
	
	
	
	
	
	private Listener listener;
	//interface
	public interface Listener {
        void sentAddress(CharSequence input,Boolean isPartidaaa, MarkerOptions markerOpt,LatLng LatLng);
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
		
		
		if (location != null){
			gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));
			CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(new LatLng(location.getLatitude(), location.getLongitude()))
				.zoom(20).bearing(90).build();          
			gmap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));       
		}
		
		
		
		//add marker onckick
		gmap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
				@Override
				public void onMapClick(LatLng point) {
					gmap.clear();
					marker = new MarkerOptions().position(point);
					mLatLng = point;
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
		okbtn = v.findViewById(R.id.address_pickerButtonNext);
		mapView = v.findViewById(R.id.map_view_picker);
		mapView.onCreate(mapViewBundle);
		mapView.getMapAsync(this);

		
		
		
		
		
		
		
		
		
		
		
		//button ok
		okbtn.setOnClickListener(new OnClickListener(){
				private MandaditosMain fragmentToOpen;
				@Override
				public void onClick(View p1){
					String input = edAddress.getText().toString();
					if(input.isEmpty()){
						edAddress.setError("Ingresa la dirección");
						edAddress.requestFocus();
					}
					else  if(marker==null){
						edAddress.setError("Selecciona en el mapa el lugar exacto");
						edAddress.requestFocus();
					}
					else  if(input.isEmpty() && marker==null){
						Toast.makeText(getActivity(),"Ingresa la dirección y toca en el mapa el lugar exacto",Toast.LENGTH_SHORT).show();
					}
					else  if(!(input.isEmpty() && marker==null)){
						listener.sentAddress(input,isPartidaa,marker,mLatLng);
						FragmentManager manager = getFragmentManager();
						final FragmentTransaction transaction= manager.beginTransaction();
						transaction.show(getFragmentManager().findFragmentByTag(MandaditosMain.tag))
							.hide(getFragmentManager().findFragmentByTag(tag))
							.commit();	
					}
					else{
						Toast.makeText(getActivity(),"Error",Toast.LENGTH_SHORT).show();
					}
					
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
	public void restartInstance(){
		marker=null;
		edAddress.setError(null);
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