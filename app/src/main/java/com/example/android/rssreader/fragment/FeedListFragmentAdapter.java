package com.example.android.rssreader.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.rssreader.R;
import com.example.android.rssreader.model.NewsFeedModel;

import java.util.List;

public class FeedListFragmentAdapter extends ArrayAdapter<NewsFeedModel> {
    public FeedListFragmentAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private final LayoutInflater mInflater;

    public void setData(List<NewsFeedModel> data){
        clear();
        if(data != null)
            for(NewsFeedModel feedModel: data){
                add(feedModel);
            }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if(convertView == null){
            view = mInflater.inflate(R.layout.feed_list_fragment,parent,false);
        } else {
            view = convertView;
        }

        NewsFeedModel newsFeedModel = getItem(position);
        ((TextView)view.findViewById(R.id.tvLink)).setText(newsFeedModel.getLink());
        ((TextView)view.findViewById(R.id.titleText)).setText(newsFeedModel.getTitle());
        ((TextView)view.findViewById(R.id.descriptionText)).setText(newsFeedModel.getDescription());

        return view;
    }
}
