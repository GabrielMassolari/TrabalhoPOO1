import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class BancoDeDados implements Serializable{
    public static ArrayList<Carro> carros = new ArrayList<Carro>();
    public static ArrayList<Moto> motos =  new ArrayList<Moto>();
    public static ArrayList<Venda> vendas = new ArrayList<Venda>();
    public static ArrayList<Compra> compras = new ArrayList<Compra>();
    public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    public static ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();

    public static void salvarDadosEmArquivos(){
        salvarClientesEmArquivo();
        salvarVendedoresEmArquivo();
        salvarCarrosEmArquivo();
        salvarMotosEmArquivo();
        salvarVendasEmArquivo();
        salvarComprasEmArquivo();
    }

    public static void lerDadosDosArquivos(){
        lerClientesDeArquivo();
        lerVendedoresDeArquivo();
        lerCarrosDeArquivo();
        lerMotosDeArquivo();
        lerVendasDeArquivo();
        lerComprasDeArquivo();
    }

    public static void salvarCarrosEmArquivo(){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream("carros.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(carros);
        }catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado.");
        }catch (IOException e) {
            System.out.println("Erro ao criar arquivo.");
        } finally {
            if(oos != null){
                try{
                    oos.close();
                }catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo");
                }
            }
        }
    }

    public static void salvarMotosEmArquivo(){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream("motos.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(motos);
        }catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado.");
        }catch (IOException e) {
            System.out.println("Erro ao criar arquivo.");
        } finally {
            if(oos != null){
                try{
                    oos.close();
                }catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo");
                }
            }
        }
    }

    public static void salvarVendasEmArquivo(){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream("vendas.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(vendas);
        }catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado.");
        }catch (IOException e) {
            System.out.println("Erro ao criar arquivo.");
        } finally {
            if(oos != null){
                try{
                    oos.close();
                }catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo");
                }
            }
        }
    }

    public static void salvarComprasEmArquivo(){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream("compras.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(compras);
        }catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado.");
        }catch (IOException e) {
            System.out.println("Erro ao criar arquivo.");
        } finally {
            if(oos != null){
                try{
                    oos.close();
                }catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo");
                }
            }
        }
    }

    public static void salvarClientesEmArquivo(){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream("clientes.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(clientes);
        }catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado.");
        }catch (IOException e) {
            System.out.println("Erro ao criar arquivo.");
        } finally {
            if(oos != null){
                try{
                    oos.close();
                }catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo");
                }
            }
        }
    }

    public static void salvarVendedoresEmArquivo(){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream("vendedores.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(vendedores);
        }catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado.");
        }catch (IOException e) {
            System.out.println("Erro ao criar arquivo.");
        } finally {
            if(oos != null){
                try{
                    oos.close();
                }catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo");
                }
            }
        }
    }

    public static void lerCarrosDeArquivo(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("carros.dat");
            ois = new ObjectInputStream(fis);
            carros = (ArrayList<Carro>)ois.readObject();
        }catch (ClassNotFoundException e){
            System.out.println("Classe não encontrada.");
        } catch (IOException e){
            System.out.println("Erro ao carregar arquivo.");
        } finally {
            if(ois != null){
                try{
                    ois.close();
                }catch (IOException e){
                    System.out.println("Erro ao fechar arquivo");
                }
            }
        }
    }

    public static void lerMotosDeArquivo(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("motos.dat");
            ois = new ObjectInputStream(fis);
            motos = (ArrayList<Moto>)ois.readObject();
        }catch (ClassNotFoundException e){
            System.out.println("Classe não encontrada.");
        } catch (IOException e){
            System.out.println("Erro ao carregar arquivo.");
        } finally {
            if(ois != null){
                try{
                    ois.close();
                }catch (IOException e){
                    System.out.println("Erro ao fechar arquivo");
                }
            }
        }
    }

    public static void lerVendasDeArquivo(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("vendas.dat");
            ois = new ObjectInputStream(fis);
            vendas = (ArrayList<Venda>)ois.readObject();
        }catch (ClassNotFoundException e){
            System.out.println("Classe não encontrada.");
        } catch (IOException e){
            System.out.println("Erro ao carregar arquivo.");
        } finally {
            if(ois != null){
                try{
                    ois.close();
                }catch (IOException e){
                    System.out.println("Erro ao fechar arquivo");
                }
            }
        }
    }

    public static void lerComprasDeArquivo(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("compras.dat");
            ois = new ObjectInputStream(fis);
            compras = (ArrayList<Compra>)ois.readObject();
        }catch (ClassNotFoundException e){
            System.out.println("Classe não encontrada.");
        } catch (IOException e){
            System.out.println("Erro ao carregar arquivo.");
        } finally {
            if(ois != null){
                try{
                    ois.close();
                }catch (IOException e){
                    System.out.println("Erro ao fechar arquivo");
                }
            }
        }
    }

    public static void lerClientesDeArquivo(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("clientes.dat");
            ois = new ObjectInputStream(fis);
            clientes = (ArrayList<Cliente>)ois.readObject();
        }catch (ClassNotFoundException e){
            System.out.println("Classe não encontrada.");
        } catch (IOException e){
            System.out.println("Erro ao carregar arquivo.");
        } finally {
            if(ois != null){
                try{
                    ois.close();
                }catch (IOException e){
                    System.out.println("Erro ao fechar arquivo");
                }
            }
        }
    }

    public static void lerVendedoresDeArquivo(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("vendedores.dat");
            ois = new ObjectInputStream(fis);
            vendedores = (ArrayList<Vendedor>)ois.readObject();
        }catch (ClassNotFoundException e){
            System.out.println("Classe não encontrada.");
        } catch (IOException e){
            System.out.println("Erro ao carregar arquivo.");
        } finally {
            if(ois != null){
                try{
                    ois.close();
                }catch (IOException e){
                    System.out.println("Erro ao fechar arquivo");
                }
            }
        }
    }
}
