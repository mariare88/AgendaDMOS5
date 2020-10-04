package br.edu.mariaregina.agenda_dmos5.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.mariaregina.agenda_dmos5.R;
import br.edu.mariaregina.agenda_dmos5.data.ContatoDAO;
import br.edu.mariaregina.agenda_dmos5.model.Contato;


public class ItemContatoAdapter  extends RecyclerView.Adapter<ItemContatoAdapter.ContatoViewHolder> {

    private ContatoDAO mContatoDAO;
    private List<Contato> mContatoList;
    private static RecyclerItemClickListener mClickListener;

    public ItemContatoAdapter(List<Contato> mContatoList, Context context) {
        this.mContatoList = mContatoList;
        mContatoDAO = new ContatoDAO(context);
    }

    public void setClickListener(RecyclerItemClickListener clickListener) {
        ItemContatoAdapter.mClickListener = clickListener;
    }

    @NonNull
    @Override
    public ContatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_contato, parent, false);
        ContatoViewHolder viewHolder = new ContatoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoViewHolder holder, final int position) {
        holder.nomeTextView.setText(mContatoList.get(position).getNome());
      // holder.sobrenomeTextView.setText(mContatoList.get(position).getSobrenomeome());
     //   holder.telefonefixoTextView.setText(mContatoList.get(position).getTelefonefixo());
      //  holder.telefonecelularTextView.setText(mContatoList.get(position).getTelefonecelular());
        if(mContatoList.get(position).isFavorito())
            holder.favoritoImageView.setImageResource(R.drawable.ic_favorito);
        else
            holder.favoritoImageView.setImageResource(R.drawable.ic_nao_favorito);

        /*
        Aqui tratamos o clique na imnagem, observe que o ImageView é um elemento do item do RecyclerView,
        assim, tratamos o onClickListener normalmente.
         */
        holder.favoritoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCoracaoClique(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mContatoList.size();
    }

    /*
            Configuramos se o objeto é ou não favorito
             */
    private void onCoracaoClique(int position) {

        if (mContatoList.get(position).isFavorito())
            mContatoList.get(position).undoFavorite();
        else
            mContatoList.get(position).doFavotite();
        mContatoDAO.adiciona(mContatoList.get(position));
        notifyDataSetChanged();
    }

    public class ContatoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Os atributos disponíveis no layout
        public TextView nomeTextView;
        public TextView sobrenomeTextView;
        public TextView telefonefixoTextView;
        public TextView telefonecelularTextView;
        public ImageView favoritoImageView;


        /*
        O Construtor recupera os elementos de layout
         */
        public ContatoViewHolder(@NonNull View itemView) {
            super(itemView);
           nomeTextView = itemView.findViewById(R.id.text_nome);
           sobrenomeTextView = itemView.findViewById(R.id.edittext_sobrenome);
            telefonefixoTextView = itemView.findViewById(R.id.edittext_telefonefixo);
            telefonecelularTextView = itemView.findViewById(R.id.edittext_telefonecelular);
            favoritoImageView = itemView.findViewById(R.id.image_favorito);
            itemView.setOnClickListener(this);
        }

        /*
        Aqui tratamos o clique no item e não em elementos do item.
         */
        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(getAdapterPosition());
        }

    }
}


