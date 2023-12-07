package Repository;

import Model.TaiKhoan;

public class Auth {

    public static TaiKhoan User = null;

    public static void clear() {
        Auth.User = null;
    }

    public static Boolean isLogin() {
        return Auth.User != null;
    }

    public static Boolean isManager() {
        return Auth.isLogin() && User.getVaiTro();
    }
}
