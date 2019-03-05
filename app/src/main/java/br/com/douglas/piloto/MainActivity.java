package br.com.douglas.piloto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import br.com.douglas.piloto.model.Car;
import br.com.douglas.piloto.service.HTTPService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etCep = findViewById(R.id.etMain_cep);
        final TextView tvResposta = findViewById(R.id.etMain_resposta);

        Button botao = findViewById(R.id.btnMain_buscarCep);
        botao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Log.i("[MainActivity] =>", "Buscando CEP!");
                if(etCep.getText().toString().length() > 0 &&
                        !etCep.getText().toString().equals("")){

                    HTTPService service = new HTTPService(etCep.getText().toString());
                    try {
                        Car retorno = service.execute().get();
                        tvResposta.setText(retorno.toString());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i("casca", etCep.getText().toString());
                }
            }
        });
    }
}