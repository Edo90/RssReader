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

import java.util.Objects;

public class DeleteDialogFragment extends DialogFragment {

    private static RSSModel model;
    private TextView deleteTextTitle;
    private Button deleteButton;
    private Button cancelButton;
    private RssViewModel rssViewModel;

    public DeleteDialogFragment() {
    }

    public static DeleteDialogFragment newInstance(RSSModel model) {
        DeleteDialogFragment fragment = new DeleteDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable("RSS_MODEL", model);
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
        if (getArguments() != null)
            model = getArguments().getParcelable("RSS_MODEL");
        deleteTextTitle.setText(model != null ? model.getName() : null);
        setListeners();
        rssViewModel = new RssViewModel(Objects.requireNonNull(getActivity()).getApplication());
        super.onViewCreated(view, savedInstanceState);
    }


    private void setListeners() {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rssViewModel.delete(model.getId());
                Toast.makeText(getContext(), "El feed ha sido eliminado con exito", Toast.LENGTH_SHORT).show();
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
