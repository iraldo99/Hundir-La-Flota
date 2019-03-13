package hundirlaflota;


public class Barco {
    final private int jugador;//1 = jugador1 , 2 = jugador2
    private int[] posicion_inicial;
    private String direccion;
    private int tamaño;
    final private int[][] posiciones;
    final private int[][] posiciones_tiradas;
    private boolean vivo;

    public Barco(int jugador, int[] pos_ini, String direccion, int tamaño) {
        this.jugador = jugador;
        this.tamaño = tamaño;
        this.direccion = direccion;
        this.posicion_inicial = new int[2];
        // posicion_inicial[0] = (COLUMNA)
        // posicion_inicial[1] = (FILA)
        this.posicion_inicial = pos_ini;
        this.posiciones = new int[tamaño][tamaño];
        this.posiciones_tiradas = new int[tamaño][tamaño];
        this.vivo=true;
        
        rellenarPosiciones(posicion_inicial, direccion);
            
        
    }

    public int[][] getPosiciones() {
        return posiciones;
    }
    public int getPosiciones(int c, int f) {
        return posiciones[c][f];
    }

    

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int[] getPosicion_inicial() {
        return posicion_inicial;
    }

    public void setPosicion_inicial(int[] posicion_inicial) {
        this.posicion_inicial = posicion_inicial;
    }

    
    
    
    public void rellenarPosiciones(int[] posicionInicial,String direccion){
        posiciones[0][0]=posicionInicial[0];
        posiciones[0][1]=posicionInicial[1];
        
        if(tamaño>1){
            // ESTO ES MEJORABLE (UN FOR GRANDE CON IFS, Y PARAMETRIZAR SUMANDO
            // C y F SIEMPRE
            if(this.direccion.equals("arriba")){
                
                for (int i = 1; i < tamaño; i++) {
                    posiciones[i][0] = posicion_inicial[0];
                    posiciones[i][1] = posicion_inicial[1]-i;
                }
                
            }else if(this.direccion.equals("abajo")){
                
                for (int i = 1; i < tamaño; i++) {
                    posiciones[i][0] = posicion_inicial[0];
                    posiciones[i][1] = posicion_inicial[1]+i;
                }
                
            }else if(this.direccion.equals("derecha")){
                
                for (int i = 1; i < tamaño; i++) {
                    posiciones[i][0] = posicion_inicial[0]+i;
                    posiciones[i][1] = posicion_inicial[1];
                }
                
            }else if(this.direccion.equals("izquierda")){
                
                for (int i = 1; i < tamaño; i++) {
                    posiciones[i][0] = posicion_inicial[0]-i;
                    posiciones[i][1] = posicion_inicial[1];
                }
                
            }
            
        }
    }
    
}
