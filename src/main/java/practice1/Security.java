package practice1;

import com.google.common.collect.ImmutableList;

public class Security {

    private SecurityChecker securityChecker;

    public Security(SecurityChecker checker) {
        this.securityChecker = checker;
    }

    public boolean hasAccess(User user, Permission permission, ImmutableList<Permission> permissions) {
        return (user == null || permission == null ||permissions.size() == 0) ? false : (securityChecker.isAdmin())||(this.securityChecker.checkPermission(user, permission) || permissions.contains(permission));
    }
}
