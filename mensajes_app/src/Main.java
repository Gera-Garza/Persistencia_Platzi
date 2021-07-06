import com.mysql.jdbc.Connection;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do{
            System.out.println("-------");
            System.out.println("Aplicacion de mensajes");
            System.out.println("1.- Crear mensaje");
            System.out.println("2.- Lsitar mensajes");
            System.out.println("3.- Eliminar mensaje");
            System.out.println("4.- Editar mensajes");
            System.out.println("5.- Salir");

            opcion = sc.nextInt();

            switch (opcion){
                case 1:
                    MensajesService.crearMensaje();
                    break;
                case 2:
                    MensajesService.listarMensajes();
                    break;
                case 3:
                    MensajesService.borrarMensaje();
                    break;
                case 4:
                    MensajesService.editarMensaje();
                    break;
                default:
                    break;
            }
        }while (opcion!=5);


    }
}
