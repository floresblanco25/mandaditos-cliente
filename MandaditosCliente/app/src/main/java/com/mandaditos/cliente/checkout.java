package com.mandaditos.cliente;


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
public class checkout extends Fragment 
{
	public static String tag ="checkout";



	private checkoutListener listener;
	private TextView addressA,addressB,mDate,mEtaText,total,distTotal;
	private RadioGroup money;
	private String where="Partida";


	





	//interface
	public interface checkoutListener
	{
        void sentAddress(CharSequence input, Boolean isPartidaaa, MarkerOptions markerOpt);
    }











	//Constructor
	public static checkout newInstance()
	{
        checkout fragment = new checkout();
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
		total = v.findViewById(R.id.checkoutTotal);
		money = v.findViewById(R.id.checkoutRadioGroupMoney);
		distTotal = v.findViewById(R.id.totalDistance);









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
		total.setText(t);
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
        if (context instanceof checkoutListener)
		{
            listener = (checkoutListener) context;
        }
		else
		{
            throw new RuntimeException(context.toString()
									   + " must implement checkoutlistener");
        }
	}










}
