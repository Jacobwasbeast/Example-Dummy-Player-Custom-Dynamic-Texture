package jacobwasbeast.examplemod.Entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class ExampleDummyPlayer extends LivingEntity {
    protected static final TrackedData<String> Tracked_url = DataTracker.registerData(ExampleDummyPlayer.class, TrackedDataHandlerRegistry.STRING);

    public ExampleDummyPlayer(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(Tracked_url, "https://s.namemc.com/i/63455d7069b397c2.png");
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        if (!nbt.contains("url")) {
            nbt.putString("url", dataTracker.get(Tracked_url));
        }
        return super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        if (nbt.contains("url")) {
            dataTracker.set(Tracked_url, nbt.getString("url"));;
        }
        super.readNbt(nbt);
    }
    public void setUrl(String url) {
        this.dataTracker.set(Tracked_url, url);
        NbtCompound nbt = new NbtCompound();
        this.writeNbt(nbt);
        nbt.putString("url", url);
        this.readNbt(nbt);
    }

    public String getUrl() {
        return this.dataTracker.get(Tracked_url);
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        Iterable<ItemStack> stack = new Iterable<ItemStack>() {
            @NotNull
            @Override
            public Iterator<ItemStack> iterator() {
                return new Iterator<ItemStack>() {
                    @Override
                    public boolean hasNext() {
                        return false;
                    }

                    @Override
                    public ItemStack next() {
                        return null;
                    }
                };
            }
        };
        return stack;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

    }

    @Override
    public Arm getMainArm() {
        return null;
    }

    @Override
    public boolean isCustomNameVisible() {
        return false;
    }

    @Override
    public void tick() {
        if (!this.hasCustomName()) {
            this.setCustomName(Text.literal("ExampleDummyPlayer"));
        }
    }
}
