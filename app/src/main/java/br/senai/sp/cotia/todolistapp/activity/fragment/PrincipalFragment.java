package br.senai.sp.cotia.todolistapp.activity.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;

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

        // instancia a database
        database = AppDatabase.getDatabase(getActivity());

        // define o layout manager do recyclerview
        binding.recyclerTarefas.setLayoutManager(new LinearLayoutManager(getContext()));

        // executa a async task
        new ReadTarefa().execute();

        // retorna a view raiz do binding
        return binding.getRoot();
    }

    class ReadTarefa extends AsyncTask<Void, Void, List<Tarefa>> {

        @Override
        protected List<Tarefa> doInBackground(Void... voids) {
            // guarda na variavel tarefas, as tarefas do banco de dados
            tarefas = database.getTarefaDao().getAll();
            // retorna a lista de tarefas
            return tarefas;
        }

        @Override
        protected void onPostExecute(List<Tarefa> tarefas) {
            // instancia o adapter
            adapter = new TarefaAdapter(tarefas,getActivity(), listenerTarefa);
            // aplica o adapter no recyclerview
            binding.recyclerTarefas.setAdapter(adapter);
        }
    }

    // implementacao da interface onTarefaClickListener
    private TarefaAdapter.OnTarefaClickListener listenerTarefa = (view, tarefa) -> {
      // variavel para transportar a tarefa (pacote)
      Bundle bundle = new Bundle();
      // "pendurar" a tarefa no pacote
      bundle.putSerializable("tarefa", tarefa);
      // navega para o proximo fragment enviando o bundle
      NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_detalheTarefa, bundle);


    };

}