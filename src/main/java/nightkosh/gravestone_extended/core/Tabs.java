package nightkosh.gravestone_extended.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import nightkosh.gravestone_extended.block.enums.EnumMemorials;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Tabs extends nightkosh.gravestone.core.Tabs {

    public static CreativeTabs memorialsTab;
    public static CreativeTabs otherItemsTab;

    public static void registration() {
        memorialsTab = new CreativeTabs("tabGSMemorials") {
            @Override
            public ItemStack getIconItemStack() {
                ItemStack stack = new ItemStack(GSBlock.memorial, 1, 0);
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setInteger("Type", EnumMemorials.STONE_CREEPER_STATUE.ordinal());
                stack.setTagCompound(nbt);
                return stack;
            }

            @Override
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem() {
                return Item.getItemFromBlock(GSBlock.memorial);
            }
        };

        otherItemsTab = new CreativeTabs("tabGSOther") {
            @Override
            public ItemStack getIconItemStack() {
                return new ItemStack(GSBlock.skullCandle, 1, 1);
            }

            @Override
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem() {
                return Item.getItemFromBlock(GSBlock.skullCandle);
            }
        };
    }
}
