package Server;

import java.net.*;
import java.io.*;
import javax.json.*;
public class Client {
    public void methodClient()
    {
        try
        {
        //Inizializzazione della socket
        Socket socket = new Socket("localhost", 4000);
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
