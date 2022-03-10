public class ValueLessThanZero extends Exception{
    public ValueLessThanZero(){
        this("Valor menor que zero");
    }

    public ValueLessThanZero(String texto){
        super(texto);
    }
}
