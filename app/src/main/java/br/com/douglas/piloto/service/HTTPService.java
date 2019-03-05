package br.com.douglas.piloto.service;


import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import br.com.douglas.piloto.model.Car;

public class HTTPService extends AsyncTask<Void,Void, Car> {

    private final String car;

    public HTTPService(String car) {
        this.car = car;
    }

    @Override
    protected Car doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        try {
            URL url = new URL("http://192.168.0.17:8080/modelo/?modelo=" + this.car );//+ "/json/");
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

        System.out.println(resposta.toString());

        Gson gson = new Gson();

        //Car car = new Car(); //new Gson().fromJson(resposta.toString(), Car.class);

        Car car = gson.fromJson(resposta.toString(),Car.class);


        return car;
    }

}
