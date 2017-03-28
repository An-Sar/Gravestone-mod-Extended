package nightkosh.gravestone_extended.entity.helper;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import nightkosh.gravestone.helper.GroupOfGravesSpawnerHelper;
import nightkosh.gravestone.tileentity.TileEntityGraveStone;
import nightkosh.gravestone_extended.tileentity.GraveStoneSpawn;

import java.util.ArrayList;
import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityGroupOfGravesMobSpawnerHelper extends GroupOfGravesSpawnerHelper {

    private List<TileEntityGraveStone> gravesTEList;
    private GraveStoneSpawn graveStoneSpawn;
    private boolean canMobsBeSpawned;

    public EntityGroupOfGravesMobSpawnerHelper(World worldIn) {
        super(worldIn);
        graveStoneSpawn = new GraveStoneSpawn(this);
    }

    public void addGraveTe(TileEntityGraveStone te) {
        gravesTEList.add(te);
    }

    public boolean canMobsBeSpawned() {
        return canMobsBeSpawned;
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();

        if (!getIWorld().isRemote) {
            if (gravesTEList != null && gravesTEList.isEmpty()) {
                this.setDead();
            } else {
                canMobsBeSpawned = graveStoneSpawn.isMobSpawnAllowed();
                graveStoneSpawn.updateDelay();
            }
        }
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tagCompund) {
        NBTTagList tagList = tagCompund.getTagList("gravesList", 10);
        if (tagList != null && !tagList.hasNoTags()) {
            gravesTEList = new ArrayList<TileEntityGraveStone>(tagList.tagCount());
            for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound posTag = (NBTTagCompound) tagList.get(i);
                if (posTag != null && posTag.hasKey("x") && posTag.hasKey("y") && posTag.hasKey("z")) {
                    BlockPos pos = new BlockPos(posTag.getInteger("x"), posTag.getInteger("y"), posTag.getInteger("z"));
                    TileEntity te = worldObj.getTileEntity(pos);
                    if (te != null && te instanceof TileEntityGraveStone) {
                        gravesTEList.add((TileEntityGraveStone) te);
                    }
                }
            }
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tagCompound) {
        if (gravesTEList != null && !gravesTEList.isEmpty()) {
            NBTTagList tagList = new NBTTagList();
            for (TileEntityGraveStone te : gravesTEList) {
                if (te != null) {
                    BlockPos pos = te.getPos();
                    NBTTagCompound posTag = new NBTTagCompound();
                    posTag.setInteger("x", pos.getX());
                    posTag.setInteger("y", pos.getY());
                    posTag.setInteger("z", pos.getZ());
                    tagList.appendTag(posTag);
                }
            }

            tagCompound.setTag("gravesList", tagList);
        }
    }

    @Override
    public boolean haveSpawnerHelper() {
        return false;
    }

    @Override
    public EntityGroupOfGravesMobSpawnerHelper getSpawnerHelper() {
        return null;
    }
}
