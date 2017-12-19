package nightkosh.gravestone_extended.potion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;
import nightkosh.gravestone_extended.core.ModInfo;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PotionPurification extends PotionBase {

    public PotionPurification() {
        super(false, 0xffffff);
        this.setRegistryName(ModInfo.ID, "gs_purification_potion");
        this.setPotionName("potion.purification.title");
    }

    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entity, int amplifier, double health) {
        Collection<PotionEffect> potions = entity.getActivePotionEffects();
        List<Potion> potionsToRemove = new ArrayList<>();
        for (PotionEffect potion : potions) {
            if (potion.getPotion().isBadEffect()) {
                potionsToRemove.add(potion.getPotion());
            }
        }
        for (Potion potion : potionsToRemove) {
            entity.removePotionEffect(potion);
        }

        if (entity.isBurning()) {
            entity.extinguish();
        }
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public boolean hasStatusIcon() {
        return false;
    }

    public static void applyPotionOnBlocks(EntityPotion entityPotion) {
        int range = 5;
        Map<BlockPos, TileEntity> teMap = entityPotion.world.getChunkFromBlockCoords(entityPotion.getPosition()).getTileEntityMap();
        teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX + range, entityPotion.posY, entityPotion.posZ)).getTileEntityMap());
        teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX - range, entityPotion.posY, entityPotion.posZ)).getTileEntityMap());
        teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX, entityPotion.posY, entityPotion.posZ + range)).getTileEntityMap());
        teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX, entityPotion.posY, entityPotion.posZ - range)).getTileEntityMap());
        teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX + range, entityPotion.posY, entityPotion.posZ + range)).getTileEntityMap());
        teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX + range, entityPotion.posY, entityPotion.posZ - range)).getTileEntityMap());
        teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX - range, entityPotion.posY, entityPotion.posZ + range)).getTileEntityMap());
        teMap.putAll(entityPotion.world.getChunkFromBlockCoords(new BlockPos(entityPotion.posX - range, entityPotion.posY, entityPotion.posZ - range)).getTileEntityMap());

        for (Map.Entry<BlockPos, TileEntity> teEntry : teMap.entrySet()) {
            if (teEntry != null && teEntry.getValue() instanceof TileEntityGraveStone &&
                    (teEntry.getKey().getX() >= entityPotion.posX - range && teEntry.getKey().getX() <= entityPotion.posX + range &&
                            teEntry.getKey().getZ() >= entityPotion.posZ - range && teEntry.getKey().getZ() <= entityPotion.posZ + range &&
                            teEntry.getKey().getY() >= entityPotion.posY - range && teEntry.getKey().getY() <= entityPotion.posY + range)) {
                ((TileEntityGraveStone) teEntry.getValue()).setPurified(true);
            }
        }
    }
}
