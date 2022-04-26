package br.senai.sp.cotia.todolistapp.activity.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.database.AppDatabase;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;


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
        // variavel para acessar a database
        AppDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // instancia a database
        database = AppDatabase.getDatabase(getActivity());

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
            // passa para as variaveis globais
            year = ano;
            month = mes;
            day = dia;
            // formata a String da dataEscolhida
            dataEscolhida = String.format("%02d/%02d/%04d", day, month+1, year);
            // jogar a string no botao
            binding.data.setText(dataEscolhida);
        }, year,month,day);

        // listener do botao de data
        binding.data.setOnClickListener(v -> {
            // abre o date picker
            datePicker.show();


        });

        // listener do botao salvar

        binding.salvar.setOnClickListener(v ->{
            // validar os campos
            if(binding.titulo.getText().toString().isEmpty()) {
                Toast.makeText(getContext(),R.string.erroTitle, Toast.LENGTH_LONG).show();
                binding.titulo.requestFocus();
            }else if(binding.descricao.getText().toString().isEmpty()) {
                Toast.makeText(getContext(),R.string.erroDesc, Toast.LENGTH_LONG).show();
                binding.descricao.requestFocus();
            }else if(dataEscolhida.isEmpty()) {
                Toast.makeText(getContext(),R.string.erroData, Toast.LENGTH_LONG).show();
            }else {
                // criar um objeto tarefa
                Tarefa tarefa = new Tarefa();
                // popular a tarefa
                tarefa.setTitulo(binding.titulo.getText().toString());
                tarefa.setDescricao(binding.descricao.getText().toString());
                // cria um calendar e popula com a data que foi selecionada
                Calendar dataRealizacao = Calendar.getInstance();
                dataRealizacao.set(year,month,day);
                // passar para a tarefa os milissegundos da data
                tarefa.setDataPrevista(dataRealizacao.getTimeInMillis());
                // criar um Calendar para a data atual
                Calendar dataAtual = Calendar.getInstance();
                tarefa.setDataCriacao(dataAtual.getTimeInMillis());

                // salvar a tarefa no BD
                new InsertTarefa().execute(tarefa);

            }
        });



        // retorna a view raiz do binding
        return binding.getRoot();
    }

    // classe para a Task de inserir tarefa
    private class InsertTarefa extends AsyncTask<Tarefa, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.w("PASSOU", "No onPreExecute");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.w("PASSOU", "No onProgressUpdate");
        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            Log.w("PASSOU", "No doInBackground");
            // extrair a tarefa do vetor
            Tarefa t = tarefas[0];
            try {
                // tenta inserir
                database.getTarefaDao().insert(t);
                // retorna ok caso tenha passado sem erro
                return "ok";
            }catch (Exception e) {
                e.printStackTrace();
                // retorna a mensagem de erro caso tenha dado erro
                return e.getMessage();
            }


        }

        @Override
        protected void onPostExecute(String msg) {
            if(msg.equals("ok")) {
                Log.w("PRONTO", "Funcionou");
            }else {
                Log.w("PRONTO", msg);
                Toast.makeText(getContext(),"DEU RUIM"+msg, Toast.LENGTH_SHORT).show();
            }

        }
    }
}