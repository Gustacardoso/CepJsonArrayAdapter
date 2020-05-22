package com.gcsapps.exemplosretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gcsapps.exemplosretrofit.api.CEPService;
import com.gcsapps.exemplosretrofit.model.CEP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText editTextcidade;
    EditText editTextend;
    ListView listView;
    Button button;
    Retrofit retrofit;
    ArrayList<CEP> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextcidade = findViewById(R.id.idcidade);
        editTextend = findViewById(R.id.idend);
        listView = findViewById(R.id.listId);

        button = findViewById(R.id.idbt);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            recuperarCEPRetrofit();
        }
    });
    }
    private ArrayList<CEP> cepArrayList(){
        CEP c = new CEP();
        list.add(c);
     return list;
    }
    private void recuperarCEPRetrofit() {
        final String cidade = editTextcidade.getText().toString();
        String endereco = editTextend.getText().toString();
        CEPService cepService = retrofit.create( CEPService.class);
        Call<List<CEP>> call = cepService.recuperarCEP(cidade, endereco);

       call.enqueue(new Callback<List<CEP>>() {
            @Override
            public void onResponse(Call<List<CEP>> call, Response<List<CEP>> response) {
                if (response.isSuccessful()){
                    list = (ArrayList<CEP>) response.body();
              /*ArrayAdapter<CEP> adapter = new ArrayAdapter<CEP>(
                      getApplicationContext(),
                      android.R.layout.simple_list_item_1,
                      list
              );
              listView.setAdapter(adapter);*/

                         //criar adapter para a lista
                   ArrayAdapter<CEP> adapter = new CepArrayAdapter(getApplicationContext(),cepArrayList());

                       listView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<CEP>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"digite algma coisa",Toast.LENGTH_LONG).show();
            }
        });
    }
}
