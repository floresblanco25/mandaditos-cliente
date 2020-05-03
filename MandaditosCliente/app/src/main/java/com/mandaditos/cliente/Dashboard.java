package com.mandaditos.cliente;
import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.util.*;
import android.widget.*;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;
import com.mandaditos.cliente.Mandaditos.*;
import com.mandaditos.cliente.mDashboard.*;
import java.util.*;

public class Dashboard extends AppCompatActivity
{
	DatabaseReference mDataBase;
	TextView orderNumber, partida,destino,distancia,dateEta, 
	whereTogetMoney,totalCost,EstadoDeOrden;
	FirebaseAuth mFirebaseAuth;

	private ArrayList<MandaditosDataModel> mList;
	RecyclerView recyclerView;
    mAdapter adapter;

	private ProgressDialog pDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		mDataBase = FirebaseDatabase.getInstance().getReference();
		mFirebaseAuth = FirebaseAuth.getInstance();
		mList = new ArrayList<MandaditosDataModel>();
		
		
		
		
		
		
		
		//dialog 
		pDialog = new ProgressDialog(Dashboard.this);
		pDialog.setMessage("Cargando datos de los servidores..");
		pDialog.show();









		FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
		String email = mFirebaseUser.getEmail().toString();
		int index = email.indexOf('@');
		email = email.substring(0, index);
		String userId = email.toLowerCase();
		mDataBase.child(DbNames.Usuarios+"/" + userId + "/Ordenes/Mandaditos").addValueEventListener(new ValueEventListener(){

				@Override
				public void onDataChange(DataSnapshot p1)
				{
					pDialog.dismiss();
					mList.clear();
					if (p1.exists())
					{
						for (DataSnapshot childDataSnapshot : p1.getChildren())
						{
							childDataSnapshot.getKey().toString();
						}

						//Recycler
						recyclerView =  findViewById(R.id.dashboardRecycler);
						adapter = new mAdapter(mList);
						LinearLayoutManager mLayoutManager =new LinearLayoutManager(Dashboard.this);
						recyclerView.setLayoutManager(mLayoutManager);
						recyclerView.setItemAnimator(new DefaultItemAnimator());
						recyclerView.setHasFixedSize(false);
						recyclerView.setAdapter(adapter);

					}
					else
					{
						Log.wtf("falla de firebase", "no exixtse");
					}
				}

				@Override
				public void onCancelled(DatabaseError p1)
				{
				}
			});
	}
	
	@Override
	public void onBackPressed() {
		finishAffinity();
        startActivity(new Intent(Dashboard.this,Home.class));
    } 

}
