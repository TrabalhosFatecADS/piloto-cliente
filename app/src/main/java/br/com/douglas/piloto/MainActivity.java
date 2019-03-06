package br.com.douglas.piloto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.douglas.piloto.model.Car;
import br.com.douglas.piloto.service.HTTPMarca;
import br.com.douglas.piloto.service.HTTPService;
import br.com.douglas.piloto.service.HTTPTodos;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etCep = findViewById(R.id.etMain_cep);
        final TextView tvResposta = findViewById(R.id.etMain_resposta);

        Button botaoModelo = findViewById(R.id.btnMain_buscarCep);
        botaoModelo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(etCep.getText().toString().length() > 0 && !etCep.getText().toString().equals("")){
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

        Button botaoMarca = findViewById(R.id.btnMain_buscarMarca);
        botaoMarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HTTPMarca service = new HTTPMarca(etCep.getText().toString());

                String view = "";
                try {
                    List<Car> retorno = service.execute().get();
                    for(Car car : retorno){
                        view = view + car.getModelo().toString() + ", ";
                        System.out.println(car.toString());
                    }
                    tvResposta.setText(view);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.i("casca", etCep.getText().toString());
            }
        });

        Button botaoTodos = findViewById(R.id.btnMain_buscarTodos);
        botaoTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HTTPTodos service = new HTTPTodos();

                String view = "";
                try {
                    List<Car> retorno = service.execute().get();
                    for(Car car : retorno){
                        view = view + car.getModelo().toString() + ", ";
                        System.out.println(car.toString());
                    }
                    tvResposta.setText(view);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.i("casca", etCep.getText().toString());
            }
        });

    }
}