package hr.java.FitnessCentar.Util;

import hr.java.FitnessCentar.model.entity.Korisnik;

public class Session {
    public static Korisnik currentUser;

    private Session() {}

    public static Korisnik getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Korisnik currentUser) {
        Session.currentUser = currentUser;
    }

    public static void clear() {
        currentUser = null;
    }
}
