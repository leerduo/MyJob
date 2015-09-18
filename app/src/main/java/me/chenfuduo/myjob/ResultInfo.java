package me.chenfuduo.myjob;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenfuduo on 2015/9/17.
 */
public class ResultInfo {

    private List<JobInfo> info = new ArrayList<>();

    public ResultInfo(List<JobInfo> info) {
        this.info = info;
    }

    public ResultInfo() {
    }

    public List<JobInfo> getInfo() {
        return info;
    }

    public void setInfo(List<JobInfo> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "info=" + info +
                '}';
    }
}
