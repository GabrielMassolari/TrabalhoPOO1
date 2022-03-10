public class RadiusLessThanZero extends Exception {
    public RadiusLessThanZero(){
        this("Raio deve ser maior que zero");
    }

    public RadiusLessThanZero(String texto){
        super(texto);
    }
}
