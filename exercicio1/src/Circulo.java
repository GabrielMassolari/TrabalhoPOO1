public class Circulo {
    private double raio;
    private int x;
    private int y;

    private void setRaio(double raio) throws RadiusLessThanZero{
        if(raio > 0){
            this.raio = raio;
        }else{
            throw new RadiusLessThanZero();
        }
    }

    private double getRaio(){
        return this.raio;
    }

    public double obterRaio(){
        return getRaio();
    }

    private void setX(int x){
        this.x = x;
    }

    private void setY(int y){
        this.y = y;
    }

    private int getX(){
        return this.x;
    }

    private int getY(){
        return this.y;
    }

    public void setCoordenadaCentro(int x, int y){
        setX(x);
        setY(y);
    }

    public int[] ObterCoordenadaCentro(){
        int cordenadas[] = {getX(), getY()};
        return cordenadas;
    }

    public void aumentarRaioPercentual(double percentual) throws RadiusLessThanZero{
        percentual = percentual / 100;
        setRaio(this.raio * (1 + percentual));
    }

    public double calcularArea(){
        return Math.PI * (this.raio * this.raio);
    }

    public double calcularComprimento(){
        return 2 * Math.PI * this.raio;
    }

    public Circulo(){
        this.raio = 1;
        this.x = 0;
        this.y = 0;
    }

    public Circulo(double raio, int x, int y) throws RadiusLessThanZero{
        setRaio(raio);
        setCoordenadaCentro(x, y);
    }
}               
