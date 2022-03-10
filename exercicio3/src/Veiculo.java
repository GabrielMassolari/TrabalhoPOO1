import java.io.Serializable;

public abstract class Veiculo implements Serializable {
    private String placa;
    private String marca;
    private String modelo;
    private double quilometragem;
    private String cor;
    private Cliente proprietario;
    
    public String getPlaca() {
        return this.placa;
    }
    
    public void setPlaca(String placa) {
        this.placa = placa;
    }
   
    public String getMarca() {
        return this.marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getModelo() {
        return this.modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public double getQuilometragem() {
        return this.quilometragem;
    }
   
    public void setQuilometragem(double quilometragem) throws ValueLessThanZero {
        if(quilometragem >= 0){
            this.quilometragem = quilometragem;
        }else{
            throw new ValueLessThanZero("Quilometragem deve ser maior que zero");
        }
    }
    
    public String getCor() {
        return this.cor;
    }
    
    public void setCor(String cor) {
        this.cor = cor;
    }

    public Cliente getProprietario() {
        return proprietario;
    }

    public void setProprietario(Cliente proprietario){
        this.proprietario = proprietario;
    }

    public Veiculo(String placa, String marca, String modelo, double quilometragem, String cor, Cliente proprietario) throws ValueLessThanZero {
        setPlaca(placa);
        setMarca(marca);
        setModelo(modelo);
        setQuilometragem(quilometragem);
        setCor(cor);
        setProprietario(proprietario);
    }    
}
