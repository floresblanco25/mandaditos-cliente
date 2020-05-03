package com.mandaditos.cliente.Mandaditos;


import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.RadioGroup.*;
import com.google.android.gms.maps.model.*;
import com.google.firebase.auth.*;
import com.mandaditos.cliente.*;
import java.text.*;
import java.util.*;

import android.view.View.OnClickListener;
import com.mandaditos.cliente.R;
import com.google.firebase.database.*;
public class MandaditosCkeckout extends Fragment 
{
	public static String tag ="checkout";



	private Listener listener;
	private TextView addressA,addressB,mDate,mEtaText,totalCost,distTotal;
	private RadioGroup money;
	private String where=DbNames.Partida;
	private Button checkoutButton;
	private String EstadoDeOrden=DbNames.SinCompletar;

	private TextView dondeRecogeremosEffectivo;
	private LatLng latLngA;

	private LatLng latLngB;
	
	FirebaseAuth mFirebaseAuth;
	DatabaseReference mDataBase;
	private String Usuario;


	





	//interface
	public interface Listener
	{
		void onGatherAllData(
			String Usuario,String Partida, String Destino, String Distancia, String Fecha, 
			String ETA, String RecogerDineroEn, String Costo, String EstadoDeOrden,LatLng LatLngA,
			LatLng LatLngB
		);
    }











	//Constructor
	public static MandaditosCkeckout newInstance()
	{
        MandaditosCkeckout fragment = new MandaditosCkeckout();
        return fragment;
    }











	//get args
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mFirebaseAuth = FirebaseAuth.getInstance();
		mDataBase = FirebaseDatabase.getInstance().getReference();
		FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
		String email = mFirebaseUser.getEmail().toString();
		int index = email.indexOf('@');
		email = email.substring(0, index);
		String userId = email.toLowerCase();
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Usuarios/" + userId + "/Perfil").child("nombre");
		ref.addListenerForSingleValueEvent(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot dataSnapshot) {
					String Usuario = dataSnapshot.getValue(String.class);
					setUsuario(Usuario);

				}

				@Override
				public void onCancelled(DatabaseError databaseError) {

				}
			});

	}










	//set view
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.checkout, container, false);
		addressA = v.findViewById(R.id.checkoutAddressA);
		addressB = v.findViewById(R.id.checkoutAddressB);
		mDate = v.findViewById(R.id.checkoutDate);
		mEtaText = v.findViewById(R.id.checkoutHour);
		totalCost = v.findViewById(R.id.checkoutTotal);
		money = v.findViewById(R.id.checkoutRadioGroupMoney);
		distTotal = v.findViewById(R.id.totalDistance);
		checkoutButton = v.findViewById(R.id.checkoutButtonProcess);
		dondeRecogeremosEffectivo = v.findViewById(R.id.checkoutTextDonderecogerefectivo);
		









		//set text
		String date = new SimpleDateFormat("EEE dd-MMM", Locale.getDefault()).format(new Date());
		mDate.setText(date);
		












		//radiobuttons
		money.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(RadioGroup p1, int p2)
				{
					int selectedId = money.getCheckedRadioButtonId();

					// find the radiobutton by returned id
					RadioButton whereButton = p1.findViewById(selectedId);
					if (whereButton.getText().toString().toLowerCase().contains(DbNames.Partida.toLowerCase()))
					{
						where = DbNames.Partida;
					}

					if (whereButton.getText().toString().toLowerCase().contains(DbNames.Destino.toLowerCase()))
					{
						where = DbNames.Destino;
					}
				}

			});
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			//Button checkout
		checkoutButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					String Partida = addressA.getText().toString();
					String Destino = addressB.getText().toString();
					String Distancia = distTotal.getText().toString();
					String Fecha = mDate.getText().toString();
					String ETA = mEtaText.getText().toString();
					String Costo = totalCost.getText().toString();
					LatLng LatLngA = latLngA ;
					LatLng LatLngB = latLngB;
					String RecogerDineroEn = where;
					
					
					

					if (money.getCheckedRadioButtonId() == -1)
{
						dondeRecogeremosEffectivo.setError("Selecciona donde recogeremos el pago");
						dondeRecogeremosEffectivo.requestFocus();
					}
					else  if(!(money.getCheckedRadioButtonId()== -1)){
						listener.onGatherAllData(
							Usuario, Partida,  Destino,  Distancia,  Fecha, 
							ETA,  RecogerDineroEn,  Costo,  EstadoDeOrden,LatLngA,LatLngB
												 );
												 
												 
						Intent i = new Intent(getActivity(),Dashboard.class);
						getActivity().startActivity(i);
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
	}












	//voids
	public void setUsuario(String usuario)
	{
		Usuario=usuario;
	}
	
	public void setAddressA(CharSequence t)
	{
		addressA.setText(t);
	}
	public void setAddressB(CharSequence t)
	{
		addressB.setText(t);
	}

	public void setTotalCost(CharSequence t)
	{
		totalCost.setText(t);
	}
	public String getWhereGetMoney()
	{
		return where;
	}
	
	public void setTotalKm(String km)
	{
		distTotal.setText(km);
	}
	public void setETAText(CharSequence t){
		mEtaText.setText(t);
	}
	public void setLatLngA(LatLng ltlngA){
		latLngA=ltlngA;
	}
	public void setLatLngB(LatLng ltlngB){
		latLngB=ltlngB;
	}
	







	
	
	
	


	//cycles
    @Override
    public void onDetach()
	{
        super.onDetach();
        listener = null;
    }

	@Override
	public void onAttach(Context context)
	{
		super.onAttach(context);
        if (context instanceof Listener)
		{
            listener = (Listener) context;
        }
		else
		{
            throw new RuntimeException(context.toString()
									   + " must implement checkoutlistener");
        }
	}










}
