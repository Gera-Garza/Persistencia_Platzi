import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException{
        int opcion_menu = -1;
        String[] botones = {" 1. ver gatos", "2. ver favoritos", "3. salir"};
        do{
            //menu principal
            String opcion = (String) JOptionPane.showInputDialog(null, "Gatitos java", "Menu principal", JOptionPane.INFORMATION_MESSAGE,
                    null, botones,botones[0]);

            //validamos que opcion selecciona el usuario
            for(int i=0;i<botones.length;i++){
                if(opcion.equals(botones[i])){
                    opcion_menu = i;
                }
            }
            switch(opcion_menu){
                case 0:
                    GatosService.verGatos();
                    break;
                case 1:
                    Gatos gato = new Gatos();
                    GatosService.verFavorito(gato.getApikey());
                default:
                    break;
            }
        }while(opcion_menu != 1);
    }
}
