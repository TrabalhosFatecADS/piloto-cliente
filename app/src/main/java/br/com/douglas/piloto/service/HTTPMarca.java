package br.com.douglas.piloto.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import br.com.douglas.piloto.model.Car;

public class HTTPMarca extends AsyncTask<Void,Void, List<Car>> {

    private final String marca;

    public HTTPMarca(String marca) {
        this.marca = marca;
    }

    @Override
    protected List<Car> doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        try {
            URL url = new URL("http://192.168.0.17:8080/marca/?marca=" + this.marca );//+ "/json/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json");
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while(scanner.hasNext()){
                resposta.append(scanner.next());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        List<Car> cars = Arrays.asList(gson.fromJson(resposta.toString(), Car[].class));

        return cars;
    }
}
