package seminar.seminar2.g1060;

import java.io.Serializable;
import java.util.Date;

public abstract class Cont implements Comparable<Cont>, Serializable {
    protected Titular titular;
    protected Date dataDeschidere;
    protected Moneda moneda;
    protected double valoare;
    protected String sucursala;

    public Cont() {
    }

    public Cont(Titular titular, Date dataDeschidere, Moneda moneda,
                double valoare, String sucursala) throws Exception{
        Date dataCurenta = new Date();
        if (dataDeschidere.after(dataCurenta)){
            throw new Exception("Data eronata!");
        }
        this.titular = titular;
        this.dataDeschidere = dataDeschidere;
        this.moneda = moneda;
        this.valoare = valoare;
        this.sucursala = sucursala;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public Date getDataDeschidere() {
        return dataDeschidere;
    }

    public void setDataDeschidere(Date dataDeschidere) throws Exception{
        Date dataCurenta = new Date();
        if (dataDeschidere.after(dataCurenta)){
            throw new Exception("Data eronata!");
        }
        this.dataDeschidere = dataDeschidere;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public double getValoare() {
        return valoare;
    }

    public void setValoare(double valoare) {
        this.valoare = valoare;
    }

    public String getSucursala() {
        return sucursala;
    }

    public void setSucursala(String sucursala) {
        this.sucursala = sucursala;
    }

    @Override
    public String toString() {
        return "Cont{" +
                titular +
                "," + (dataDeschidere==null?"":Main.formatData.format(dataDeschidere)) +
                "," + moneda +
                "," + valoare +
                "," + sucursala +
                '}';
    }

    @Override
    public int compareTo(Cont o) {
        return dataDeschidere.compareTo(o.dataDeschidere);
    }
}
