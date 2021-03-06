package nightkosh.gravestone_extended.item.tools;

import nightkosh.gravestone_extended.core.ModInfo;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemDiamondBoneHoe extends ItemBoneHoe {

    public ItemDiamondBoneHoe() {
        super(ToolMaterial.DIAMOND);
        this.setUnlocalizedName("gravestone.bone_hoe_diamond");
        this.setRegistryName(ModInfo.ID, "gs_bone_hoe_diamond");
    }
}
