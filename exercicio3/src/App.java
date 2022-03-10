import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        //Autores: Gabriel Guimarães Massolari e Vinicius Toledo
        Scanner scan = new Scanner(System.in);
        BancoDeDados.lerDadosDosArquivos();
        MenuPrincipal.executar(scan);
        BancoDeDados.salvarDadosEmArquivos();
        scan.close();
    }
}
