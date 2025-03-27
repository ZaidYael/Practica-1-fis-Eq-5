package com.example.fis_practica_1;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class simulacionController {

    int tiempo,ruta,totalEstaciones,noEstacion;
    boolean algoritmoEnEjecucion;

    @FXML
    public StackPane panePrincipal;
    @FXML
    public Button botonInicio;

    //Muestreo de tiempo, todavia en pruebas, ignorar/no usar
//    @FXML
//    public Label timeLabel;
//    @FXML
//    public Label time;

    // Lista para almacenar las estaciones (círculos)
    public List<Circle> estaciones = new ArrayList<>();

    @FXML
    public void initialize() {
        // Estaciones predefinidias de manera inicial (de color rojo) y un valor del 1 al 5, que identifica cada estacion
        // Se puede mover para donde quieras las estaciones (con X y Y)
        Circle estacion1 = estacionCreada(0, 0, Color.RED,1);
        Circle estacion2 = estacionCreada(-100, -50, Color.RED,2);
        Circle estacion3 = estacionCreada(100, -50, Color.RED,3);
        Circle estacion4 = estacionCreada(-100, 50, Color.RED,4);
        Circle estacion5 = estacionCreada(100, 50, Color.RED,5);

        // Agregar las estaciones al StackPane (revisar el main.fxml)
        panePrincipal.getChildren().addAll(estacion1, estacion2, estacion3, estacion4, estacion5);

        // Agregar las estaciones a la lista para poder manipularlas después
        estaciones.add(estacion1);
        estaciones.add(estacion2);
        estaciones.add(estacion3);
        estaciones.add(estacion4);
        estaciones.add(estacion5);

    }

    // Creacion de una estacion especifica dentro del "Pane Central"
    // Este metodo permite la creacion de objetos del tipo "Circle" de JavaFX
    // Puedes personalizar el tamaño de los circulos con sus radios
    // setTranslate permite mover el objeto dentro del contenedor (StackPane en este caso) de manera
    // horizontal y vertical
    public Circle estacionCreada(double x, double y, Color color, int noEstacion) {
        Circle estacion = new Circle(30, color);  // Crear un círculo con radio 30 y el color pasado
        estacion.setTranslateX(x);
        estacion.setTranslateY(y);
        return estacion;
    }

    // Cambio de estacion color verde
    public void cambiarColorVerde(int noEstacion) {
        if (noEstacion >= 1 && noEstacion <= 5) {
            estaciones.get(noEstacion - 1).setFill(Color.GREEN);  // Cambiar el color a verde
        }
    }

    // Cambio de estacion color rojo
    public void cambiarColorRojo(int noEstacion) {
        if (noEstacion >= 1 && noEstacion <= 5) {
            estaciones.get(noEstacion - 1).setFill(Color.RED);  // Cambiar el color a rojo
        }
    }

    // Generador de tiempo del 5 al 10 aleatorio
    public int tiempoGenerado(){
        Random rand = new Random();
        tiempo = 5 + rand.nextInt(6);
        return tiempo;
    }

    // El algoritmo tiene un hilo, el cual toma el tiempo, lo multiplica por 1000 (porque en milisegundos) para convertir
    // los segundos. Al momento de cambiar de ruta, esta se suma el 1 de la ruta para cada una de las estacinoes
    public void algoritmo() {
        ruta = 1;
        totalEstaciones = 5;
        noEstacion = 1;

        new Thread(() -> {
            while (true) {
                algoritmoEnEjecucion = true;
                tiempo = tiempoGenerado();
                cambiarColorVerde(noEstacion);
                try {
                    Thread.sleep(tiempo * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cambiarColorRojo(noEstacion);
                if (noEstacion == totalEstaciones) {
                    break;
                }
                noEstacion += ruta;
            }
        }).start();
    }

    // Boton, llama al metodo del algoritmo y tambien en caso de que el usuario quiera presionar mas, no se generan mas metros (o hilos)
    @FXML
    private void inicioAlgoritmo() {
        if (algoritmoEnEjecucion){
            System.out.println("Simulacion en prueba, por favor espere.");
            return;
        }
        algoritmo(); // Con este metodo se inicializa el algoritmo, puede agregarlo a otros botones

    }

    // IMPORTANTE: Hay que prevenir la generacion de hilos, para que el programa no colapse
}
