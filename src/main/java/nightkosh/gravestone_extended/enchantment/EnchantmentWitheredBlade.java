package nightkosh.gravestone_extended.enchantment;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EnchantmentWitheredBlade extends EnchantmentPoisonedBlade {

    public EnchantmentWitheredBlade() {
        super();
        this.setName("withered_blade");
    }

    @Override
    protected String getNameForRegistry() {
        return "gs_withered_blade";
    }

    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level) {
        if (target instanceof EntityLivingBase) {
            ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100 * level));
        }
    }
}
