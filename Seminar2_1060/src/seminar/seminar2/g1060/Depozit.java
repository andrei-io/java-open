package seminar.seminar2.g1060;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Depozit extends Cont implements OperatiuniDepozite {
    private TipDepozit tipDepozit;
    private int codContract;

    //private Titular imputernicit;

    private static final long serialVersionUID=1L;

    public Depozit() {
    }

    public Depozit(Titular titular, Date dataDeschidere,
                   Moneda moneda, double valoare, String sucursala,
                   TipDepozit tipDepozit, int codContract) throws Exception {
        super(titular, dataDeschidere, moneda, valoare, sucursala);
        this.tipDepozit = tipDepozit;
        this.codContract = codContract;
    }

    public Depozit(int codContract) {
        this.codContract = codContract;
    }

    public TipDepozit getTipDepozit() {
        return tipDepozit;
    }

    public void setTipDepozit(TipDepozit tipDepozit) {
        this.tipDepozit = tipDepozit;
    }

    public int getCodContract() {
        return codContract;
    }

    public void setCodContract(int codContract) {
        this.codContract = codContract;
    }

    @Override
    public String toString() {
        return super.toString()+
                "\n{" + tipDepozit +
                "," + codContract +
                "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depozit depozit = (Depozit) o;
        return codContract == depozit.codContract;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codContract);
    }

    @Override
    public double calculDobanda() {
        Date dataCurenta = new Date();
        long numarZile = TimeUnit.MILLISECONDS.toDays(
                dataCurenta.getTime()-dataDeschidere.getTime()
        );
        double dobanda = (valoare*tipDepozit.getRataD()*numarZile)/(365*100.0);
        return dobanda;
    }
}
