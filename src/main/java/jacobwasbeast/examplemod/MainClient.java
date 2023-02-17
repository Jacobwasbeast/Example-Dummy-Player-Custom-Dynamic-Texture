package jacobwasbeast.examplemod;

import jacobwasbeast.examplemod.Entity.ExampleDummyPlayerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class MainClient implements ClientModInitializer {
    public static final Map<String, Identifier> textureMap = new HashMap<>();

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Main.PLAYER_ENTITY_TYPE, (context) -> {
            return new ExampleDummyPlayerRenderer(context, 0.5f);
        });
}
}
