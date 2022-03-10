public class App {
    
    public static void limparTela(){
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }catch(Exception e){
        }
    }
    public static void main(String[] args) throws Exception {
        Automovel jetta = new Automovel(0, 0.1, 240, 1, 2);

        for(int i = 0; i < 50; i++){
            limparTela();
            System.out.println("--- Carro: Volkswagen Jetta 2.0 TSI Comfortline 2011 ---\n");
            System.out.printf("Aceleração Atual: %.2f Km/h\n", jetta.getVelocidadeAtual() * 3.6);
            System.out.printf("Hodometro: %.4f Km\n", jetta.getQuilometragem() / 1000);
            System.out.printf("Tanque de Gasolina: %.4f L\n", jetta.getVolumeCombustivel());
            jetta.acelerar(1);
            Thread.sleep(1500);
        }

    }
}
