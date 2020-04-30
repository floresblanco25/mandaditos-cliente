package com.mandaditos.cliente;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.widget.*;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;
import com.mandaditos.cliente.Mandaditos.*;
import java.util.*;
import com.mandaditos.cliente.mDashboard.*;
import android.util.*;

public class Dashboard extends AppCompatActivity
{
	DatabaseReference mDataBase;
	TextView orderNumber, partida,destino,distancia,dateEta, 
	whereTogetMoney,totalCost;
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
		orderNumber = findViewById(R.id.dashboarOrderTitle);
		partida = findViewById(R.id.dashboardAddressA);
		destino = findViewById(R.id.dashboardAddressB);
		distancia = findViewById(R.id.dashboardDistance);
		dateEta = findViewById(R.id.dashboardDateEta);
		whereTogetMoney = findViewById(R.id.dashboardWhereGetMoney);
		totalCost = findViewById(R.id.dashboardTotalCost);
		mFirebaseAuth = FirebaseAuth.getInstance();
		mList = new ArrayList<MandaditosModel>();









		FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
		String email = mFirebaseUser.getEmail().toString();
		int index = email.indexOf('@');
		email = email.substring(0, index);
		String userId = email.toLowerCase();
		mDataBase.child("Usuarios/" + userId + "/Ordenes/Mandaditos").addValueEventListener(new ValueEventListener(){

				@Override
				public void onDataChange(DataSnapshot p1)
				{
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
								childDataSnapshot.child(DbNames.costo).getValue().toString());
							mList.add(mObject);
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

}
