package me.chenfuduo.myjob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chenfuduo on 2015/9/17.
 */
public class JobAdapter extends BaseAdapter{

    private List<JobInfo> jobInfoList;
    private Context context;

    public JobAdapter(List<JobInfo> jobInfoList, Context context) {
        this.jobInfoList = jobInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return jobInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return jobInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);
            viewHolder.company = (TextView) convertView.findViewById(R.id.company);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.address = (TextView) convertView.findViewById(R.id.address);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String name = jobInfoList.get(position).getName();

        if (name.equals("中国科学技术大学")){
            viewHolder.icon.setImageResource(R.drawable.a47);
        }else if (name.equals("合肥工业大学")){
            viewHolder.icon.setImageResource(R.drawable.a49);
        }else if(name.equals("安徽大学")){
            viewHolder.icon.setImageResource(R.drawable.a48);
        }

        viewHolder.company.setText(jobInfoList.get(position).getCompany());
        viewHolder.time.setText(jobInfoList.get(position).getHoldtime());
        viewHolder.address.setText(jobInfoList.get(position).getAddress());
        return convertView;
    }

    static class ViewHolder{
        ImageView icon;
        TextView company;
        TextView time;
        TextView address;
    }

}
