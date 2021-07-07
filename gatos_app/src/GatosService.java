import com.google.gson.Gson;
import com.squareup.okhttp.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class GatosService {
    public static void verGatos() throws IOException {
        //1. vamos a traer los gatos de la API
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/images/search")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String elJson = response.body().string();

        //cortar los corchetes del json

        elJson = elJson.substring(1,elJson.length());
        elJson = elJson.substring(0,elJson.length()-1);

        //crear un objeto de clase gson
        Gson gson = new Gson();
        Gatos gatos = gson.fromJson(elJson,Gatos.class);

        //redimencionar en caso de necesitar
        Image image = null;
        try {
            URL url = new URL(gatos.getUrl());
            image = ImageIO.read(url);

            ImageIcon fondoGato = new ImageIcon(image);

            if(fondoGato.getIconWidth() > 800){
                //redimencionamso
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800,600, Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
            }

            String menu = "Opciones: \n"+
                    "1.- Ver otra iamgen\n"+
                    "2.- Favorito \n"+
                    "3.- Volver \n";
            String botones[] = {"Ver otra imagen","Favorito","Volver"};
            String id_gato = gatos.getId();
            String opcion = (String) JOptionPane.showInputDialog(null,menu,
                    id_gato,JOptionPane.INFORMATION_MESSAGE,fondoGato,botones,botones[0]);

            int seleccion = -1;

            //validamos que opcion selecciona el usuario
            for (int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i]))
                    seleccion = i;
            }

            switch (seleccion){
                case 0:
                    verGatos();
                    break;
                case 1:
                    favoritoGato(gatos);
                    break;
                default:
                    break;
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void favoritoGato(Gatos gato){
        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n    \"image_id\": \""+gato.getId()+"\"\r\n}");
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gato.apikey)
                    .build();
            Response response = client.newCall(request).execute();
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
