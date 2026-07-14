package hr.java.FitnessCentar.Services;

import hr.java.FitnessCentar.Repository.interfaces.AdminRepository;

public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void resetDatabase() throws Exception {
        adminRepository.resetDatabase();
    }
}
