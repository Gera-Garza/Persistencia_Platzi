import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int opcion_menu = -1;
        String botones[] = {"1.- Ver gatos","2.- Salir"};
        do{

            //Menu principal
            String opcion = (String) JOptionPane.showInputDialog(null,"Gatitos Java","Menu princial",
                    JOptionPane.INFORMATION_MESSAGE,null,botones,botones[0]);

           //validamos que opcion selecciona el usuario
            for (int i = 0; i < botones.length; i++) {
                if (opcion.equals(botones[i]))
                    opcion_menu = i;
            }

            switch (opcion_menu){
                case 1:
                    GatosService.verGatos();
                    break;
                default:
                    break;
            }
        }while (opcion_menu !=2);
    }
}
