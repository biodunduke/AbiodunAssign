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
import android.widget.Toast;
import android.widget.ToggleButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OjoSet.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class OjoSet extends Fragment {
    Button prefSave;
    RadioGroup rdGrp;
    RadioButton rdBtn;
    int selectedRadioId;
    String fragColor;
    ToggleButton toggleButton;
    Switch portraitSwitch;
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
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        rdGrp = view.findViewById(R.id.abiodunRadioGrp);
        prefSave = view.findViewById(R.id.abiodunSubmitButton);
        toggleButton = view.findViewById(R.id.abiodunToggleButton);
        portraitSwitch = view.findViewById(R.id.abiodunSwitchOrientation);

        prefSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedRadioId = rdGrp.getCheckedRadioButtonId();
                rdBtn = getActivity().findViewById(selectedRadioId);//Used getActivity instead  of view to deal with error
                try {
                    fragColor = rdBtn.getText().toString();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), getString(R.string.chooseColor), Toast.LENGTH_LONG).show();
                }
                String toggleValue = toggleButton.getText().toString();
                String fontSize = spinner.getSelectedItem().toString();
                Boolean switchValue = portraitSwitch.isChecked() ? true : false;
                SharedPreferences sharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                try {
                    editor.putString("color", fragColor);
                    editor.putString("format", toggleValue);
                    editor.putString("fontSize", fontSize);
                    editor.putBoolean("portrait", switchValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                editor.commit();
                String added = fragColor + ", " + toggleValue + ", " + fontSize + ", " + String.valueOf(switchValue);
                Toast.makeText(getActivity(), added, Toast.LENGTH_LONG).show();
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
        } else {
            //     throw new RuntimeException(context.toString() + getString(R.string.mustImpFragment));
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
