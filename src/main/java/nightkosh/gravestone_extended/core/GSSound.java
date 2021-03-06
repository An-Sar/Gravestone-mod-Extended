package nightkosh.gravestone_extended.core;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@GameRegistry.ObjectHolder(ModInfo.ID)
public class GSSound {

    @GameRegistry.ObjectHolder("blocks.fluid.bubbling")
    public static SoundEvent BUBBLING = createSoundEvent("blocks.fluid.bubbling");

    @GameRegistry.ObjectHolder("blocks.fluid.drop_of_acid")
    public static SoundEvent DROP_OF_ACID = createSoundEvent("blocks.fluid.drop_of_acid");

    private static SoundEvent createSoundEvent(final String soundName) {
        final ResourceLocation soundID = new ResourceLocation(ModInfo.ID, soundName);
        return new SoundEvent(soundID).setRegistryName(soundID);
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
            event.getRegistry().registerAll(BUBBLING, DROP_OF_ACID);
        }
    }
}
