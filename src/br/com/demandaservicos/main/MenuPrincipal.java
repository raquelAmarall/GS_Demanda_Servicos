package br.com.demandaservicos.main;

import br.com.demandaservicos.model.*;

import br.com.demandaservicos.db.ConexaoBD;

import br.com.demandaservicos.excecao.*;

import br.com.demandaservicos.persistencia.*;

import br.com.demandaservicos.servico.*;

import java.util.Scanner;

import java.util.List;

public class MenuPrincipal {

    private Scanner scanner = new Scanner(System.in);
    
    private DadosTerritorio territorio =
            new DadosTerritorio(20, 40, 60, 10000);
    
    private Sensor[] sensores = {
    	    new SensorConsultas(),
    	    new SensorVacinacao(),
    	    new SensorExames()
    	};

    public void exibirMenu() {

        System.out.println("\n=== TERRITORY PREDICTIVE INSIGHTS ===");
        System.out.println("1 - Verificar sensores");
        System.out.println("2 - Rodar previsões");
        System.out.println("3 - Consultar histórico de medições");
        System.out.println("4 - Gerenciar dados do território");
        System.out.println("5 - Consultar status completo");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");

    }
    
    public void iniciar() {

        int opcao;

        do {
            exibirMenu();

            opcao = scanner.nextInt();

            switch (opcao) {

            case 1:
                System.out.println("\n=== VERIFICAÇÃO DOS SENSORES ===");


                for (Sensor sensor : sensores) {
                    if (sensor.verificarFuncionamento()) {
                        System.out.println(sensor.tipo() + ": Funcionando");
                    } else {
                        System.out.println(sensor.tipo() + ": Indisponível");
                    }
                }

                break;

            case 2:

                System.out.println("\n=== EXECUÇÃO DAS PREVISÕES ===");

                MedicaoDAO medicaoDAO = new MedicaoDAO();
                AlertaDAO alertaDAO = new AlertaDAO();

                EstrategiaPrevisao estrategia =
                        FabricaPrevisao.criarEstrategia("media");

                ServicoMonitoramento servico = new ServicoMonitoramento(
                        medicaoDAO, alertaDAO, territorio, estrategia);


                for (Sensor sensor : sensores) {

                    try {
                        int valor = sensor.lerValor();

                        System.out.println("\nSensor: " + sensor.tipo());
                        System.out.println("Valor coletado: " + valor);

                        Medicao medicao = new Medicao(sensor.tipo(), valor);
                        medicaoDAO.inserir(medicao);

                        servico.verificarEEmitirAlerta(sensor.tipo());

                    } catch (FonteIndisponivelException e) {
                        System.out.println("Sensor indisponível: " + e.getMessage());

                    } catch (DadosInsuficientesException e) {
                        System.out.println("Ainda não há dados suficientes: " + e.getMessage());
                    }
                }

                break;

            case 3:

                System.out.println("\n=== HISTÓRICO DE MEDIÇÕES ===");

                MedicaoDAO daoHistorico = new MedicaoDAO();

                List<Medicao> historico = daoHistorico.listarTodas();

                if (historico.isEmpty()) {
                    System.out.println("Nenhuma medição encontrada.");
                } else {

                    for (Medicao medicao : historico) {

                        System.out.println("\nID: " + medicao.getId());
                        System.out.println("Sensor: " + medicao.getTipoSensor());
                        System.out.println("Valor: " + medicao.getValor());
                        System.out.println("Data e hora: " + medicao.getDataHora());
                    }
                }

                break;

            case 4:

                System.out.println("\n=== DADOS DO TERRITÓRIO ===");

                System.out.println("População atendida: "
                        + territorio.getPopulacaoAtendida());

                System.out.println("Limiar de atenção: "
                        + territorio.getLimiarAtencao());

                System.out.println("Limiar de alerta: "
                        + territorio.getLimiarAlerta());

                System.out.println("Limiar crítico: "
                        + territorio.getLimiarCritico());

                System.out.println("\nDeseja atualizar os dados? (1 - Sim / 0 - Não)");
                int atualizar = scanner.nextInt();

                if (atualizar == 1) {

                    try {
                        System.out.print("Nova população atendida: ");
                        int populacao = scanner.nextInt();

                        System.out.print("Novo limiar de atenção: ");
                        double atencao = scanner.nextDouble();

                        System.out.print("Novo limiar de alerta: ");
                        double alerta = scanner.nextDouble();

                        System.out.print("Novo limiar crítico: ");
                        double critico = scanner.nextDouble();

                        territorio.setPopulacaoAtendida(populacao);
                        territorio.setLimiares(atencao, alerta, critico);

                        System.out.println("Dados territoriais atualizados!");

                    } catch (DadosInvalidosException e) {
                        System.out.println("Erro ao atualizar: " + e.getMessage());
                    }
                }

                break;

            case 5:

                System.out.println("\n=== STATUS COMPLETO DO SISTEMA ===");

                System.out.println("\n--- SENSORES ---");


                for (Sensor sensor : sensores) {
                    String status = sensor.verificarFuncionamento()
                            ? "Funcionando" : "Indisponível";

                    System.out.println(sensor.tipo() + ": " + status);
                }

                System.out.println("\n--- DADOS DO TERRITÓRIO ---");

                System.out.println("População atendida: "
                        + territorio.getPopulacaoAtendida());

                System.out.println("Limiar de atenção: "
                        + territorio.getLimiarAtencao());

                System.out.println("Limiar de alerta: "
                        + territorio.getLimiarAlerta());

                System.out.println("Limiar crítico: "
                        + territorio.getLimiarCritico());

                System.out.println("\n--- BANCO DE DADOS ---");

                MedicaoDAO daoMedicoes = new MedicaoDAO();
                AlertaDAO daoAlertas = new AlertaDAO();

                System.out.println("Total de medições: "
                        + daoMedicoes.listarTodas().size());

                System.out.println("Total de alertas: "
                        + daoAlertas.listarTodos().size());

                break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);
    }
    
    public static void main(String[] args) {
    	
    	ConexaoBD.getInstancia().conectar();
    	
        MenuPrincipal menu = new MenuPrincipal();
        menu.iniciar();
    }
}