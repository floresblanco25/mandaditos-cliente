package com.mandaditos.cliente;

import android.content.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.mandaditos.cliente.*;
import java.util.*;
import android.view.View.*;

public class ServicesAdapter extends RecyclerView.Adapter<serviceViewHolder>
 {

    private Context mContext;
    private List<ServicesModel> mserviceList;

    ServicesAdapter(Context mContext, List< ServicesModel > mserviceList) {
        this.mContext = mContext;
        this.mserviceList = mserviceList;
    }

    @Override
    public serviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_row, parent, false);
        return new serviceViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final serviceViewHolder holder, int position) {
        holder.mImage.setImageResource(mserviceList.get(position).getServiceImage());
        holder.mTitle.setText(mserviceList.get(position).getServiceName());
		holder.mDetails.setText(mserviceList.get(position).getServiceDescription());
    }

    @Override
    public int getItemCount() {
        return mserviceList.size();
    }
}

class serviceViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle,mDetails;
	CardView cv;

    serviceViewHolder(View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
		mDetails = itemView.findViewById(R.id.servDetails);
		cv = itemView.findViewById(R.id.cardview);
		
		itemView.setOnClickListener(new OnClickListener(){

@Override
public void onClick(View p1)
{
	final Intent i = new Intent(p1.getContext(), LauncherActivity.class);
	p1.getContext().startActivity(i);
}
});
    }
}
