package br.senai.sp.cotia.todolistapp.activity.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentDetalheTarefaBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;


public class DetalheTarefa extends Fragment {

        private FragmentDetalheTarefaBinding binding;
        // variavel para a tarefa ser detalhada
        private Tarefa tarefa;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia o binding
        binding = FragmentDetalheTarefaBinding.inflate(inflater, container, false);

        // verifica se existe algo sendo passado no bundle
        if(getArguments() != null) {
            // recupero a tarefa
            tarefa = (Tarefa) getArguments().getSerializable("tarefa");
            // popula os campos com as informações da tarefa
            binding.tvTitle.setText(tarefa.getTitulo());
            binding.tvDesc.setText(tarefa.getDescricao());
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            binding.dataTarefa.setText(fmt.format(tarefa.getDataPrevista()));
            // se a tarefa estiver concluida
            if(tarefa.isConcluda()) {
                binding.tvTitle.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }

        binding.btnVizuTarefa.setOnClickListener(v->{
            Bundle bundle = new Bundle();
            bundle.putSerializable("tarefa", tarefa);
            NavHostFragment.findNavController(DetalheTarefa.this).navigate(R.id.action_detalheTarefa_to_itemTarefa,bundle);
        });

        // retorna a view raiz do binding
        return binding.getRoot();
    }
}