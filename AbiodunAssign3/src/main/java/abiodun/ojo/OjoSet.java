package abiodun.ojo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

public class OjoSet extends Fragment {
    /*
Abiodun Ojo
N01178447
*/
    Button prefSave;
    RadioGroup rdGrp, rdGrpClock;
    RadioButton rdBtn, rdBtnClock;
    int selectedRadioId, selectedRadioIdClock;
    String fragColor = "", clockFormat = "", fontSize = "";
    Switch portraitSwitch;
    Boolean switchValue;
    private OnFragmentInteractionListener mListener;

    public OjoSet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ojo_set, container, false);

        final Spinner spinner = view.findViewById(R.id.abiodunSpinnerFontSize);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.fonts_size, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        //Edit start
        final SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        rdGrp = view.findViewById(R.id.abiodunRadioGrp);
        rdGrpClock = view.findViewById(R.id.abiodunRadioGrpClock);
        prefSave = view.findViewById(R.id.abiodunSubmitButton);
        portraitSwitch = view.findViewById(R.id.abiodunSwitchOrientation);


        prefSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedRadioId = rdGrp.getCheckedRadioButtonId();
                selectedRadioIdClock = rdGrpClock.getCheckedRadioButtonId();
                rdBtn = getActivity().findViewById(selectedRadioId);//Used getActivity instead  of view to deal with error
                rdBtnClock = getActivity().findViewById(selectedRadioIdClock);
                fontSize = spinner.getSelectedItem().toString();
                switchValue = portraitSwitch.isChecked() ? true : false;
                try {
                    fragColor = rdBtn.getText().toString();
                    clockFormat = rdBtnClock.getText().toString();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    //    Toast.makeText(getActivity(), getString(R.string.chooseColor), Toast.LENGTH_LONG).show();
                }

                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                try {
                    editor.putString("color", fragColor);
                    editor.putString("format", clockFormat);
                    editor.putString("fontSize", fontSize);
                    editor.putBoolean("portrait", switchValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                editor.commit();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onFragmentInteraction(String name, String desc) {
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String name, String desc);
    }
}
