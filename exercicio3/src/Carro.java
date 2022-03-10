public class Carro extends Veiculo{
    private String motor; 
    private String categoria;
    private String cambio;

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getCategoria(){
        return this.categoria;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public String getCambio(){
        return this.cambio;
    }

    public void setCambio(String cambio){
        this.cambio = cambio;
    }

    public Carro(String placa, String marca, String modelo, double quilometragem, String cor,
            String motor, String categoria, String cambio, Cliente proprietario) throws ValueLessThanZero {
        super(placa, marca, modelo, quilometragem, cor, proprietario);
        setMotor(motor);
        setCategoria(categoria);
        setCambio(cambio);
    }
}
