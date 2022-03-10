public class Moto extends Veiculo {
    private double cilindrada;

    public double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(double cilindrada) throws ValueLessThanZero {
        if(cilindrada > 0){
            this.cilindrada = cilindrada;
        }else{
            throw new ValueLessThanZero("Valor de Cilindrada deve ser maior que zero");
        }
    }

    public Moto(String placa, String marca, String modelo, double quilometragem, String cor, double cilindrada,
                Cliente proprietario) throws ValueLessThanZero {
        super(placa, marca, modelo, quilometragem, cor, proprietario);
        setCilindrada(cilindrada);
    }
}
