
package Cliente;

public class Messenger {
    
    public static ConectorCliente servidor,cliente;
  
    
    public static void main (String[] args){
        VCliente cliente = new VCliente();
        cliente.show();
    }
    
    public static void initCliente(String ipCliente, String bd, String usuario, String clave, String ipBD, String puerto)
    {
        cliente = new ConectorCliente(ipCliente, bd, usuario, clave, ipBD, puerto);
      
        cliente.start();
    }
    
}
