package models;

public class Klasse {
    String klasseName;
    int antall;
    int alder;

    public Klasse(String klasseName, int antall, int alder) {
        this.klasseName = klasseName;
        this.antall = antall;
        this.alder = alder;
    }

    @Override
    public String toString() {
        return "Klasse{" +
            "klasseName='" + klasseName + '\'' +
            ", antall=" + antall +
            ", alder=" + alder +
            '}';
    }

    public String getKlasseName() {
        return klasseName;
    }

    public void setKlasseName(String klasseName) {
        this.klasseName = klasseName;
    }

    public int getAntall() {
        return antall;
    }

    public void setAntall(int antall) {
        this.antall = antall;
    }

    public int getAlder() {
        return alder;
    }

    public void setAlder(int alder) {
        this.alder = alder;
    }
}
