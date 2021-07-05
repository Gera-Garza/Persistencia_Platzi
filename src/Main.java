import com.mysql.jdbc.Connection;

public class Main {
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        try(Connection cnx = (Connection) conexion.get_connection()){

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
