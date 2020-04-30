package com.mandaditos.cliente.Mandaditos;


import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.RadioGroup.*;
import com.google.android.gms.maps.model.*;
import com.mandaditos.cliente.*;
import java.text.*;
import java.util.*;
import android.util.*;
public class MandaditosCkeckout extends Fragment 
{
	public static String tag ="checkout";



	private Listener listener;
	private TextView addressA,addressB,mDate,mEtaText,totalCost,distTotal;
	private RadioGroup money;
	private String where="Partida";
	private Button checkoutButton;


	





	//interface
	public interface Listener
	{
		void onGatherAllData(String addressA,String addressB,String date, String eta, String totalMoney, 
		String totalDist,String whereGetMoney);
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
					if (whereButton.getText().toString().toLowerCase().contains("partida"))
					{
						where = "partida";
					}

					if (whereButton.getText().toString().toLowerCase().contains("destino"))
					{
						where = "destino";
					}
				}

			});
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			//Button checkout
		checkoutButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					listener.onGatherAllData(addressA.getText().toString(),addressB.getText().toString(),
											 mDate.getText().toString(),mEtaText.getText().toString(),
											 totalCost.getText().toString(),distTotal.getText().toString(),
											 where);
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
