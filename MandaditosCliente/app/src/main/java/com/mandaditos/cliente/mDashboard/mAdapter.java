package com.mandaditos.cliente.mDashboard;

import android.content.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.mandaditos.cliente.*;
import com.mandaditos.cliente.models.*;
import java.util.*;

public class mAdapter extends RecyclerView.Adapter<mViewHolder>
{

    private Context mContext;
    private List<mandaditosModel> mDataList;

    mAdapter(Context mContext, List< mandaditosModel > mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_row, parent, false);
        return new mViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final mViewHolder holder, int position) {
		holder.PartidaTv.setText(mDataList.get(position).getPartida());
		holder.DestinoTv.setText(mDataList.get(position).getDestino());
		holder.DistanciaTv.setText(mDataList.get(position).getDistancia());
		holder.FechaEtaTv.setText(mDataList.get(position).getFecha()+" "+mDataList.get(position).getEta());
		holder.DondeRecogerDineroTv.setText(mDataList.get(position).getRecogerDineroEn());
		holder.CostoTv.setText(mDataList.get(position).getCosto());
		holder.EstadoDeOrdenTv.setText(mDataList.get(position).getEstadoDeOrden());
		holder.NumeroDeOrdenTv.setText(mDataList.get(position).getNumeroDeOrden());
		holder.driverAsignadoTv.setText(mDataList.get(position).getDriverAsignado());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}

class mViewHolder extends RecyclerView.ViewHolder {

    TextView NumeroDeOrdenTv,PartidaTv,DestinoTv,DistanciaTv,FechaEtaTv,DondeRecogerDineroTv,CostoTv,EstadoDeOrdenTv,driverAsignadoTv;
	private Context context;

    mViewHolder(View v) {
        super(v);
		
				NumeroDeOrdenTv = v.findViewById(R.id.dashboarOrderTitle);
				PartidaTv = v.findViewById(R.id.dashboardAddressA);
				DestinoTv = v.findViewById(R.id.dashboardAddressB);
				DistanciaTv = v.findViewById(R.id.dashboardDistance);
				FechaEtaTv = v.findViewById(R.id.dashboardDateEta);
				DondeRecogerDineroTv = v.findViewById(R.id.dashboardWhereGetMoney);
				CostoTv = v.findViewById(R.id.dashboardTotalCost);
				EstadoDeOrdenTv = v.findViewById(R.id.dashboardOrderStatus);
				driverAsignadoTv = v.findViewById(R.id.conductorAsignadoDashboard);
				context = v.getContext();

		v.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
				}
			});
    }







}

