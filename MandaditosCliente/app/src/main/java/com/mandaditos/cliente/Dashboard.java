package com.mandaditos.cliente;
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
	whereTogetMoney,totalCost,orderStatus;
	FirebaseAuth mFirebaseAuth;

	private ArrayList<MandaditosModel> mList;
	RecyclerView recyclerView;
    mAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		mDataBase = FirebaseDatabase.getInstance().getReference();
		mFirebaseAuth = FirebaseAuth.getInstance();
		mList = new ArrayList<MandaditosModel>();









		FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
		String email = mFirebaseUser.getEmail().toString();
		int index = email.indexOf('@');
		email = email.substring(0, index);
		String userId = email.toLowerCase();
		mDataBase.child(DbNames.usuarios+"/" + userId + "/Ordenes/Mandaditos").addValueEventListener(new ValueEventListener(){

				@Override
				public void onDataChange(DataSnapshot p1)
				{
					mList.clear();
					if (p1.exists())
					{
						for (DataSnapshot childDataSnapshot : p1.getChildren())
						{
							childDataSnapshot.getKey().toString();
							MandaditosModel mObject = new MandaditosModel
							(
								childDataSnapshot.getKey().toString(),
								childDataSnapshot.child(DbNames.partida).getValue().toString(),
								childDataSnapshot.child(DbNames.destino).getValue().toString(),
								childDataSnapshot.child(DbNames.distancia).getValue().toString(),
								childDataSnapshot.child(DbNames.fecha).getValue().toString(),
								childDataSnapshot.child(DbNames.eta).getValue().toString(),
								childDataSnapshot.child(DbNames.wheregetmoney).getValue().toString(),
								childDataSnapshot.child(DbNames.costo).getValue().toString(),
								childDataSnapshot.child(DbNames.orderStatus).getValue().toString());
							String status = childDataSnapshot.child(DbNames.orderStatus).getValue().toString();
							if(status.contains(DbNames.sinCompletar)){
							mList.add(mObject);
							}else{
							}
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
