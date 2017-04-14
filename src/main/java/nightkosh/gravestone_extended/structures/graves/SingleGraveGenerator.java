package nightkosh.gravestone_extended.structures.graves;

import net.minecraft.util.math.ChunkPos;
import nightkosh.gravestone_extended.config.ExtendedConfig;
import nightkosh.gravestone_extended.structures.GSStructureGenerator;
import nightkosh.gravestone_extended.structures.catacombs.CatacombsGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;

import java.util.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SingleGraveGenerator implements GSStructureGenerator {
    private static SingleGraveGenerator instance = new SingleGraveGenerator();

    protected SingleGraveGenerator() {

    }

    public static SingleGraveGenerator getInstance() {
        return instance;
    }

    // chance to generate a structure
    public static final double DEFAULT_GENERATION_CHANCE = 0.1D;
    public static final byte RANGE = 100;
    protected static List<ChunkPos> structuresList = new ArrayList<>();

    @Override
    public boolean generate(World world, Random rand, int x, int z, EnumFacing direction, double chance, boolean isCommand) {
        if (!isCommand) {
            x += 7;
            z += 7;
        }
        if (isCommand || (ExtendedConfig.generateSingleGraves && canSpawnStructureAtCoords(world, x, z, chance))) {
            if (!isCommand && rand.nextInt(4) == 3) {
                new ComponentOpenedGrave(0, direction, rand, x, z).addComponentParts(world, rand);
            } else {
                new ComponentSingleGrave(0, direction, rand, x, z).addComponentParts(world, rand);
            }
            structuresList.add(new ChunkPos(x, z));
            return true;
        }

        return false;
    }

    protected static boolean canSpawnStructureAtCoords(World world, int x, int z, double chance) {
        return chance < ExtendedConfig.gravesGenerationChance && isBiomeAllowed(world, x, z) && noAnyInRange(x, z);
    }

    protected static boolean isBiomeAllowed(World world, int x, int z) {
        List<BiomeDictionary.Type> biomeTypesList = new ArrayList<>(Arrays.asList(BiomeDictionary.getTypesForBiome(world.getBiomeGenForCoords(new BlockPos(x, 0, z)))));
        return !biomeTypesList.contains(BiomeDictionary.Type.WATER) &&
                (ExtendedConfig.generateGravesInMushroomBiomes || !BiomeDictionary.getTypesForBiome(world.getBiomeGenForCoords(new BlockPos(x, 0, z))).equals(BiomeDictionary.Type.MUSHROOM));
    }

    protected static boolean noAnyInRange(int x, int z) {
        for (ChunkPos position : structuresList) {
            if (position.chunkXPos > x - RANGE && position.chunkXPos < x + RANGE
                    && position.chunkZPos > z - RANGE && position.chunkZPos < z + RANGE) {
                return false;
            }
        }

        for (ChunkPos position : CatacombsGenerator.getStructuresList()) {
            if (position.chunkXPos > x - CatacombsGenerator.CATACOMBS_RANGE && position.chunkXPos < x + CatacombsGenerator.CATACOMBS_RANGE
                    && position.chunkZPos > z - CatacombsGenerator.CATACOMBS_RANGE && position.chunkZPos < z + CatacombsGenerator.CATACOMBS_RANGE) {
                return false;
            }
        }

        return true;
    }

    public static List<ChunkPos> getStructuresList() {
        return structuresList;
    }
}
