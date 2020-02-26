package Server;
import java.net.*;
import java.io.*;
import javax.json.*;

public class Server {
    ServerSocket server=null;
    Socket socketClient=null;
    int porta= 6789;
    DataInputStream in;
    DataOutputStream out;

    public void comunica() {
       try {

           BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
           JsonObject personObject = Json.createObjectBuilder()
                   .add("45.4112214184", "11.887318333333333")
                   .add("45.411106666666667", "11.887786666666667")
                   .add("45.41143833333334", "11.887943333333332")
                   .add("45.411551666666675", "11.887473333333332")
                   .build();

           JsonWriter writer = Json.createWriter(out);
           writer.writeObject(personObject);
           //chiudo connessione col client
           socketClient.close();
       }
     catch (IOException e) {
        e.printStackTrace();
    }
    }
    public Socket attendi() {
        try {

                System.out.println("[0] - Server attivo");
                //inizializzo il servizio
                server = new ServerSocket(porta);
                System.out.println("[01 - Server in ascolto sulla porta:" + porta);
                //metto in ascolto sulla porta aperta
                socketClient = server.accept();
                //evito accesso multiplo dopo aver accettato la richiesta
                server.close();

                in = new DataInputStream(socketClient.getInputStream());
                out = new DataOutputStream(socketClient.getOutputStream());


        }catch(IOException e){
            e.printStackTrace();
        }

        return socketClient;

    }
}
/*public class Server {

    public Server() {
        try {
            System.out.println("Json inviato");
            ServerSocket ssocket = new ServerSocket(8554);
            Socket socket = ssocket.accept();
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            JsonObject personObject = Json.createObjectBuilder()
                    .add("45.4112214184", "11.887318333333333")
                    .add("45.411106666666667","11.887786666666667")
                    .add("45.41143833333334", "11.887943333333332")
                    .add("45.411551666666675", "11.887473333333332")
                    .build();

            JsonWriter writer = Json.createWriter(out);
            writer.writeObject(personObject);
            writer.close();
            out.close();
            socket.close();
            ssocket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/


