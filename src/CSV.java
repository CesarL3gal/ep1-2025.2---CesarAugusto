import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

public class CSV {


    //Area das Escritas

    //teste
    public static void CSV_Paciente(Paciente Paciente) {

        try (FileWriter CSV = new FileWriter("Pacientes.csv", true)) {

            CSV.write(Paciente.nome + ";" + Paciente.cpf + ";" + Paciente.idade + ";" + Paciente.planoSaude + "\n");
        } catch (IOException erro) {
            System.out.println("Erro ao Cadastrar Paciente!");
        }

    }

    public static void CSV_Medico(Medico Medico) {

        try (FileWriter CSV = new FileWriter("Medicos.csv", true)) {

            CSV.write(Medico.nome + ";" + Medico.cpf + ";" + Medico.crm +  ";" + Medico.idade + ";" + Medico.especializacao + "\n");

        } catch (IOException erro) {

            System.out.println("Erro ao Cadastrar Médico!");

        }

    }

    public static void CSV_Consulta(Consultas Consultas) {

        try (FileWriter CSV = new FileWriter("Consultas.csv", true)) {

            CSV.write(Consultas.paciente.cpf + ";" + Consultas.medico.crm + ";" + Consultas.data + ";" + Consultas.motivo + "\n");

        } catch (IOException erro) {

            System.out.println("Erro ao Cadastrar Consulta!");

        }
    }

//    public static void CSV_Internacao(Internacao Internacao){
//        try (FileWriter CSV = new FileWriter("Internação.csv", true)){
//            CSV.write();
//        }
//        catch (IOException erro) {
//            System.out.println("Erro ao Cadastrar Internação!");
//        }
//    }






    //Area das Leituras


    // Resetar o Array List, usar toda vez que faz uma modificação, pra garantir que fique em tempo real
// Retorna uma leitura de um arquivo CSV como ArrayList
//Reler CSV dos Paceintes
    public static ArrayList<Paciente> relerPaciente() {
        ArrayList<Paciente> lista = new ArrayList<Paciente>();
        try {

            BufferedReader lerPaciente = new BufferedReader(new FileReader("Pacientes.CSV"));
            String linha;
            while ((linha = lerPaciente.readLine()) != null) {
                Paciente paciente = new Paciente(linha);
                lista.add(paciente);
            }
            return lista;
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivos");
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
        return lista;
    }

    //Reler CSV dos Médicos
    public static ArrayList<Medico> relerMedico() {
        ArrayList<Medico> lista = new ArrayList<>();
        try {
            BufferedReader lerMedico = new BufferedReader(new FileReader("Medicos.CSV"));
            String linha;
            while ((linha = lerMedico.readLine()) != null) {
                Medico medico = new Medico(linha);
                lista.add(medico);
            }
            return lista;
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivos");
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
        return lista;
    }


    //Reler CSV das Consultas
    public static ArrayList<Consultas> relerConsulta(ArrayList<Paciente> paciente, ArrayList<Medico> medico) {
        ArrayList<Consultas> lista = new ArrayList<>();
        try {

            BufferedReader lerConsulta = new BufferedReader(new FileReader("Consultas.CSV"));
            String linha;
            while ((linha = lerConsulta.readLine()) != null) {
                String[] partes = linha.split(";");
                Consultas consultas = new Consultas(linha,paciente,medico);
                lista.add(consultas);
            }
            return lista;
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivos");
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
        return lista;
    }

    public static ArrayList<Internacao> relerInternacao(ArrayList<Paciente> paciente, ArrayList<Medico> medico) {
        ArrayList<Internacao> lista = new ArrayList<>();
        try {
            BufferedReader lerInternacao = new BufferedReader(new FileReader("Internacao.CSV"));
            String linha;
            while ((linha = lerInternacao.readLine()) != null) {
                String[] partes = linha.split(";");
                Internacao internacao = new Internacao(linha, paciente, medico);
                lista.add(internacao);
            }
            return lista;
        } catch (FileNotFoundException e) {
            System.out.println("Não achou o arquivos");
        } catch (IOException e) {
            System.out.println("Deu erro na leitura rapaiz");
        }
        return lista;
    }
}