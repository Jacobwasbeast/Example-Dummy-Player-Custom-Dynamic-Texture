package jacobwasbeast.examplemod;

import jacobwasbeast.examplemod.Entity.ExampleDummyPlayer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;

public class Main implements ModInitializer {
    public static final EntityType<ExampleDummyPlayer> PLAYER_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("examplemod", "exampledummyplayer"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, ExampleDummyPlayer::new).dimensions(EntityDimensions.fixed(0.75f, 2f)).build()
    );
    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(PLAYER_ENTITY_TYPE, ExampleDummyPlayer.createLivingAttributes());
    }
}
