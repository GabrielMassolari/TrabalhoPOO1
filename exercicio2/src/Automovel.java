public class Automovel {
    private double quilometragem;
    private double volumeCombustivel;
    private double capacidadeTanque;
    private double velocidadeMaxima;
    private double velocidadeAtual;
    private double aceleracaoMedia;
    private double consumoMedio;

    public void setQuilometragem(double quilometragem) throws ValueLessThanZero {
        if(quilometragem >= 0){
            this.quilometragem = quilometragem;
        }else{
            throw new ValueLessThanZero("Quilometragem deve ser maior ou igual a zero");
        }
    }

    public double getQuilometragem(){
        return this.quilometragem;
    }

    public void setCapacidadeTanque(double capacidadeTanque) throws ValueLessThanZero{
        if(capacidadeTanque > 0){
            this.capacidadeTanque = capacidadeTanque;
        }else{
            throw new ValueLessThanZero("Capacidade Tanque deve ser maior que zero");
        }
    }

    public double getCapacidadeTanque(){
        return this.capacidadeTanque;
    }
    

    public void setVelocidadeMaxima(double velocidadeMaxima) throws ValueLessThanZero{
        if(velocidadeMaxima > 0){
            this.velocidadeMaxima = velocidadeMaxima * 0.277778;
        }else{
            throw new ValueLessThanZero("Velocidade Máxima deve ser maior que zero");
        }
    }

    public double getVelocidadeMaxima(){
        return this.velocidadeMaxima;
    }

    public void setAceleracaoMedia(double aceleracaoMedia) throws ValueLessThanZero{
        if(aceleracaoMedia > 0){
            this.aceleracaoMedia = aceleracaoMedia;
        }else{
            throw new ValueLessThanZero("Aceleração Media deve ser maior que zero");
        }
    }

    public double getAceleraçaoMedia(){
        return this.aceleracaoMedia;
    }

    public void setConsumoMedio(double consumoMedio) throws ValueLessThanZero {
        if(consumoMedio > 0){
            this.consumoMedio = consumoMedio;
        }else{
            throw new ValueLessThanZero("Consumo Medio deve ser maior que zero");
        }
    }

    public double getConsumoMedio(){
        return this.consumoMedio;
    }

    public void setVolumeCombustivel(double volumeCombustivel) throws ValueLessThanZero{
        if(volumeCombustivel >= 0){
            this.volumeCombustivel = volumeCombustivel;
        }else{
            throw new ValueLessThanZero("Volume Combustível deve ser maior ou igual a zero");
        }
    }

    public double getVolumeCombustivel(){
        return this.volumeCombustivel;
    }

    public void setVelocidadeAtual(double velocidadeAtual) throws ValueLessThanZero{
        if(velocidadeAtual >= 0){
            this.velocidadeAtual = velocidadeAtual;
        }else{
            throw new ValueLessThanZero("Velocidade Atual deve ser maior ou igual a zero");
        }
    }

    public double getVelocidadeAtual(){
        return this.velocidadeAtual;
    }

    public double getFrenagemMedia(){
        return this.aceleracaoMedia * -1;
    }

    public double abastecerVeiculo(double valorLitro, double valorPago) throws ValueLessThanZero{
        double litros = valorPago / valorLitro;
        double volumeTanqueAtual = this.volumeCombustivel;
        double troco = 0;

        if(volumeTanqueAtual + litros <= this.capacidadeTanque){
            setVolumeCombustivel(this.volumeCombustivel + litros);
            return troco;
        }else{
            troco = (litros - (this.capacidadeTanque - this.volumeCombustivel)) * valorLitro;
            setVolumeCombustivel(this.capacidadeTanque);
            return troco;
        }
    }

    private double calcularGastoCombustivel(double distancia){
        return distancia / (this.consumoMedio * 1000);
    }
    
    private void gastarCombustivel(double distancia) throws ValueLessThanZero{
        setVolumeCombustivel(this.volumeCombustivel - (distancia / (this.consumoMedio * 1000)));
    }

    private double calcularVelocidadeAtual(int segundos, double aceleracao){
        return this.velocidadeAtual + (aceleracao * segundos);
    }

    private double calcularQuilometragem(int segundos, double aceleracao){
        return (this.velocidadeAtual * segundos) + (aceleracao * (segundos * segundos) / (2));
    }

    private double calcularAceleracao(int segundos){
        return (this.velocidadeMaxima - this.velocidadeAtual) / segundos;
    }

    private double calcularFrenagem(int segundos){
        return (0 - this.velocidadeAtual) / segundos;
    }

    private void manterVelocidade(int segundos) throws ValueLessThanZero{
        gastarCombustivel(calcularQuilometragem(segundos, 0));
        setQuilometragem(this.quilometragem + calcularQuilometragem(segundos, 0));
    }

    public void frear(int segundos) throws ValueLessThanZero{
        if(segundos > 0){
            for(int i = 0; i < segundos; i++){
                if(calcularVelocidadeAtual(segundos, getFrenagemMedia()) >= 0){
                    setQuilometragem(this.quilometragem + calcularQuilometragem(1, getFrenagemMedia()));
                    setVelocidadeAtual(calcularVelocidadeAtual(1, getFrenagemMedia()));
                }else if(this.velocidadeAtual > 0){
                    setQuilometragem(this.quilometragem + calcularQuilometragem(1, calcularFrenagem(1)));
                    setVelocidadeAtual(0);
                    break;
                }
            }
        }else{
            throw new ValueLessThanZero("O tempo de Frenagem deve ser maior que zero");
        }
    }

    public void parar() throws ValueLessThanZero{
        frear((int)(this.velocidadeAtual / this.aceleracaoMedia) + 1);
    }

    public void acelerar(int segundos) throws ValueLessThanZero{
        if(segundos > 0){
            for(int i = 0; i < segundos; i++){
                if(calcularGastoCombustivel(calcularQuilometragem(1, this.aceleracaoMedia)) <= this.volumeCombustivel){
                    if(calcularVelocidadeAtual(1, this.aceleracaoMedia) <= this.velocidadeMaxima){
                        gastarCombustivel(calcularQuilometragem(1, this.aceleracaoMedia));
                        setQuilometragem(this.quilometragem + calcularQuilometragem(1, this.aceleracaoMedia));
                        setVelocidadeAtual(calcularVelocidadeAtual(1, this.aceleracaoMedia));
                    }else if(this.velocidadeAtual < this.velocidadeMaxima){
                        gastarCombustivel(calcularQuilometragem(1, calcularAceleracao(1)));
                        setQuilometragem(this.quilometragem + calcularQuilometragem(1, calcularAceleracao(1)));
                        setVelocidadeAtual(this.velocidadeMaxima);
                    }else{
                        manterVelocidade(segundos - i);
                        break;
                    }
                }else if(this.volumeCombustivel > 0){
                    setQuilometragem(this.quilometragem + (this.volumeCombustivel * (this.consumoMedio * 1000)));
                    if(this.velocidadeAtual + (this.volumeCombustivel * (this.consumoMedio * 1000)) <= velocidadeAtual){
                        setVelocidadeAtual(this.velocidadeAtual + (this.volumeCombustivel * (this.consumoMedio * 1000)));
                    }else if(this.velocidadeAtual < this.velocidadeMaxima){
                        setVelocidadeAtual(this.velocidadeMaxima);
                    }
                    setVolumeCombustivel(0);
                    parar();
                    break;
                }else{
                    parar();
                    break;
                }
            } 
        }else{
            throw new ValueLessThanZero("O tempo de Aceleração deve ser maior que zero");
        }
    }

    public Automovel(double quilometragem, double capacidadeTanque, double velocidadeMaxima,
             double aceleracaoMedia, double consumoMedio) throws ValueLessThanZero {
        setQuilometragem(quilometragem);
        setVolumeCombustivel(capacidadeTanque / 2);
        setCapacidadeTanque(capacidadeTanque);
        setVelocidadeMaxima(velocidadeMaxima);
        setVelocidadeAtual(0);
        setAceleracaoMedia(aceleracaoMedia);
        setConsumoMedio(consumoMedio);
    }
    
}
