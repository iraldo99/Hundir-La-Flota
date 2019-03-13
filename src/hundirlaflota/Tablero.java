package hundirlaflota;

public class Tablero {
    private int tamaño;
    private String[][] posicion;
    private int[] id_barcos;
    private int jugador;//1 = jugador1 , 2 = jugador2
    
    
    public String palo = HundirLaFlota.palo;
    
    public String agua = HundirLaFlota.agua;
    public String barco_dibujo = HundirLaFlota.barco_dibujo;
    public String tiro_barco = "X";
    public String tiro_agua = "O";

    public Tablero(int tamaño,int jugador) {
        this.tamaño = tamaño;
        this.posicion = new String[tamaño][tamaño];
        this.jugador=jugador;
        
        
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                posicion[i][j]=agua;
            }
        }
        
    }

    public int[] getId_barcos() {
        return id_barcos;
    }

    public void setId_barcos(int[] id_barcos) {
        this.id_barcos = id_barcos;
    }
    
    public int getTamaño() {
        return tamaño;
    }
    
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    public String[][] getPosicion() {
        return posicion;
    }
    
    public String getPosicion(int c, int f) {
        
        return posicion[c][f];
    }
    
    public String getPosicion(int [] pos_ini) {
        
        return posicion[pos_ini[0]][pos_ini[1]];
    }
    
    public void setPosicion(String[][] posicion) {
        this.posicion = posicion;
    }
    
    public void introducirBarco(Barco barco){
        int tamaño = barco.getTamaño();
        int c;
        int f;
        for (int i = 0; i < tamaño; i++) {
            c = barco.getPosiciones(i, 0);
            f = barco.getPosiciones(i, 1);
            this.posicion[c][f] = barco_dibujo;
        }
    }
    
    
    public void recibirDisparo(){
        
    }
    
    
}
