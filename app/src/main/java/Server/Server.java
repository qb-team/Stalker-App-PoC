package Server;
import java.net.*;
import java.io.*;
import javax.json.*;

public class Server {

    public void myMethod() {
        try {
            ServerSocket ssocket = new ServerSocket(4000);
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
            System.out.println("Json inviato");
            socket.close();
            ssocket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



