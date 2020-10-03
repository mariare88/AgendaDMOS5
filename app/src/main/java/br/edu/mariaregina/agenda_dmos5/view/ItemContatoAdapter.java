package br.edu.mariaregina.agenda_dmos5.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.edu.mariaregina.agenda_dmos5.R;
import br.edu.mariaregina.agenda_dmos5.model.Contato;








import java.util.List;

import br.edu.mariaregina.agenda_dmos5.R;
import br.edu.mariaregina.agenda_dmos5.model.Contato;

public class ItemContatoAdapter  extends ArrayAdapter {


        private LayoutInflater inflater;
        @Nullable
        private View convertView;

        public ItemContatoAdapter(Context context, List<Contato> contatoList) {
            super(context, R.layout.item_lista_contato, contatoList);
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            this.convertView = convertView;
            final ViewHolder holder;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_lista_contato, null);
                holder = new ViewHolder();
                holder.nomeTextView = convertView.findViewById(R.id.edittext_nome);
                holder.sobrenomeTextView = convertView.findViewById(R.id.edittext_sobrenome);
                holder.telefonefixoTextView = convertView.findViewById(R.id.edittext_telefonefixo);
                holder.telefonecelularTextView = convertView.findViewById(R.id.edittext_telefonecelular);
                // holder.favoritoImageView = convertView.findViewById(R.id.image_favorito);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
           Contato obj = (Contato) getItem(position);
            holder.nomeTextView.setText(obj.getNome());
            holder.sobrenomeTextView.setText(obj.getSobrenomeome());
            holder.telefonefixoTextView.setText(obj.getTelefonefixo());
            holder.telefonecelularTextView.setText(obj.getTelefonecelular());
            //if (obj.isFavorito())
            //  holder.favoritoImageView.setImageResource(R.drawable.ic_favorito);
            //else
            //   holder.favoritoImageView.setImageResource(R.drawable.ic_nao_favorito);
            return convertView;


        }
        private static class ViewHolder {
            public TextView nomeTextView;
            public TextView sobrenomeTextView;
            public TextView telefonefixoTextView;
            public TextView telefonecelularTextView;
            // public ImageView favoritoImageView;
        }
    }

