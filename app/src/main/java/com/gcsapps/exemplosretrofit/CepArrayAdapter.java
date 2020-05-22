package com.gcsapps.exemplosretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gcsapps.exemplosretrofit.model.CEP;

import java.util.ArrayList;

public class CepArrayAdapter extends ArrayAdapter<CEP> {

      private  final Context context;
      private  final ArrayList<CEP> elementos;

      public CepArrayAdapter(Context context, ArrayList<CEP> elementos){
          super(context, R.layout.listacep,elementos);

          this.context = context;
          this.elementos = elementos;
      }
      @Override
    public View getView(int position, View convertView, ViewGroup parent) {
          LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


          View rowView = inflater.inflate(R.layout.listacep, parent, false);

          TextView cep = rowView.findViewById(R.id.idCepLista);
          TextView endereco = rowView.findViewById(R.id.idEndList);
          TextView bairro = rowView.findViewById(R.id.idBairroList);
          TextView cidade = rowView.findViewById(R.id.idCidadeList);
          TextView estado = rowView.findViewById(R.id.idEstadoList);

             cep.setText(elementos.get(position).getCep());
             endereco.setText(elementos.get(position).getLogradouro());
             bairro.setText(elementos.get(position).getBairro());
             cidade.setText(elementos.get(position).getLocalidade());
             estado.setText(elementos.get(position).getUf());
            return  rowView;
      }
}
