package com.example.android.rssreader.pataVoladora;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.rssreader.R;
import com.example.android.rssreader.model.RSSModel;
import com.example.android.rssreader.viewmodel.RssViewModel;

public class DeleteDialogFragment extends DialogFragment {

    private static RssViewModel rssViewModel;
    private TextView deleteTextTitle;
    private Button deleteButton;
    private Button cancelButton;

    public DeleteDialogFragment() {
    }

    public static DeleteDialogFragment newInstance(RSSModel model) {
        DeleteDialogFragment fragment = new DeleteDialogFragment();
        Bundle args = new Bundle();
        args.putString("titleToBeDeleted", model.getName());
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        deleteTextTitle = view.findViewById(R.id.title_to_be_deleted);
        deleteButton = view.findViewById(R.id.bDelete);
        cancelButton = view.findViewById(R.id.bCancel);
        String titleToBeDeleted = getArguments().getString("titleToBeDeleted");
        deleteTextTitle.setText(titleToBeDeleted);
        setListeners();
        super.onViewCreated(view, savedInstanceState);
    }


    private void setListeners() {

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Deleted Clicked", Toast.LENGTH_SHORT).show();
                closeFragment();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment();
            }
        });
    }

    private void closeFragment() {
        this.dismiss();
    }

}
