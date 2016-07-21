package nightkosh.gravestone_extended.helper;

import nightkosh.gravestone.helper.ISpawner;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;
import nightkosh.gravestone_extended.tileentity.GraveStoneSpawn;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveSpawnerHelper extends nightkosh.gravestone.helper.GraveSpawnerHelper {

    public static void setGraveSpawnerHelper() {
        TileEntityGraveStone.graveSpawnerHelper = new GraveSpawnerHelper();
    }

    public ISpawner getSpawner(TileEntityGraveStone te) {
        return new GraveStoneSpawn(te);
    }
}
