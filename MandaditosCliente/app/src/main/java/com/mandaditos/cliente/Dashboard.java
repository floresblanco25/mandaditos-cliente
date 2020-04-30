package com.mandaditos.cliente;
import android.support.v7.app.*;
import android.os.*;
import com.google.firebase.database.*;
import android.widget.*;

public class Dashboard extends AppCompatActivity
{
DatabaseReference mDataBase;
TextView orderNumber, partida,destino,distancia,dateEta, 
whereTogetMoney,totalCost;
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
		
		
		
		
		
		
	mDataBase.child("Orden").addValueEventListener(new ValueEventListener(){

	@Override
	public void onDataChange(DataSnapshot p1)
	{
		if(p1.exists()){
			String partidaTx = p1.child("Partida").getValue().toString();
			String destinoTx = p1.child("Destino").getValue().toString();
			String distanciaTx = p1.child("Distancia").getValue().toString();
			String dateTx = p1.child("Fecha").getValue().toString();
			String etaTx = p1.child("ETA").getValue().toString();
			String whereGetMoneyTx = p1.child("Recoger dinero en").getValue().toString();
			String costo = p1.child("Costo").getValue().toString();
			
			
			
			
			partida.setText(partidaTx);
			destino.setText(destinoTx);
			distancia.setText(distanciaTx);
			dateEta.setText(dateTx+" "+etaTx);
			whereTogetMoney.setText(whereGetMoneyTx);
			totalCost.setText(costo);

		}
		else{
		}
	}

	@Override
	public void onCancelled(DatabaseError p1)
	{
	}
	});
	}
	
}
