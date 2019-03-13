//
package hundirlaflota;

import java.util.ArrayList;
import java.util.Scanner;



public class HundirLaFlota {
    
    public static String input(String pregunta){ /////////// FUNCIONES INPUT \
        Scanner reader = new Scanner(System.in);
        System.out.println(pregunta);
        String texto = reader.next();
        return texto;
    }
    public static int intput(String pregunta){
        Scanner reader = new Scanner(System.in);
        System.out.println(pregunta);
        int numero = reader.nextInt();
        return numero;
    }
    public static double indput(String pregunta){
        Scanner reader = new Scanner(System.in);
        System.out.println(pregunta);
        double numero = reader.nextDouble();
        return numero;
    }
    public static String input(){
        Scanner reader = new Scanner(System.in);
        String texto = reader.next();
        return texto;
    }
    public static int intput(){
        Scanner reader = new Scanner(System.in);
        int numero = reader.nextInt();
        return numero;
    }
    public static double indput(){
        Scanner reader = new Scanner(System.in);
        double numero = reader.nextDouble();
        return numero;
    }////////////////////////////////////////////////////// FUNCIONES INPUT /
    
    
    public static Tablero tablero1_1;//juego de jugador 1
    public static Tablero tablero1_2;//vista de jugador 1
    public static Tablero tablero2_1;//juego de jugador 2
    public static Tablero tablero2_2;//vista de jugador 2
    public static int tamañoTableros = 5;
    public static String palo = "|";
    public static String agua = "~";
    public static String barco_dibujo = "#";
    public static String[] nombre_jugadores;
    public static int contadorDeBarcos;
    public static int jugadorActual = 0;
    public static int cant_barcos = 3;
    public static String[] posibles;
    
    public static ArrayList <Barco> barcos = new ArrayList<>();
    
    
    
    
    public static void mostrarTablero(Tablero tablero){
        for (int i = 0; i < tamañoTableros+1; i++) {
            System.out.println();
            for (int j = 0; j < tamañoTableros+1; j++) {
                if(i==0){
                    if(j-1<0){
                        System.out.print(palo+"---"+palo);
                    }else{
                        System.out.print(palo+"-"+(j-1)+"-"+palo);
                    }
                }else if(j==0){
                    System.out.print(palo+"-"+(i-1)+"-"+palo);
                }else{
                    System.out.print(palo+" "+tablero.getPosicion((j-1),(i-1))+" "+palo);
                }
            }
        }
        System.out.println();
    }
    
    
    public static void establecerTamañoJuego(){
        // PIDE AL USUARIO EL TAMAÑO DEL TABLERO, SIENDO ÉSTE MÍNIMO 5
        // Y ESTABLECE LA CANTIDAD DE BARCOS //
        tamañoTableros = intput("Dame el tamaño del tablero de juego: ");
        while(tamañoTableros < 5){
            System.out.println("El tamaño debe ser mínimo de 5.");
            tamañoTableros = intput("Dame el tamaño del tablero de juego: ");
        }
        cant_barcos = tamañoTableros-2;
    }
    
    
    public static void establecerJugadores(){
        nombre_jugadores = new String[3];
        nombre_jugadores[0] = ("EMPTY");
        
        nombre_jugadores[1] = input("Nombre del jugador 1: ");
        tablero1_1 = new Tablero(tamañoTableros,1);
        tablero1_2 = new Tablero(tamañoTableros,1);
        
        nombre_jugadores[2] = input("Nombre del jugador 2: ");
        tablero2_1 = new Tablero(tamañoTableros,2);
        tablero2_2 = new Tablero(tamañoTableros,2);
    }
    
    
    public static void crearBarcos(int jugador, Tablero tablero){
        System.out.println("JUGADOR "+jugador+": "+nombre_jugadores[jugador]+
                " POSICIONA TUS BARCOS");
        
        int tamaño = tamañoTableros-1;
        
        int contador = 1;
        int[] pos_ini = new int[2];
        String direccion;
        
        
        posibles = new String[4];
        for (int i = 0; i < 4; i++) {
            posibles[i]=" ";
            
        }
        
        
        
        while(tamaño > 1){
            for (int i = 0; i < 4; i++) {
                posibles[i]=" ";
            }
            
            mostrarTablero(tablero);
            
            System.out.println("Barco "+contador+": Tamaño: "+tamaño);
            
            pos_ini[0] = intput("¿Desde qué columna quieres ponerlo? ");
            pos_ini[1] = intput("¿Y qué fila? ");
            
            pos_ini = compruebaAgua(tablero, pos_ini);
            
            System.out.println("La pos: "+tablero.getPosicion(pos_ini[0],pos_ini[1]));
            
            while(!compruebaDirecciones(pos_ini[0], pos_ini[1], tamaño, tablero)){
                System.out.println("Desde esa posición no cabe el barco hacia "
                        + "ninguna dirección.");
                pos_ini[0] = intput("¿Desde qué columna quieres ponerlo? ");
                pos_ini[1] = intput("¿Y qué fila? ");
                
                pos_ini = compruebaAgua(tablero, pos_ini);
            }
            
            
            direccion = pideDireccion();
            
            
            barcos.add(new Barco(jugador,pos_ini,direccion,tamaño));
            tablero.introducirBarco(barcos.get(barcos.size()-1));
            
            /*if (barcos.size()==1) { //testeo posicones del barco
                System.out.println("Las POS del barco 1.");
                for (int i = 0; i < barcos.get(0).getTamaño(); i++) {
                    System.out.println(barcos.get(0).getPosiciones(i, 0)+","+
                            barcos.get(0).getPosiciones(i, 1));
                }
                System.out.println();
            }*/
            
            
            contador++;
            tamaño--;
        }
        
    }
    
    
    public static void imprimirPosibles(){
        System.out.println("Direcciones posibles hacia las que puedes "
                    + "ponerlo:");
            System.out.println(posibles[0]+" "+posibles[1]+" "+posibles[2]+
            " "+posibles[3]);
    }
    
    
    public static String pideDireccion(){
        String direccion;
        boolean check = false;
        imprimirPosibles();

        direccion = input("¿En qué dirección?");
        for(int i = 0; i < posibles.length; i++){
            if(posibles[i].equals(direccion)){
                check = true;
            }
        }
        while(!check){
            System.out.println("Esa dirección es errónea.");
            imprimirPosibles();

            direccion = input("¿En qué dirección?");
            for(int i = 0; i < posibles.length; i++){
                if(posibles[i].equals(direccion)){
                    check = true;
                }
            }
        }
        return direccion;
    }
    
    
    public static int[] compruebaAgua(Tablero tablero, int[] pos_ini){
        while(!tablero.getPosicion(pos_ini[0],pos_ini[1]).equals(agua)){
            System.out.println("Esa posición inicial es errónea.");
            pos_ini[0] = intput("¿Desde qué columna quieres ponerlo? ");
            pos_ini[1] = intput("¿Y qué fila? ");
        }
        return pos_ini;
    }
    
    
    public static boolean compruebaDirecciones(int col, int fil, int tamaño, Tablero tablero){
        boolean posible = false;
        
        
        if(comprueba(tablero,"arriba",tamaño,col,fil)){
            
            posibles[0] = "arriba";
            posible = true;
        }
        if(comprueba(tablero,"abajo",tamaño,col,fil)){
            posibles[1] = "abajo";
            posible = true;
        }
        if(comprueba(tablero,"derecha",tamaño,col,fil)){
            
            posibles[2] = "derecha";
            posible = true;
        }
        if(comprueba(tablero,"izquierda",tamaño,col,fil)){
            
            posibles[3] = "izquierda";
            posible = true;
        }
        
        
        return posible;
        
    }
    
    
    public static boolean comprueba(Tablero tablero, String direccion, int tamaño, int col, int fil){
        
        
        switch (direccion) {
            case "arriba":
                if(!((fil+1)>=tamaño)){
                    return false;
                }else{
                    for (int i = 1; i < tamaño; i++) {
                        if(!((fil+1-i)>=0)){
                            return false;
                        }else{
                            if(!tablero.getPosicion((col),(fil-i)).equals(agua)){
                                return false;
                            }
                        }
                    }
                }
                break;
            case "abajo":
                if(((fil+tamaño)>tamañoTableros)){
                    return false;
                }else{
                    for (int i = 1; i < tamaño; i++) {
                        if((fil+i)>tamañoTableros){
                            return false;
                        }else{
                            if(!(tablero.getPosicion((col),(fil+i)).equals(agua))){
                                return false;
                            }
                        }
                    }
                }
                break;
            case "izquierda":
                if(!((col+1)>=tamaño)){
                    return false;
                }else{
                    for (int i = 1; i < tamaño; i++) {
                        if(!((col+1-i)>=0)){
                            return false;
                        }else if(!tablero.getPosicion((col-i),(fil)).equals(agua)){
                            return false;
                        }
                    }
                }
                break;
            case "derecha":
                if(((col+tamaño)>tamañoTableros)){
                    return false;
                }else{
                    for (int i = 1; i < tamaño; i++) {
                        if(!((col+i)<tamañoTableros)){
                            return false;
                        }else if(!tablero.getPosicion((col+i),(fil)).equals(agua)){
                            return false;
                        }
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
        
        
        return true;
        
    }
    
    
    public static void inicializarJuego(){
        
        
        establecerTamañoJuego();
        
        establecerJugadores();
        /*
        int random1[];
        random1 = new int[2];
        random1[0] = 1;
        random1[1] = 1;
        barcos.add(new Barco(1,random1,"abajo",4));
        
        System.out.println(barcos.get(barcos.size()-1).getPosiciones(0,0));
        System.out.println(barcos.get(barcos.size()-1).getPosiciones(0,1));
        System.out.println(barcos.get(barcos.size()-1).getPosiciones(1,0));
        System.out.println(barcos.get(barcos.size()-1).getPosiciones(1,1));
        System.out.println(barcos.get(barcos.size()-1).getPosiciones(2,0));
        System.out.println(barcos.get(barcos.size()-1).getPosiciones(2,1));
        System.out.println(barcos.get(barcos.size()-1).getPosiciones(3,0));
        System.out.println(barcos.get(barcos.size()-1).getPosiciones(3,1));
        tablero1_1.introducirBarco(barcos.get(barcos.size()-1));
        */
        crearBarcos(1,tablero1_1);
        crearBarcos(2,tablero2_1);
    }
    
    
    public static void alternarJugador(){
        switch (jugadorActual) {
                case 0:
                    jugadorActual=1;
                    break;
                case 1:
                    jugadorActual=2;
                    break;
                case 2:
                    jugadorActual=1;
                    break;
                default:
                    throw new AssertionError();
            }
    }
    
    
    public static void imprimirMenuJuego(){
        System.out.println(); //margen
        System.out.println("JUGADOR: "+nombre_jugadores[jugadorActual]);
        System.out.println("1) Ver tableros");
        System.out.println("2) Disparar");
        System.out.println("0) SALIR");
    }
    
    
    public static void main(String[] args) {
        
        inicializarJuego();
        mostrarTablero(tablero1_1);
        mostrarTablero(tablero1_2);
        mostrarTablero(tablero2_1);
        mostrarTablero(tablero2_2);
        
        boolean termina = false;
        while(!termina){
            alternarJugador();
            imprimirMenuJuego();
            
            
        }
        
        
    }
    
     
     
    
}
