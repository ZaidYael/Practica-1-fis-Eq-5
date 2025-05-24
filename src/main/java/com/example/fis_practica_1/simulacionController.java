package com.example.fis_practica_1;


import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class simulacionController {

    int tiempo,ruta,totalEstaciones,noEstacion;
    boolean algoritmoEnEjecucion;
    private final int TOTAL_ASIENTOS = 40;
    private Circle autobus;

    @FXML
    public StackPane panePrincipal;
    @FXML
    public Button botonInicio;
    @FXML
    public Label asientosOcupados;
    @FXML
    public Label asientosDisponibles;


    // Lista para almacenar las estaciones (círculos)
    public List<Circle> estaciones = new ArrayList<>();
    public List<Line> rutas = new ArrayList<>();

    @FXML
    public void initialize() {
        Image imagenFondo = new Image(getClass().getResource("/Mapa.jpeg").toExternalForm());

        BackgroundImage backgroundImage = new BackgroundImage(
                imagenFondo,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        panePrincipal.setBackground(new Background(backgroundImage));

        // Crear estaciones
        Circle estacion1 = estacionCreada(-350, -100, Color.GREEN, 1);
        Circle estacion2 = estacionCreada(-150, -50, Color.GREEN, 2);
        Circle estacion3 = estacionCreada(0, -50, Color.GREEN, 3);
        Circle estacion4 = estacionCreada(150, 50, Color.GREEN, 4);
        Circle estacion5 = estacionCreada(350, 100, Color.GREEN, 5);

        // Crear rutas
        Line ruta1 = ruta1Creada(-250, -75, 1);
        Line ruta2 = ruta2Creada(-75, -50, 2);
        Line ruta3 = ruta3Creada(75, 0, 3);
        Line ruta4 = ruta4Creada(250, 75, 4);

        // PRIMERO las rutas
        panePrincipal.getChildren().addAll(ruta1, ruta2, ruta3, ruta4);

        // Crear autobus
        autobus = new Circle(15, Color.ORANGE);
        // Un poco de diseño
        autobus.setStroke(Color.BLACK);        // Color del borde
        autobus.setStrokeWidth(2);             // Grosor del borde
        autobus.setStrokeType(StrokeType.OUTSIDE); // El grosor ligero esta afuera

        autobus.setTranslateX(estacion1.getTranslateX()); // Posicion de inicio del autobus en X
        autobus.setTranslateY(estacion1.getTranslateY()); // Posicion de inicio del autobus en Y
        autobus.setVisible(false); // Empieza invisible
        panePrincipal.getChildren().add(autobus);

        // Luego las estaciones encima del autobús
        panePrincipal.getChildren().addAll(estacion1, estacion2, estacion3, estacion4, estacion5);

        estaciones.add(estacion1);
        estaciones.add(estacion2);
        estaciones.add(estacion3);
        estaciones.add(estacion4);
        estaciones.add(estacion5);

        rutas.add(ruta1);
        rutas.add(ruta2);
        rutas.add(ruta3);
        rutas.add(ruta4);
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


    public Line ruta1Creada(double x, double y, int noRuta) {
        Line ruta = new Line(140.00, 140.00, 1.00, 100.00);  //v: Longitud horizontal v1: Inclinacion derecha v2:No sé v3: Longitud vertical
        ruta.setTranslateX(x);
        ruta.setTranslateY(y);

        ruta.setStroke(Color.GREEN);
        ruta.setStrokeWidth(3);

        return ruta;
    }

    public Line ruta2Creada(double x, double y, int noRuta) {
        Line ruta = new Line(90.00, 1.00, 1.00, 1.00);  //v: Longitud horizontal v1: Inclinacion derecha v2:No sé v3: Longitud vertical
        ruta.setTranslateX(x);
        ruta.setTranslateY(y);

        ruta.setStroke(Color.GREEN);
        ruta.setStrokeWidth(3);

        return ruta;
    }

    public Line ruta3Creada(double x, double y, int noRuta) {
        Line ruta = new Line(95.00, 175.00, 1.00, 100.00);  //v: Longitud horizontal v1: Inclinacion derecha v2:No sé v3: Longitud vertical
        ruta.setTranslateX(x);
        ruta.setTranslateY(y);

        ruta.setStroke(Color.GREEN);
        ruta.setStrokeWidth(3);

        return ruta;
    }

    public Line ruta4Creada(double x, double y, int noRuta) {
        Line ruta = new Line(140.00, 140.00, 1.00, 100.00);  //v: Longitud horizontal v1: Inclinacion derecha v2:No sé v3: Longitud vertical
        ruta.setTranslateX(x);
        ruta.setTranslateY(y);

        ruta.setStroke(Color.GREEN);
        ruta.setStrokeWidth(3);

        return ruta;
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
    // El algoritmo ahora no usa condiciones, si no bloques de Lambda.
    public void algoritmo() {
        ruta = 1;
        totalEstaciones = 5;
        noEstacion = 1;

        botonInicio.setDisable(true);
        algoritmoEnEjecucion = true;

        new Thread(() -> {
            while (true) {
                algoritmoEnEjecucion = true;
                tiempo = tiempoGenerado();

                int estacionActual = noEstacion;
                int siguienteEstacion = noEstacion + ruta;

                Platform.runLater(() -> {
                    cambiarColorRojo(estacionActual);
                    autobus.setVisible(true);
                    animarAutobus(estaciones.get(estacionActual - 1), estaciones.get(siguienteEstacion - 1), tiempo);
                });

                try {
                    Thread.sleep(tiempo * 1000); // Espera la duración de la animación
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    cambiarColorVerde(estacionActual);
                    autobus.setVisible(false);
                });

                if (noEstacion == totalEstaciones - 1) break;

                noEstacion += ruta;
            }

            Platform.runLater(() -> {
                botonInicio.setDisable(false);
            });

        }).start();
    }


    // Algoritmo de inicio
    public void algoritmoInicio(){
        Thread hiloInicio = new Thread(() -> { // Inicio del nuevo hilo para la espera de 3 segundos
            try {
                System.out.println("Iniciando simulacion...");
                botonInicio.setDisable(true); // Desactiva el boton

                int ocupados = generarNumero();
                int disponibles = TOTAL_ASIENTOS - ocupados;

                // Aquí se actualiza la interfaz con el número de asientos ocupados
                javafx.application.Platform.runLater(() -> {
                    asientosOcupados.setText(String.valueOf(ocupados));
                    asientosDisponibles.setText(String.valueOf(disponibles));

                    System.out.println("Asientos ocupados: " + ocupados + " | Asientos disponibles: " + disponibles); // Esto se imprime por consola
                });

                Thread.sleep(3000); // 3 Segundos de espera // 3000 milisegundos -> 3 segundos
                algoritmo(); // Vuelve a llamar al algoritmo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        hiloInicio.start();
    }

    // Boton, llama al metodo del algoritmo y tambien en caso de que el usuario quiera presionar mas, no se generan mas metros (o hilos)
    @FXML
    private void btnEjecucionSimulacion() {
        if (algoritmoEnEjecucion){
            System.out.println("Simulacion en prueba, por favor espere.");
        }
        algoritmoInicio(); // Ahora llama al algoritmo de inicio primero
    }

    // IMPORTANTE: Hay que prevenir la generacion de hilos, para que el programa no colapse
    private static long semilla = System.currentTimeMillis() % 1000;

    public static int generarNumero() {
        // Parámetros para el generador congruencial
        long a = 1664525;
        long c = 1013904223;
        long m = (long) Math.pow(2, 32);

        // Fórmula: Xn+1 = (a * Xn + c) % m
        semilla = (a * semilla + c) % m;

        // Escalar el número al rango deseado (1 a 40)
        int numeroAleatorio = (int) (semilla % 40) + 1;
        return numeroAleatorio;
    }

    // Animaciones del autobus

    private void animarAutobus(Circle origen, Circle destino, int duracionSegundos) {
        Line camino = new Line(
                origen.getTranslateX(), origen.getTranslateY(),
                destino.getTranslateX(), destino.getTranslateY()
        );

        PathTransition transicion = new PathTransition();
        transicion.setNode(autobus);
        transicion.setPath(camino);
        transicion.setDuration(Duration.seconds(duracionSegundos));
            transicion.setCycleCount(1);
            transicion.setAutoReverse(false);

        autobus.setVisible(true);
        transicion.play();
    }
}