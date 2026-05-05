package hr.java.FitnessCentar.model.Enum;

public enum vrstaGymaE {
    KomercijalniGym,
    PowerliftingGym,
    CrossFitGym,
    CombatGym;


    public static vrstaGymaE fromId(int id) {
        switch (id) {
            case 1:
                return KomercijalniGym;
            case 2:
                return PowerliftingGym;
            case 3:
                return CrossFitGym;
            case 4:
                return CombatGym;
            default:
                throw new IllegalArgumentException("Nepoznata vrijednost: " + id);
        }
    }
}
