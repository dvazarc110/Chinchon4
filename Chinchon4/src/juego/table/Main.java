package juego.table;

public class Main {

    public static void main(String[] args) {

        Menu.mostrar();

        MotorPartida motor = new MotorPartida();

        motor.iniciar();
    }
}
