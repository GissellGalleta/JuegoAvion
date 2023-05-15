package Main;

import controlador.ControladorJuego;
import modelo.BalaEnemiga;
import modelo.BalaNave;
import vista.VentanaJuego;

public class Main {
    public static void main(String[] args) {
        VentanaJuego v1=new VentanaJuego();
        BalaNave b1=new BalaNave(v1);
        BalaEnemiga bE=new BalaEnemiga(v1,b1);
        b1.start();
        bE.start();
        ControladorJuego c1=new ControladorJuego(v1);
    }
}
