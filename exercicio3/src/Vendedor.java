public class Vendedor extends Pessoa{
    private double porcentagemComissao;
    
    public double getPorcentagemComissao() {
        return porcentagemComissao;
    }
    
    public void setPorcentagemComissao(double porcentagemComissao) throws ValueLessThanZero {
        if(porcentagemComissao > 0){
            this.porcentagemComissao = porcentagemComissao;
        }else{
            throw new ValueLessThanZero("Valor de Porcentangem Comissao deve ser maior que zero.");
        }
    }

    public Vendedor(String nome, String cpf, String telefone, double porcentagemComissao) throws ValueLessThanZero {
        super(nome, cpf, telefone);
        setPorcentagemComissao(porcentagemComissao);
    }
}
