package br.senai.sp.cotia.todolistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    //lista de tarefa
    private List<Tarefa> tarefas;
    // variavel para o Context
    private Context context;


    // construtor pra receber os valores
    public TarefaAdapter(List<Tarefa> lista, Context contexto) {
        this.tarefas = lista;
        this.context = contexto;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // infla o layout do adapter
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_tarefas, parent, false);
        // retorna um novo viewHolder com a view
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        // obtem a tarefa pela position
        Tarefa t = tarefas.get(position);
        // exibe o titulo da tarefa no text view
        holder.tvTitulo.setText(t.getTitulo());
        // exibe a descricao da tarefa no text view
        holder.tvDescricao.setText(t.getDescricao());
        // se estiver concluida
        if(t.isConcluda()) {
            holder.tvStatus.setText(R.string.concluida);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.green));
        }else if() {

        }else {
            holder.tvStatus.setText(R.string.aberta);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.yellow));
        }

        // formata a data de long para String
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvData.setText(fmt.format(t.getDataPrevista()));

    }

    // retorna a quantidade de elementos a serem exibidos na lista
    @Override
    public int getItemCount() {
        if(tarefas != null) {
            return tarefas.size();
        }
        return 0;
    }

    // classe ViewHolder para mapear os componentes do xml
       class TarefaViewHolder extends RecyclerView.ViewHolder {
            // variaveis para acessar os componentes do xml
            TextView tvTitulo, tvData, tvStatus, tvDescricao;
           public TarefaViewHolder(View view) {
               // chama o construtor da superclasse
               super(view);
               // passar para as variaveis, os componentes do xml
               tvTitulo = view.findViewById(R.id.tv_titulo);
               tvData = view.findViewById(R.id.data_taf);
               tvStatus = view.findViewById(R.id.status_taf);
               tvDescricao = view.findViewById(R.id.tv_descricao);
           }
       }
}
