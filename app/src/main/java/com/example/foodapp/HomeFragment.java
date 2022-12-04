package com.example.foodapp;

import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText changeColor;
    private EditText searchbar;
    private BottomSheetDialog mBottomSheetDialog;

    private ImageButton ImageButton;
    private TextView Text;
    private FragmentManager fm;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        searchbar = (EditText) fragmentView.findViewById(R.id.editTextTextPersonName);
        // Inflate the layout for this fragment
        ImageButton= (ImageButton) fragmentView.findViewById(R.id.c);
        mBottomSheetDialog=new BottomSheetDialog(getActivity());

        //Text=(TextView) fragmentView.getRootView().findViewById(R.id.text1);

        //TextView text = (TextView) fragmentView.findViewById(R.id.text1);



        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View sheetView = getActivity().getLayoutInflater().inflate(R.layout.fragment_bottom_sheet, null);
                mBottomSheetDialog.setContentView(sheetView);
                mBottomSheetDialog.show();
               // Text.setText("This is a test for the button");

                //View sheetView = getActivity().getLayoutInflater().inflate(R.layout.fragment_food, null);
            }
        });

        return fragmentView;
        //return inflater.inflate(R.layout.fragment_home, container, false);
    }



}