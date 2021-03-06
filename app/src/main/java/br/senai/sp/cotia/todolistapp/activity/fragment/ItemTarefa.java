package br.senai.sp.cotia.todolistapp.activity.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentItemTarefaBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;


public class ItemTarefa extends Fragment {

        private FragmentItemTarefaBinding binding;
        private Tarefa tarefa;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia o binding
        binding = FragmentItemTarefaBinding.inflate(inflater, container, false);

        if(getArguments() != null){
            //recupero a tarefa
            tarefa = (Tarefa) getArguments().getSerializable("tarefa");
            binding.tituloSub.setText(tarefa.getTitulo());
        }

        // retorna a view raiz do binding
        return binding.getRoot();
    }
}