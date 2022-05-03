package br.senai.sp.cotia.todolistapp.activity.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.adapter.TarefaAdapter;
import br.senai.sp.cotia.todolistapp.database.AppDatabase;
import br.senai.sp.cotia.todolistapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;


public class PrincipalFragment extends Fragment {

        private FragmentPrincipalBinding binding;
        // variavel para a database
        private AppDatabase database;
        // variavel para o Adapter
        private TarefaAdapter adapter;
        // variavel pra lista
        private List<Tarefa> tarefas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia o binding
        binding = FragmentPrincipalBinding.inflate(inflater, container, false);

        // clique no botao de adicionar tarefa
        binding.btnAddTarefa.setOnClickListener(view -> {
            NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_cadTarefa);
        });

        // retorna a view raiz do binding
        return binding.getRoot();
    }
}