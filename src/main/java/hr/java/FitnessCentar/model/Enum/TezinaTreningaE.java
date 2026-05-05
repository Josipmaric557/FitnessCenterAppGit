package hr.java.FitnessCentar.model.Enum;

public enum TezinaTreningaE {
    slabo,
    srednje,
    tesko;

    public static TezinaTreningaE fromId(int id) {
        switch (id) {
            case 1:
                return slabo;
            case 2:
                return srednje;
            case 3:
                return tesko;
            default:
                throw new IllegalArgumentException("Nepoznata vrijednost: " + id);
        }
    }
}
