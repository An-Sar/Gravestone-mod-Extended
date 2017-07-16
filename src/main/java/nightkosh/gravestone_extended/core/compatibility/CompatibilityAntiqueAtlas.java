package nightkosh.gravestone_extended.core.compatibility;

import nightkosh.gravestone_extended.config.ExtendedConfig;
import net.minecraft.entity.player.EntityPlayer;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CompatibilityAntiqueAtlas {

    protected static boolean isInstalled = false;

    private CompatibilityAntiqueAtlas() {
    }

    public static void placeDeathMarkerAtDeath(EntityPlayer player) {
        if (isInstalled() && ExtendedConfig.enableAntiqueAtlasDeathMarkers) {
//            List<Integer> atlasesIdList = AtlasAPI.getPlayerAtlases(player);
//            MarkerAPI markerAPI = AtlasAPI.getMarkerAPI();
//            if (markerAPI != null && atlasesIdList != null) {
//                for (Integer atlasId : atlasesIdList) {
//                    markerAPI.putMarker(player.getEntityWorld(), true, atlasId, "tomb", player.getCombatTracker().getDeathMessage().getUnformattedText(), (int) player.getPosition().getX(), (int) player.getPosition().getZ());
//                }
//            }
        }

    }

    public static boolean isInstalled() {
        return isInstalled;
    }
}
