import java.time.LocalDate;

public class Venda extends Negociacao {
    private double valorAnunciado;
    
    public double getValorAnunciado() {
        return valorAnunciado;
    }
    
    public void setValorAnunciado(double valorAnunciado) throws ValueLessThanZero {
        if(valorAnunciado > 0){
            this.valorAnunciado = valorAnunciado;
        }else{
            throw new ValueLessThanZero("Valor Anunciado deve ser maior que zero");
        }
    }

    public Venda(Cliente cliente, Veiculo veiculo, LocalDate diaNegociacao, double valorAnunciado) throws ValueLessThanZero {
        super(cliente, veiculo, diaNegociacao);
        setValorAnunciado(valorAnunciado);
    }
}
