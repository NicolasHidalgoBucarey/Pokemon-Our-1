
public class Mapa {
    private int Posicion;
    private String [] Lugares = {"Ciudad Paleta","Ciudad Viridian","Ciudad Pewter","Ciudad Cerulean","Ciudad Safron"};;
    private String [][] Viajar = {{"Ciudad Viridian","no"},{"Ciudad Paleta","Ciudad Pewter"},{"Ciudad Viridian","Ciudad Cerulean"},
    {"Ciudad Pewter","Ciudad Safron"},{"Ciudad Cerulean","no"}};
    
    Mapa(){
    }
    public int getPosicion(){
        return this.Posicion;
    }
    public String getLugar(int Posicion){
        return Lugares[Posicion];
    }
    public void setPosicion(int Posicion){
        this.Posicion = Posicion;
    }
    public void setLugares(String[] Lugares){
        this.Lugares = Lugares;
    }
    public String[] getLugares(){
        return Lugares;
    }
    public void setViajar(String[][] Viajar){
        this.Viajar = Viajar;
    }
    public String[][] getViajar(){
        return Viajar;
    }

    public String[] viajar(int Posicion){
        if (Posicion == 0){
            return Viajar[Posicion];
        } 
        else if(Posicion == 5){
            return Viajar[Posicion];
        }
        else{            
            return Viajar[Posicion];
        }
    }
}
