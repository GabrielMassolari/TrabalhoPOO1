import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class MenuPrincipal {

    private static void limparTela(){
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void exibirOpcoesPrincipais(){
        System.out.println("---- Menu Revenda de Carros ---\n");
        System.out.println("1 - Cadastrar Venda de um Carro");
        System.out.println("2 - Cadastrar Venda de uma Moto");
        System.out.println("3 - Cadastrar Compra");
        System.out.println("4 - Cadastrar Cliente");
        System.out.println("5 - Cadastrar Vendedor");
        System.out.println("6 - Menu de Listagens");
        System.out.println("7 - Sair");
        System.out.print("\nDigite a opção escolhida: ");
    }

    public static void executar(Scanner scan){
        int opcao = 0;
        do{
            limparTela();
            exibirOpcoesPrincipais();
            opcao = scan.nextInt();
            scan.nextLine();
            switch(opcao){
                case 1:
                    cadastrarVendaCarro(scan);
                    break;
                case 2:
                    cadastrarVendaMoto(scan);
                    break;
                case 3:
                    cadastrarCompra(scan);
                    break;
                case 4:
                    cadastrarCliente(scan);
                    break;
                case 5:
                    cadastrarVendedor(scan);
                    break;
                case 6:
                    menuListagens(scan);
                    break;
                case 7:
                    limparTela();
                    System.out.println("Saindo do Sistema...");
                    //Chamar função de salvar
                    break;
                default:
                    System.out.println("Escolha uma opção válida!");
                    break;
            }
        }while(opcao != 7);
    }

    private static String cadastrarCategoriaCarro(Scanner scan){
        boolean categoriaEscolhida = false;
        String categoria = "";
        int opcaoMenuCategoria;
        do{
            System.out.println("-- Escolha a Categoria do Carro--");
            System.out.println("1 - Hatch");
            System.out.println("2 - Sedan");
            System.out.println("3 - Caminhonete");
            System.out.println("4 - SUV");
            System.out.print("Escolha a Categoria do carro: ");
            opcaoMenuCategoria = scan.nextInt();
            scan.nextLine();
            switch(opcaoMenuCategoria){
                case 1:
                    categoria = "Hatch";
                    categoriaEscolhida = true;
                    break;
                case 2:
                    categoria = "Sedan";
                    categoriaEscolhida = true;
                    break;
                case 3:
                    categoria = "Caminhonete";
                    categoriaEscolhida = true;
                    break;
                case 4:
                    categoria = "Hatch";
                    categoriaEscolhida = true;
                    break;
                default:
                    System.out.println("ESCOLHA UMA CATEGORIA VÁLIDA");
                    break;
            }
        }while(!categoriaEscolhida);
        
        return categoria;
    }

    private static String formatarCpf(String cpf){
        if(cpf.contains(".")){
            cpf = cpf.replace(".", "");
        }
        if(cpf.contains("-")){
            cpf = cpf.replace("-", "");
        }
        return cpf;
    }

    private static String formatarPlaca(String placa){
        if(placa.contains("-")){
            placa = placa.replace("-", "");
        }

        return placa;
    }

    private static int buscarCpfCliente(String cpf){
        cpf = formatarCpf(cpf);
        for(int i = 0; i < BancoDeDados.clientes.size(); i++){
            if(BancoDeDados.clientes.get(i).getCpf().equals(cpf)){
                return i;
            }
        }
        return -1;
    }

    private static int buscarCpfVendedor(String cpf){
        cpf = formatarCpf(cpf);
        for(int i = 0; i < BancoDeDados.vendedores.size(); i++){
            if(BancoDeDados.vendedores.get(i).getCpf().equals(cpf)){
                return i;
            }
        }
        return -1;
    }

    private static void cadastrarVendaCarro(Scanner scan) {
        String placa; 
        String marca;
        String modelo; 
        double quilometragem; 
        String cor;
        String motor; 
        String categoria; 
        String cambio; 
        double valorAnunciado;
        int indexCliente = -1;
        String cpfCliente;
        Cliente proprietario;
        Carro carro = null;
        int opcaoCpf;

        limparTela();
        System.out.println("--- Cadastro de Carro ---\n");
        System.out.print("Digite a Placa do Carro: ");
        placa = scan.nextLine();
        placa = formatarPlaca(placa);
        if(buscarVeiculo(placa) != null){
            System.out.printf("Essa placa (%s) de carro já esta cadastrada\n", placa);
            pressionarEnterParaContinuar();
            return;
        }
        System.out.print("Digite a Marca do Carro: ");
        marca = scan.nextLine();
        System.out.print("Digite o Modelo do Carro: ");
        modelo = scan.nextLine();
        System.out.print("Digite a Quilometragem do Carro: ");
        quilometragem = scan.nextDouble();
        scan.nextLine();
        System.out.print("Digite a Cor do Carro: ");
        cor = scan.nextLine();
        System.out.print("Digite o Motor do Carro(ex: 1.0, 1.6 ...): ");
        motor = scan.nextLine();
        categoria = cadastrarCategoriaCarro(scan);
        System.out.print("Digite o Cambio do Carro: ");
        cambio = scan.nextLine();
        System.out.print("Digite o Valor que deseja anunciar: R$");
        valorAnunciado = scan.nextDouble();
        scan.nextLine();
        do{
            System.out.print("Digite o Cpf do Proprietário do Carro: ");
            cpfCliente = scan.nextLine();
            indexCliente = buscarCpfCliente(cpfCliente);
            if(indexCliente == -1){
                limparTela();
                System.out.println("Cpf não encontrado!");
                System.out.println("1 - Tentar Novamente");
                System.out.println("2 - Cadastrar Cliente");
                System.out.println("3 - Voltar ao Menu Principal");
                System.out.print("Digite a opção escolhida: ");
                opcaoCpf = scan.nextInt();
                scan.nextLine();
                if(opcaoCpf == 2){
                    cadastrarCliente(scan);
                    limparTela();
                }else if(opcaoCpf == 3){
                    return;
                }
            }
        }while(indexCliente == -1);
        proprietario = BancoDeDados.clientes.get(indexCliente);
        
        try {
            BancoDeDados.carros.add(new Carro(placa, marca, modelo, quilometragem, cor, motor, categoria, cambio, proprietario));
            carro = BancoDeDados.carros.get(BancoDeDados.carros.size() - 1);
            try{
                BancoDeDados.vendas.add(new Venda(proprietario, carro, LocalDate.now(), valorAnunciado));
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Não foi possivel cadastrar a Venda do Carro, TENTE NOVAMENTE");
                pressionarEnterParaContinuar();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());    
            System.out.println("Não foi possível cadastrar o carro, TENTE NOVAMENTE");
            pressionarEnterParaContinuar();
        }
    }

    private static void cadastrarVendaMoto(Scanner scan) {
        String placa; 
        String marca;
        String modelo; 
        double quilometragem; 
        String cor;
        double cilindrada; 
        double valorAnunciado;
        int indexCliente = -1;
        String cpfCliente;
        Cliente proprietario;
        Moto moto = null;
        int opcaoCpf;

        limparTela();
        System.out.println("--- Cadastro de Moto ---\n");
        System.out.print("Digite a Placa da Moto: ");
        placa = scan.nextLine();
        placa = formatarPlaca(placa);
        if(buscarVeiculo(placa) != null){
            System.out.printf("Essa placa (%s) de moto já esta cadastrada\n", placa);
            pressionarEnterParaContinuar();
            return;
        }
        System.out.print("Digite a Marca da Moto: ");
        marca = scan.nextLine();
        System.out.print("Digite o Modelo da Moto: ");
        modelo = scan.nextLine();
        System.out.print("Digite a Quilometragem da Moto: ");
        quilometragem = scan.nextDouble();
        scan.nextLine();
        System.out.print("Digite a Cor da Moto: ");
        cor = scan.nextLine();
        System.out.print("Digite a Cilindrada da moto: ");
        cilindrada = scan.nextDouble();
        scan.nextLine();
        System.out.print("Digite o Valor que deseja anunciar: R$");
        valorAnunciado = scan.nextDouble();
        scan.nextLine();
        do{
            System.out.print("Digite o Cpf do Proprietário da Moto: ");
            cpfCliente = scan.nextLine();
            indexCliente = buscarCpfCliente(cpfCliente);
            if(indexCliente == -1){
                limparTela();
                System.out.println("Cpf não encontrado!");
                System.out.println("1 - Tentar Novamente");
                System.out.println("2 - Cadastrar Cliente");
                System.out.println("3 - Voltar ao Menu Principal");
                System.out.print("Digite a opção escolhida: ");
                opcaoCpf = scan.nextInt();
                scan.nextLine();
                if(opcaoCpf == 2){
                    cadastrarCliente(scan);
                }else if(opcaoCpf == 3){
                    return;
                }
            }
        }while(indexCliente == -1);
        proprietario = BancoDeDados.clientes.get(indexCliente);
        
        try {
            BancoDeDados.motos.add(new Moto(placa, marca, modelo, quilometragem, cor, cilindrada, proprietario));
            moto = BancoDeDados.motos.get(BancoDeDados.motos.size() - 1);
            try{
                BancoDeDados.vendas.add(new Venda(proprietario, moto, LocalDate.now(), valorAnunciado));
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Não foi possivel cadastrar a Venda da Moto, TENTE NOVAMENTE");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());    
            System.out.println("Não foi possível cadastrar a Moto, TENTE NOVAMENTE");
            pressionarEnterParaContinuar();
        }
    }

    private static Veiculo buscarVeiculo(String placa){
        placa = formatarPlaca(placa);
        for(int i = 0; i < BancoDeDados.carros.size(); i++){
            if(BancoDeDados.carros.get(i).getPlaca().equals(placa)){
                return BancoDeDados.carros.get(i);
            }
        }

        for(int i = 0; i < BancoDeDados.motos.size(); i++){
            if(BancoDeDados.motos.get(i).getPlaca().equals(placa)){
                return BancoDeDados.motos.get(i);
            }
        }

        return null;
    }

    private static void deletarVeiculo(String placa){
        placa = formatarPlaca(placa);
        
        for(int i = 0; i < BancoDeDados.carros.size(); i++){
            if(BancoDeDados.carros.get(i).getPlaca().equals(placa)){
                BancoDeDados.carros.remove(i);
                return;
            }
        }

        for(int i = 0; i < BancoDeDados.motos.size(); i++){
            if(BancoDeDados.motos.get(i).getPlaca().equals(placa)){
                BancoDeDados.motos.remove(i);
                return;
            }
        }
    }


    private static void deletarVenda(String placa){
        placa = formatarPlaca(placa);
        for(int i = 0; i < BancoDeDados.vendas.size(); i++){
            if(BancoDeDados.vendas.get(i).getVeiculo().getPlaca().equals(placa)){
                BancoDeDados.vendas.remove(i);
                break;
            }
        }
    }

    private static void cadastrarCompra(Scanner scan){
        Cliente cliente; 
        Veiculo veiculo = null; 
        Vendedor vendedor; 
        double valorPago;
        String cpfCliente;
        String cpfVendedor;
        int indexCliente = -1;
        int indexVendedor = -1;
        String placaVeiculo;
        int opcaoMenu;

        limparTela();
        System.out.println("--- Cadastro de Compra ---\n");
        do{
            System.out.print("Digite o Cpf do Cliente: ");
            cpfCliente = scan.nextLine();
            indexCliente = buscarCpfCliente(cpfCliente);
            if(indexCliente == -1){
                limparTela();
                System.out.println("Cpf não encontrado!");
                System.out.println("1 - Tentar Novamente");
                System.out.println("2 - Cadastrar Cliente");
                System.out.println("3 - Voltar ao Menu Principal");
                System.out.print("Digite a opção escolhida: ");
                opcaoMenu = scan.nextInt();
                scan.nextLine();
                if(opcaoMenu == 2){
                    cadastrarCliente(scan);
                    limparTela();
                }else if(opcaoMenu == 3){
                    return;
                }
            }
        }while(indexCliente == -1);
        cliente = BancoDeDados.clientes.get(indexCliente);
        opcaoMenu = -1;

        do{
            System.out.print("Digite o Cpf do Vendedor: ");
            cpfVendedor = scan.nextLine();
            indexVendedor = buscarCpfVendedor(cpfVendedor);
            if(indexVendedor == -1){
                limparTela();
                System.out.println("Cpf não encontrado!");
                System.out.println("1 - Tentar Novamente");
                System.out.println("2 - Cadastrar Vendedor");
                System.out.println("3 - Voltar ao Menu Principal");
                System.out.print("Digite a opção escolhida: ");
                opcaoMenu = scan.nextInt();
                scan.nextLine();
                if(opcaoMenu == 2){
                    cadastrarVendedor(scan);
                    limparTela();
                }else if(opcaoMenu == 3){
                    return;
                }
            }
        }while(indexVendedor == -1);
        vendedor = BancoDeDados.vendedores.get(indexVendedor);
        opcaoMenu = -1;

        do{
            System.out.print("Digite a Placa do Veículo: ");
            placaVeiculo = scan.nextLine();
            veiculo = buscarVeiculo(placaVeiculo);
            if(veiculo == null){
                limparTela();
                System.out.println("Veículo não encontrado!");
                System.out.println("1 - Tentar Novamente");
                System.out.println("2 - Voltar ao Menu Principal");
                System.out.print("Digite a opção escolhida: ");
                opcaoMenu = scan.nextInt();
                scan.nextLine();
                if(opcaoMenu == 2){
                    return;
                }
            }

        }while(veiculo == null);
        
        System.out.print("Digite o valor que foi Pago no Veículo: R$");
        valorPago = scan.nextDouble();
        scan.nextLine();

        try{
            BancoDeDados.compras.add(new Compra(cliente, veiculo, LocalDate.now(), vendedor, valorPago));
            deletarVenda(placaVeiculo);
            deletarVeiculo(placaVeiculo);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Não foi possível cadastrar a Compra, TENTE NOVAMENTE!");
            pressionarEnterParaContinuar();
        }
    }

    private static void cadastrarCliente(Scanner scan){
        String nome; 
        String cpf; 
        String telefone; 
        String email;

        limparTela();
        System.out.println("--- Cadastro de Cliente ---\n");
        System.out.print("Digite o nome do Cliente: ");
        nome = scan.nextLine();
        System.out.print("Digite o Cpf do Cliente: ");
        cpf = scan.nextLine();
        cpf = formatarCpf(cpf);
        if(buscarCpfCliente(cpf) != -1){
            System.out.println("Cliente já esta cadastrado");
            pressionarEnterParaContinuar();
            return;
        }
        System.out.print("Digite o Telefone do Cliente: ");
        telefone = scan.nextLine();
        System.out.print("Digite o Email do Cliente: ");
        email = scan.nextLine();

        try{
            BancoDeDados.clientes.add(new Cliente(nome, cpf, telefone, email));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Não foi possível cadastrar o cliente, TENTE NOVAMENTE!");
            pressionarEnterParaContinuar();
        }
    }

    private static void cadastrarVendedor(Scanner scan){
        String nome;
        String cpf;
        String telefone; 
        double porcentagemComissao;

        limparTela();
        System.out.println("--- Cadastro de Vendedor ---\n");
        System.out.print("Digite o nome do Vendedor: ");
        nome = scan.nextLine();
        System.out.print("Digite o Cpf do Vendedor: ");
        cpf = scan.nextLine();
        cpf = formatarCpf(cpf);
        if(buscarCpfVendedor(cpf) != -1){
            System.out.println("Vendedor já está cadastrado!");
            pressionarEnterParaContinuar();
            return;
        }
        System.out.print("Digite o Telefone do Vendedor: ");
        telefone = scan.nextLine();
        System.out.print("Digite a Porcentagem de Comissão do Vendedor: ");
        porcentagemComissao = scan.nextDouble();
        scan.nextLine();

        try {
            BancoDeDados.vendedores.add(new Vendedor(nome, cpf, telefone, porcentagemComissao));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Não foi possível cadastrar o vendedor, TENTE NOVAMENTE!");
            pressionarEnterParaContinuar();
        }
    }

    private static void pressionarEnterParaContinuar(){ 
        System.out.println("Pressione Enter para continuar...");
        try{
            System.in.read();
        }  
        catch(Exception e){

        }  
    }

    private static void listarVendas(){
        limparTela();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("--- Listagem das Vendas ---\n");
        System.out.printf("%-10s %-14s %-7s %-9s %-8s\n", "Marca", "Modelo", "Placa", "Preço", "Data");
        System.out.println("----------------------------------------------------");
        for(Venda v : BancoDeDados.vendas){
            System.out.printf("%-10s %-14s %-7s %-7.2f %-8s\n", v.getVeiculo().getMarca(), v.getVeiculo().getModelo(), v.getVeiculo().getPlaca(), v.getValorAnunciado(), dtf.format(v.getDiaNegociacao()));
        }
        System.out.println("----------------------------------------------------");
        pressionarEnterParaContinuar();
    }

    private static void listarCarros() {
        limparTela();
        System.out.println("--- Listagem de Carros Disponíveis para Venda ---\n");
        System.out.printf("%-10s %-14s %-7s %-5s %-12s %-12s\n", "Marca", "Modelo", "Placa", "Motor", "Categoria", "Cambio");
        System.out.println("-------------------------------------------------------------------------------------------");
        for(Carro c : BancoDeDados.carros){
            System.out.printf("%-10s %-14s %-7s %-5s %-12s %-12s\n", c.getMarca(), c.getModelo(), c.getPlaca(), c.getMotor(), c.getCategoria(), c.getCambio());
        }
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    private static void listarMotos(){
        limparTela();
        System.out.println("--- Listagem de Motos Disponíveis para Venda ---\n");
        System.out.printf("%-10s %-14s %-7s %-10s\n", "Marca", "Modelo", "Placa", "Cilindrada");
        System.out.println("-----------------------------------------------------");
        for(Moto m : BancoDeDados.motos){
            System.out.printf("%-10s %-14s %-7s %-10.0f\n", m.getMarca(), m.getModelo(), m.getPlaca(), m.getCilindrada());
        }
    }

    private static void listarCompras(){
        limparTela();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("--- Listagem das Compras Realizadas ---\n");
        System.out.printf("%-7s %-16s %-7s %-8s\n", "Placa", "Vendedor", "Valor Pago", "Data");
        System.out.println("----------------------------------------------------");
        for(Compra c: BancoDeDados.compras){
            System.out.printf("%-7s %-16s %-5.2f %-8s\n", c.getVeiculo().getPlaca(), c.getVendedor().getNome(), c.getValorPago(), dtf.format(c.getDiaNegociacao()));
        }
        System.out.println("----------------------------------------------------");
    }

    private static void listarClientes(){
        limparTela();
        System.out.println("--- Listagem dos Clientes ---\n");
        System.out.printf("%-20s %-10s %-10s %-12s\n", "Nome", "Cpf", "Telefone", "Email");
        System.out.println("--------------------------------------------------------");
        for(Cliente c: BancoDeDados.clientes){
            System.out.printf("%-20s %-10s %-10s %-12s\n", c.getNome(), c.getCpf(), c.getTelefone(), c.getEmail());
        }
        System.out.println("--------------------------------------------------------");
    }

    private static void listarVendedores(){
        limparTela();
        System.out.println("--- Listagem dos Vendedores ---\n");
        System.out.printf("%-20s %-10s %-10s %-8s\n", "Nome", "Cpf", "Telefone", "Porcentagem Comissão");
        System.out.println("-----------------------------------------------------------------");
        for(Vendedor v : BancoDeDados.vendedores){
            System.out.printf("%-20s %-10s %-10s %-6.2s%\n", v.getNome(), v.getCpf(), v.getTelefone(), v.getPorcentagemComissao());
        }
        System.out.println("-----------------------------------------------------------------");
    }

    private static void menuListagens(Scanner scan){
        int opcao;
        do{
            limparTela();
            System.out.println("--- Menu de Listagens ---\n");
            System.out.println("1 - Listar Carros");
            System.out.println("2 - Listar Motos");
            System.out.println("3 - Listar Clientes");
            System.out.println("4 - Listar Vendedores");
            System.out.println("5 - Listar Vendas");
            System.out.println("6 - Listar Compras");
            System.out.println("7 - Voltar ao Menu Principal");
            System.out.printf("Digite a opção escolhida: ");
            opcao = scan.nextInt();
            scan.nextLine();
            switch(opcao){
                case 1:
                    listarCarros();
                    pressionarEnterParaContinuar();
                    break;
                case 2:
                    listarMotos();
                    pressionarEnterParaContinuar();
                    break;
                case 3:
                    listarClientes();
                    pressionarEnterParaContinuar();
                    break;
                case 4:
                    listarVendedores();
                    pressionarEnterParaContinuar();
                    break;
                case 5:
                    listarVendas();
                    pressionarEnterParaContinuar();
                    break;
                case 6:
                    listarCompras();
                    pressionarEnterParaContinuar();
                    break;
                default:
                    break;
            }
        }while(opcao != 7);
        
    }
}
