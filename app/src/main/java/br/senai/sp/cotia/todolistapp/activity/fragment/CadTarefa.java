package br.senai.sp.cotia.todolistapp.activity.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;


public class CadTarefa extends Fragment {

        private FragmentCadTarefaBinding binding;
        // variavel para o date picker
        private DatePickerDialog datePicker;
        // variaveis para o dia, mes e ano
        int year, month, day;
        // variavel para pegar a data atual
        private Calendar dataAtual;
        // variavek para a data formatada
        String dataEscolhida = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia o binding
        binding = FragmentCadTarefaBinding.inflate(inflater, container, false);

        // instancia a data atual
        dataAtual = Calendar.getInstance();

        // descobre o dia, mes e ano atuais
        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);

        // instancia o datePicker
        datePicker = new DatePickerDialog(getContext(), (view, ano, mes, dia) -> {
            // cai aqui toda vez que clica no OK no DatePickerDialog

        }, year,month,day);

        // listener do botao de data
        binding.data.setOnClickListener(v -> {
            // abre o date picker
            datePicker.show();


        });



        // retorna a view raiz do binding
        return binding.getRoot();
    }
}