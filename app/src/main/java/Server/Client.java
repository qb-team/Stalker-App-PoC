package Server;

import java.net.*;
import java.io.*;
import javax.json.*;
public class Client extends Thread {
    @Override
    public void run(){
        try
        {
        //Inizializzazione della socket
        Socket socket = new Socket("127.0.0.1", 8554);
        //Inizializzazione della stream in entrata dalla socket
        BufferedReader in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
        //creazione stream di Json per leggere dalla stream della socket
        JsonReader reader = Json.createReader(in);
        // Lettura dell'oggetto JSON della socket
        JsonObject personObject = reader.readObject();
        reader.close();
        in.close();
        System.out.println("Json ricevuto: " + personObject.toString());
        socket.close();
    }
    catch (IOException e) {
        e.printStackTrace();
    }
}
}
