import java.io.Serializable;
import java.time.LocalDate;

public abstract class Negociacao implements Serializable{
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate diaNegociacao;
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Veiculo getVeiculo() {
        return veiculo;
    }
    
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    public LocalDate getDiaNegociacao() {
        return diaNegociacao;
    }
    
    public void setDiaNegociacao(LocalDate diaNegociacao) {
        this.diaNegociacao = diaNegociacao;
    }

    public Negociacao(Cliente cliente, Veiculo veiculo, LocalDate diaNegociacao) {
        setCliente(cliente);
        setVeiculo(veiculo);
        setDiaNegociacao(diaNegociacao);
    }

}
