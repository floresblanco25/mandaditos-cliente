package com.mandaditos.cliente.mDashboard;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.mandaditos.cliente.*;
import com.mandaditos.cliente.Mandaditos.*;
import java.util.*;

public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolder>
{


//constructor
	List<MandaditosModel> recyclerRowModelList;
    LinearLayout row;






    public mAdapter(List<MandaditosModel> recyclerRowModelList)
    {
        this.recyclerRowModelList = recyclerRowModelList;
    }







//filter
	public void filterList(List<MandaditosModel> filterdNames)
	{
        this.recyclerRowModelList = filterdNames;
        notifyDataSetChanged();
    }






//inflate
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_row, parent, false);//Inflates the xml row
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }






//bind
    @Override
    public void onBindViewHolder(ViewHolder xmlRow, int position)
    {
		MandaditosModel object = recyclerRowModelList.get(position);
		String orderName = object.getOrderName();
		String partida = object.getPartida();
		String destino = object.getDestino();
		String distancia = object.getDistancia();
		String date = object.getFecha();
		String eta = object.getEta();
		String wheremoney = object.getWheregetMoney();
		String cost = object.getCost();
		String status = object.getOrderStatus();




//set row texts
		xmlRow.orderNumber.setText(orderName);
        xmlRow.partida.setText(partida);
		xmlRow.destino.setText(destino);
		xmlRow.distancia.setText(distancia);
		xmlRow.dateEta.setText(date+" "+eta);
		xmlRow.whereTogetMoney.setText(wheremoney);
		xmlRow.totalCost.setText(cost);
		xmlRow.orderStatus.setText(status);






//set progress bar 


    }






//count
    @Override
    public int getItemCount()
    {
        return recyclerRowModelList.size();
    }







//viewholder class
    public class ViewHolder extends RecyclerView.ViewHolder 
    {

//initialize
        private TextView orderNumber,partida,destino,distancia,dateEta,whereTogetMoney,totalCost,orderStatus;;
		private Context context;



//view holder
        public ViewHolder(final View v)
		{
            super(v);
//initialize
            orderNumber = v.findViewById(R.id.dashboarOrderTitle);
			partida = v.findViewById(R.id.dashboardAddressA);
			destino = v.findViewById(R.id.dashboardAddressB);
			distancia = v.findViewById(R.id.dashboardDistance);
			dateEta = v.findViewById(R.id.dashboardDateEta);
			whereTogetMoney = v.findViewById(R.id.dashboardWhereGetMoney);
			totalCost = v.findViewById(R.id.dashboardTotalCost);
			orderStatus = v.findViewById(R.id.dashboardOrderStatus);
			context = v.getContext();











//view click
			v.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1)
					{
						//TODO implement actions for each order
					}
				});

        }


    }


}
