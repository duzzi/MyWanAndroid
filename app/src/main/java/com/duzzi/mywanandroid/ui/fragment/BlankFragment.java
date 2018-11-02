package com.duzzi.mywanandroid.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.core.constant.Constants;

public class BlankFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String TAG = "BlankFragment";
    private TextView mTextView;

    public BlankFragment() {
    }

    public static BlankFragment newInstance(String tag) {
        BlankFragment blankFragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, tag);
        blankFragment.setArguments(bundle);
        return blankFragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, getFragmentTag() + "setUserVisibleHint: " + isVisibleToUser);
    }

    private String getFragmentTag() {
        Bundle arguments = getArguments();
        String string = null;
        if (arguments != null) {
            string = arguments.getString(ARG_PARAM1);
        }
        string = string + "-->";
        return string;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG, getFragmentTag() + "onHiddenChanged: hidden: " + hidden);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, getFragmentTag() + "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, getFragmentTag() + "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, getFragmentTag() + "onCreateView: ");
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        String string = null;
        if (arguments != null) {
            string = arguments.getString(ARG_PARAM1);
        }
        mTextView = (TextView) view.findViewById(R.id.tv_blank);
        mTextView.setText(String.valueOf(string));
        Log.d(TAG, getFragmentTag() + "onViewCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, getFragmentTag() + "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, getFragmentTag() + "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, getFragmentTag() + "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, getFragmentTag() + "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, getFragmentTag() + "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, getFragmentTag() + "onDestroy: ");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, getFragmentTag() + "onDetach: ");
    }

}
