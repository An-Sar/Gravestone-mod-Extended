package nightkosh.gravestone_extended.structures.village.undertaker;

import nightkosh.gravestone_extended.structures.GSStructureGenerator;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class VillageUndertakerGenerator implements GSStructureGenerator {
    public static final VillageUndertakerGenerator INSTANCE = new VillageUndertakerGenerator();

    @Override
    public boolean generate(World world, Random rand, int x, int z, EnumFacing facing, double chance, boolean isCommand) {
        if (isCommand) {
            StructureBoundingBox boundingBox = ComponentVillageUndertaker.getBoundingBox(facing, x, z);
            new ComponentVillageUndertaker(new StructureVillagePieces.Start(), 0, rand, boundingBox, facing)
                    .generateComponent(world, rand, boundingBox, false);
            return true;
        }
        return false;
    }
}