package jacobwasbeast.examplemod.Entity;

import com.mojang.blaze3d.systems.RenderSystem;
import jacobwasbeast.examplemod.MainClient;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class ExampleDummyPlayerRenderer extends LivingEntityRenderer {


    public ExampleDummyPlayerRenderer(EntityRendererFactory.Context ctx, float shadowRadius) {
        super(ctx, new PlayerEntityModel(ctx.getPart(EntityModelLayers.PLAYER), false), shadowRadius);
    }

    @Override
    public Identifier getTexture(Entity entity) {
        int i = 0;
        if (entity instanceof ExampleDummyPlayer) {
            ExampleDummyPlayer dummyEntity = (ExampleDummyPlayer) entity;
            String url = dummyEntity.getUrl();
            String entityId = url.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(Locale.ROOT);
            // Check if the texture has already been registered
            if (MainClient.textureMap.containsKey(entityId)) {
                return MainClient.textureMap.get(entityId);
            }

            // Create NativeImage from URL
            NativeImage image = null;
            try {
                // Fetch the image from the URL
                URL imageURL = new URL(url);
                InputStream imageStream = imageURL.openStream();

                // Convert the image to a NativeImage
                image = NativeImage.read(imageStream);
            } catch (Exception e) {
                System.out.println("Error loading image from URL: " + url + " - " + e.toString());
                return new Identifier("minecraft", "textures/entity/player/wide/steve.png");
            }

            // Apply the texture to the player
            TextureManager textureManager = this.dispatcher.textureManager;
            NativeImageBackedTexture texture = new NativeImageBackedTexture(image);
            Identifier textureId = new Identifier("modid", "dummyplayertexture" + entityId);
            textureManager.registerTexture(textureId, texture);

            // Store the texture UUID in the texture map to prevent lag
            MainClient.textureMap.put(entityId, textureId);

            return textureId;
        }
        return new Identifier("minecraft", "textures/entity/player/wide/steve.png");
    }
}