package com.wondersgroup.yngs.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.wondersgroup.yngs.R;
import com.wondersgroup.yngs.activity.QueryActivity;
import com.wondersgroup.yngs.activity.SearchActivity;
import com.wondersgroup.yngs.widget.InfoEditBar;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QueryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QueryFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.query_input)InfoEditBar input;
    @Bind(R.id.query_btn)Button search;

    private static final String ARG_TYPE = "type";

    // TODO: Rename and change types of parameters
    private String type;


    public QueryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param type Parameter 1.
     * @return A new instance of fragment QueryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QueryFragment newInstance(String type) {
        QueryFragment fragment = new QueryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(ARG_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_query, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        input.setIcon(R.drawable.app_input);
        input.setHint(getString(R.string.entName_input_hint));
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(search.equals(v)){
            if(!input.getText().isEmpty()) {
                if ("WQCX".equals(type)||"WQFCCX".equals(type)) {
                    Intent intent = new Intent(getActivity(), QueryActivity.class);
                    intent.putExtra("type", type);
                    HashMap<String, String> body = new HashMap<>();
                    body.put("etpsNameOrRegNo", input.getText());
                    intent.putExtra("body", body);
                    startActivity(intent);
                } else if ("QYCX".equals(type)) {
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("queryString", input.getText());
                    startActivity(intent);
                }
            }else {
                new MaterialDialog.Builder(getContext())
                        .content(getContext().getString(R.string.entName_input_hint))
                        .show();
            }
        }
    }
}
