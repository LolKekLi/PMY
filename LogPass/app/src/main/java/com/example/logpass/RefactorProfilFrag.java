package com.example.logpass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RefactorProfilFrag extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_refactor_profil, container, false);

        Button viewById = inflate.findViewById(R.id.saveButton);
        EditText fac = inflate.findViewById(R.id.RfacultetText);
        EditText kur = inflate.findViewById(R.id.RkursText);
        EditText univers = inflate.findViewById(R.id.RunivesritetText);
        EditText middleNAme = inflate.findViewById(R.id.RmiddleNameText);
        EditText sern = inflate.findViewById(R.id.RsernameText);
        EditText name = inflate.findViewById(R.id.RnameText);
        EditText data = inflate.findViewById(R.id.Rdata);

        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (savedInstanceState == null)
                {
                    if (!CheckCorrectData(String.valueOf(data.getText())))
                    {
                        return;
                    }

                    List<Fragment> fragments = getParentFragmentManager().getFragments();

                    FragmentActivity activity = fragments.get(1).getActivity();

                    TextView dat = activity.findViewById(R.id.data);
                    dat.setText(data.getText());

                    TextView nameText = activity.findViewById(R.id.nameText);
                    nameText.setText(name.getText());
                    TextView sername = activity.findViewById(R.id.sernameText);
                    sername.setText(sern.getText());
                    TextView middleName = activity.findViewById(R.id.middleNameText);
                    middleName.setText(middleNAme.getText());
                    TextView facultet = activity.findViewById(R.id.facultetText);
                    facultet.setText(fac.getText());
                    TextView kurs = activity.findViewById(R.id.kursText);
                    kurs.setText(kur.getText());
                    TextView unvirset = activity.findViewById(R.id.univesritetText);
                    unvirset.setText(univers.getText());




                    getParentFragmentManager().beginTransaction().hide(fragments.get(2)).commit();
                    getParentFragmentManager().beginTransaction().show(fragments.get(1)).commit();

                }
            }
        });

        return inflate;
    }

    private boolean CheckCorrectData(String text) {
        char[] chars = text.toCharArray();

        if (chars.length > 10 || chars[2] != '.' || chars[5] != '.')
        {
            Toast toast = Toast.makeText(getActivity(), "Неверный формат", Toast.LENGTH_LONG);
            toast.show();

            return false;
        }

        try {
            Date date1 = new SimpleDateFormat("dd.MM.yyyy").parse(text);
            Date date =  new Date();
            if (date1.after(date))
            {
                Toast toast = Toast.makeText(getActivity(), "<Будущее>", Toast.LENGTH_LONG);
                toast.show();

                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }
}