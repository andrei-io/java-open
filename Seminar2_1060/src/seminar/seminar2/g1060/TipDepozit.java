package seminar.seminar2.g1060;

public enum TipDepozit {
    O_LUNA(30,3), DOUA_LUNI(60,4),
    TREI_LUNI(90,4), UN_AN(365,5);
    private int durata;
    private double rataD;

    TipDepozit(int durata,double rataD)
    {
        this.durata = durata;
        this.rataD=rataD;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public double getRataD() {
        return rataD;
    }

    public void setRataD(double rataD) {
        this.rataD = rataD;
    }
}
