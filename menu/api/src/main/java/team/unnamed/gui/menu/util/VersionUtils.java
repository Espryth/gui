package team.unnamed.gui.menu.util;

import org.bukkit.Bukkit;

public final class VersionUtils {

    public static final String SERVER_VERSION = Bukkit.getServer()
            .getClass().getPackage()
            .getName().split("\\.")[3]
            .substring(1);

    public static final int SERVER_VERSION_INT = Integer.parseInt(
            SERVER_VERSION.replace("1_", "")
                    .replaceAll("_R\\d", "")
    );

    private VersionUtils() {
        // the class shouldn't be instantiated
        throw new UnsupportedOperationException();
    }

}