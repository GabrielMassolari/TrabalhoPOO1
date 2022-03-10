public class ValueLessThanZero extends Exception {
    public ValueLessThanZero(){
        this("Valor deve ser menor que zero");
    }

    public ValueLessThanZero(String texto){
        super(texto);
    }
}
