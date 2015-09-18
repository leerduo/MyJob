package me.chenfuduo.myjob;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenfuduo on 2015/9/17.
 */
public class SchoolFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    private String URL;

   // private String idStr;

   // private String uRLDetail = "http://mobile.haitou.cc/client3/article?id=" + idStr +  "&source=xjh&ver=1.0";

    private static final String TAG = SchoolFragment.class.getSimpleName();

    private List<JobInfo> jobInfoList;

    private JobAdapter adapter;
    
    private ListView listView;

    public static SchoolFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        SchoolFragment schoolFragment = new SchoolFragment();
        schoolFragment.setArguments(args);
        return schoolFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        Log.e(TAG, "onCreate ");
    }

    private void initData(final int mPage) {
        if (mPage == 1){
            //科大
            URL = "http://mobile.haitou.cc/client3/info?ver=1.0&school=47&part=hf&source=xjh&page=1";
        }else if (mPage == 2){
            //工大
            URL = "http://mobile.haitou.cc/client3/info?ver=1.0&school=49&part=hf&source=xjh&page=1";
        }else if (mPage == 3){
            //安大
            URL = "http://mobile.haitou.cc/client3/info?ver=1.0&school=48&part=hf&source=xjh&page=1";

        }

        HttpUtils.sendHttpRequest(URL, new HttpCallbackListener() {
            @Override
            public void onSuccess(String response) {
                Log.e(TAG, "onSuccess " + response);

                ResultInfo resultInfo = JSON.parseObject(response, ResultInfo.class);
                Log.e(TAG, "onSuccess " + resultInfo.toString());

                jobInfoList = resultInfo.getInfo();
                Log.e(TAG, "onSuccess " + jobInfoList.toString());

                for(int i = 0;i<jobInfoList.size();i++){
                    String company = jobInfoList.get(i).getCompany();
                    String holdtime = jobInfoList.get(i).getHoldtime();
                    String address = jobInfoList.get(i).getAddress();

                    int id = jobInfoList.get(i).getId();

                   // idStr = String.valueOf(id);


                    jobInfoList.get(i).setCompany(company);
                    jobInfoList.get(i).setHoldtime(holdtime);
                    jobInfoList.get(i).setAddress(address);

                    jobInfoList.get(i).setId(id);


                    String name = jobInfoList.get(i).getName();
                    jobInfoList.get(i).setName(name);


                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new JobAdapter(jobInfoList,getActivity());

                        listView.setAdapter(adapter);
                    }
                });

            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView ");
        View view = inflater.inflate(R.layout.fragment_school, container, false);
        listView = (ListView) view;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int id1 = jobInfoList.get(position).getId();

                String idStr = String.valueOf(id1);

                String uRLDetail = "http://mobile.haitou.cc/client3/article?id=" + idStr +  "&source=xjh&ver=1.0";

                Intent intent = new Intent(getActivity(),DetailActivity.class);
                intent.putExtra("URL",uRLDetail);
                startActivity(intent);
            }
        });


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        return listView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated ");



    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume ");
        jobInfoList = new ArrayList<>();
        initData(mPage);
    }
}
