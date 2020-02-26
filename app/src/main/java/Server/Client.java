package Server;

import java.net.*;
import java.io.*;
import javax.json.*;

public class Client  {
    Socket mioSocket=null;
    int porta= 6789;
    DataInputStream in;
    DataOutputStream out;

    public void comunica(){
        try {

                //Inizializzazione della stream in entrata dalla socket
                BufferedReader inBuffer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
                //creazione stream di Json per leggere dalla stream della socket
                JsonReader reader = Json.createReader(inBuffer);
                // Lettura dell'oggetto JSON della socket
                JsonObject personObject = reader.readObject();
                reader.close();
                inBuffer.close();
                System.out.println("Json ricevuto: " + personObject.toString());
                mioSocket.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket connetti() {
        try {
            System.out.println("[0] - Provo a connettermi al server");
            Socket mioSocket = new Socket(InetAddress.getLocalHost(), porta);
            System.out.println("[1] - Connesso");
            in = new DataInputStream(mioSocket.getInputStream());
            out = new DataOutputStream(mioSocket.getOutputStream());
        }catch(UnknownHostException e){
            System.out.println("Host sconosciuto");
        } catch (Exception e){

            System.out.println("Impossibile stabilire la connessione");
        }
    return mioSocket;
    }
}
/*public class Client  {
        public Client(){
            try {
                System.out.println("CIAOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
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
}*/
