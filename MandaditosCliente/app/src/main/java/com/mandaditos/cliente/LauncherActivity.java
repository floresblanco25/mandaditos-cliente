package com.mandaditos.cliente;
import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.widget.*;

public class LauncherActivity extends AppCompatActivity
{

	private static Context mContext;
	Fragment fragmentToOpen;
    FrameLayout container;

	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher);
		
		
		
		
		if (savedInstanceState != null)
		{
			fragmentToOpen = getFragmentManager().getFragment(savedInstanceState, "current");
		}
		else
		{
			FragmentManager manager = getFragmentManager();
			final FragmentTransaction transaction= manager.beginTransaction();

			fragmentToOpen = mandaditosMainFr.newInstance("",null,null);


			try
			{
				transaction.replace(R.id.launcherFrameLayout, fragmentToOpen, "addresspicker");
				transaction.addToBackStack("current");
				transaction.commit();	}
			catch (Exception e)
			{
				e.printStackTrace();	}
		}
		
	}
	
	public static Context contxt(){
		
		
		return mContext;
	}
	
}
