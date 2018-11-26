package abiodun.ojo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AbiDown extends Fragment implements AbHome.OnFragmentInteractionListener{

    private static final String TAG = "Error" ;
   // private OnFragmentInteractionListener mListener;

    public AbiDown() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.abi_down, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO:
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + getString(R.string.mustImpFragment));
          //  Log.e(TAG,context.toString());
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
    //    mListener = null;
    }

    @Override
    public void onFragmentInteraction(String name, String desc) {

    }


/*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    */
}
