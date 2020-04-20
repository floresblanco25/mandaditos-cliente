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
import com.mandaditos.cliente.data.*;
import android.content.*;
public class checkout extends Fragment 
{



	private checkoutListener listener;
	//interface
	public interface checkoutListener {
        void sentAddress(CharSequence input,Boolean isPartidaaa, MarkerOptions markerOpt);
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




	//cycles

	
	
	
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

	@Override
	public void onAttach(Context context)
	{
		super.onAttach(context);
        if (context instanceof checkoutListener) {
            listener = (checkoutListener) context;
        } else {
            throw new RuntimeException(context.toString()
									   + " must implement checkoutlistener");
        }
	}










}
