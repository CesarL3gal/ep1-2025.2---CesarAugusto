public class Quarto {
    int numero;
    boolean ocupado;

    public Quarto(int numero){
        this.numero=numero;
        this.ocupado=false;
    }

    public int getNumero(){
        return numero;
    }
    public boolean isOcupado(){
        return ocupado;
    }

    public void setOcupado(){
        this.ocupado = true;
    }
    public void setDesocupado(){
        this.ocupado = false;
    }
}
