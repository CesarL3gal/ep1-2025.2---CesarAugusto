import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

public class CSV {

    public static void CSV_Paciente(Paciente Paciente){

            try (FileWriter CSV = new FileWriter("Pacientes.csv",true)){

                CSV.write(Paciente.nome + ";" + Paciente.cpf + ";" + Paciente.idade + ";" + Paciente.telefone + ";" + Paciente.planoSaude + ";" +
                        Paciente.dinheiro + "\n");
            }catch (IOException erro){
                System.out.println("Erro ao Cadastrar Paciente!");
            }

        }

        public static void CSV_Medico(Medico Medico){

            try (FileWriter CSV =new FileWriter("Medicos.csv",true)){

                CSV.write(Medico.nome + ";" + Medico.cpf + ";" + Medico.idade + ";" + Medico.telefone + ";" + Medico.dinheiro + ";" + Medico.especializacao + ";" + Medico.preco + "\n");

            }catch (IOException erro){

                System.out.println("Erro ao Cadastrar Médico!");

            }

        }

        public static void CSV_Consulta(Consultas Consultas){

            try (FileWriter CSV =new FileWriter("Consultas.csv",true)){

                CSV.write(Consultas.paciente + ";" + Consultas.medico + ";" + Consultas.data + ";" + Consultas.hora +
                        ";" + Consultas.motivo + "\n");

            }catch (IOException erro){

                System.out.println("Erro ao Cadastrar Consulta!");

            }
    }



    //Area das Leituras

    public static void lerPaciente() {
        try{
            BufferedReader lerPaciente = new BufferedReader(new FileReader("Pacientes.CSV"));
            String linha;
            while ((linha = lerPaciente.readLine()) != null) {

                String[] partes = linha.split(";");
                for (int i = 0; i < partes.length; i++) {
                    System.out.printf(partes[i] + " ");
                }
                System.out.println();

            }
        }
        catch(FileNotFoundException e){
            System.out.println("Não achou o arquivos");
        }
        catch(IOException e){
            System.out.println("Deu erro na leitura rapaiz");
        }
    }
    //@verride
    public static void lerPaciente(ArrayList<String> lista) {
        try{
            BufferedReader lerPaciente = new BufferedReader(new FileReader("Pacientes.CSV"));
            String linha;
            while ((linha = lerPaciente.readLine()) != null) {

                lista.add(linha);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Não achou o arquivos");
        }
        catch(IOException e){
            System.out.println("Deu erro na leitura rapaiz");
        }
    }
    public int compPac(String cpf){
return 0;
    }

    public static void lerMedico() {
        try{
            BufferedReader lerMedico = new BufferedReader(new FileReader("Medicos.CSV"));
            String linha;
            while ((linha = lerMedico.readLine()) != null) {

                String[] partes = linha.split(";");
                for (int i = 0; i < partes.length; i++) {
                    System.out.printf(partes[i] + " ");
                }
                System.out.println();

            }
        }
        catch(FileNotFoundException e){
            System.out.println("Não achou o arquivos");
        }
        catch(IOException e){
            System.out.println("Deu erro na leitura rapaiz");
        }
    }


    public static void lerConsulta() {
        try{
            BufferedReader lerConsultas = new BufferedReader(new FileReader("Consultas.CSV"));
            String linha;
            while ((linha = lerConsultas.readLine()) != null) {

                String[] partes = linha.split(";");
                for (int i = 0; i < partes.length; i++) {
                    System.out.printf(partes[i] + " ");
                }
                System.out.println();


            }
        }
        catch(FileNotFoundException e){
            System.out.println("Não achou o arquivos");
        }
        catch(IOException e){
            System.out.println("Deu erro na leitura rapaiz");
        }
    }
}
