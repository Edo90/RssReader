package com.example.android.rssreader;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.rssreader.model.RSSModel;
import com.example.android.rssreader.viewmodel.RssViewModel;

import java.util.List;

public class rvRssAdapter extends RecyclerView.Adapter<rvRssAdapter.rssViewHolder>{

    private final LayoutInflater layoutInflater;
    private List<RSSModel> rssModelList;
    private Context mainContext;
    private static RssViewModel rssViewModel;


    rvRssAdapter(Context context, RssViewModel rssViewModel){
        mainContext = context;
        rvRssAdapter.rssViewModel = rssViewModel;
        layoutInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public rssViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = layoutInflater.inflate(R.layout.recycler_view_rss_item,viewGroup,false);
        return new rssViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull rssViewHolder rssViewHolder, int i) {
        final int position = i;
        setListener(rssViewHolder, position);
        if(rssModelList != null){
            RSSModel current = rssModelList.get(i);
            rssViewHolder.rssTitleView.setText(current.getName());
        } else{
            rssViewHolder.rssTitleView.setText("Sin Titulo");
        }
    }

    private void setListener(@NonNull final rssViewHolder rssViewHolder, final int position) {
        rssViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainContext,NewsActivity.class);
                intent.putExtra("link",rssModelList.get(position).getLink());
                mainContext.startActivity(intent);
            }
        });

        rssViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final int id = position + 1;

 //               FragmentManager fragmentManager = ((MainActivity)mainContext).getSupportFragmentManager();

//                DialogFragment dialogFragment = new DialogFragment();
//                dialogFragment.setCancelable(true);
//                dialogFragment.show(fragmentManager,"Confirmar");

                //rssViewModel.delete(id);
                String title = rssViewHolder.rssTitleView.getText().toString();
                showDialog(title,id);
                return false;
            }
        });
    }

    private void showDialog(String titleName, int id) {
        FragmentManager fragmentManager = ((AppCompatActivity) mainContext).getSupportFragmentManager();
        ConfirmDialogMessage confirmDialogMessage = new ConfirmDialogMessage().newInstance(titleName,id);
        confirmDialogMessage.show(fragmentManager,"fragment_alert");
    }

    void insertRss(List<RSSModel> model){
        rssModelList = model;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(rssModelList != null){
            return rssModelList.size();
        }
        else {
            return 0;
        }
    }

    class rssViewHolder extends RecyclerView.ViewHolder{
        private final TextView rssTitleView;

        private rssViewHolder(View itemView){
            super(itemView);
            rssTitleView = itemView.findViewById(R.id.rssTitle);
        }
    }

    public static class ConfirmDialogMessage extends DialogFragment{
        public ConfirmDialogMessage() {
        }

        public ConfirmDialogMessage newInstance(String titleName, int newsId){
            ConfirmDialogMessage fragment = new ConfirmDialogMessage();
            Bundle args = new Bundle();
            args.putString("titleName",titleName);
            args.putInt("newsId",newsId);
            fragment.setArguments(args);
            return fragment;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            String titleName = getArguments().getString("titleName");
            final int newsId = getArguments().getInt("newsId");

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setTitle(R.string.delete_item_confirm_text);
            alertDialogBuilder.setMessage(titleName);
            alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                 rssViewModel.delete(newsId);
                }
            });
            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            return alertDialogBuilder.create();
        }
    }

}
