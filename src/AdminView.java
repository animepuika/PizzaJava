public class AdminView implements IUserType {
    @Override
    public void accessUser() {
        System.out.println("ADMIN UI: manage users, menu, and orders.");
    }
}
