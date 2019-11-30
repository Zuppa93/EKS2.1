package eks.prak.zwei.entities;

public class Transaktion {
    public enum Transaktionstyp{
        EINZAHLUNG,AUSZAHLUNG
    }
    private Transaktionstyp transaktionstyp;
    private int betrag;
    private Konto konto;

    public Transaktion(Transaktionstyp typ, int betrag, Konto konto){
        transaktionstyp = typ;
        this.betrag = betrag;
        this.konto = konto;
    }

    public Transaktionstyp getTransaktionstyp(){
        return this.transaktionstyp;
    }

    public int getBetrag(){
        return this.betrag;
    }
}
