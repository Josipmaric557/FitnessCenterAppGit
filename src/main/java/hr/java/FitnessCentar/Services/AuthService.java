package hr.java.FitnessCentar.Services;

import hr.java.FitnessCentar.Repository.interfaces.KorisnikRepository;
import hr.java.FitnessCentar.Util.PasswordUtil;
import hr.java.FitnessCentar.Util.Session;
import hr.java.FitnessCentar.model.Enum.UlogaE;
import hr.java.FitnessCentar.model.entity.Korisnik;

import java.util.Optional;

public class AuthService {
    private final KorisnikRepository korisnikRepository;

    public AuthService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    // LOGIN LOGIKA
    public boolean login(String username, String password) throws Exception {



        Optional<Korisnik> userOpt = korisnikRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return false;
        }

        Korisnik user = userOpt.get();

        if (!PasswordUtil.checkPassword(password, user.getLozinka())) {
            return false;
        }

        Session.setCurrentUser(user);

        return true;
    }

    public void register(Korisnik korisnik) throws Exception {
        korisnik.SetLozinka(
                PasswordUtil.hashPassword(korisnik.getLozinka())
        );
        korisnikRepository.save(korisnik);
    }

    // ROLE CHECK
    public boolean isAdmin() {
        return Session.getCurrentUser() != null &&
                Session.getCurrentUser().GetUlogaE() == UlogaE.Admin;
    }

    public boolean isLoggedIn() {
        return Session.getCurrentUser() != null;
    }

    // LOGOUT
    public void logout() {
        Session.clear();
    }
}
