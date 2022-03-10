import java.time.LocalDate;

public class Compra extends Negociacao{
    private Vendedor vendedor;
    private double valorPago;

    public Vendedor getVendedor() {
        return vendedor;
    }
    
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) throws ValueLessThanZero {
        if(valorPago > 0){
            this.valorPago = valorPago;
        }else{
            throw new ValueLessThanZero("Valor pago deve ser maior que zero");
        }
    }

    public Compra(Cliente cliente, Veiculo veiculo, LocalDate diaNegociacao,Vendedor vendedor, double valorPago) throws ValueLessThanZero {
        super(cliente, veiculo, diaNegociacao);
        setVendedor(vendedor);
        setValorPago(valorPago);
    }
}
