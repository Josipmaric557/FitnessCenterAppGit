package hr.java.FitnessCentar.model.Enum;

public enum programTreningaE {
    CrossFit,
    Bodybuilding,
    FunkcionalniTrening,
    GrupniFitness,
    BorilackaVjestina;

    public static programTreningaE fromId(int id) {
        switch (id) {
            case 1:
                return CrossFit;
            case 2:
                return Bodybuilding;
            case 3:
                return FunkcionalniTrening;
            case 4:
                return GrupniFitness;
            case 5:
                return BorilackaVjestina;
            default:
                throw new IllegalArgumentException("Nepoznata vrijednost: " + id);
        }
    }
}
