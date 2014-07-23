package securecraftprotect.common.entity.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayerSCP implements IExtendedEntityProperties {
    public final static String EXT_PROP_NAME = "ExtendedPlayerSCP";
    public static final int BLINK_DATA = 20;
    private final EntityPlayer player;
    private boolean seen0096, heard0513;
    private int time, blinkSpeed, maxBlink;

    public ExtendedPlayerSCP(EntityPlayer player) {
        this.player = player;
        this.time = 0;
        this.maxBlink = 300;
        this.player.getDataWatcher().addObject(BLINK_DATA, this.maxBlink);
        this.blinkSpeed = 2;
        this.seen0096 = false;
        this.heard0513 = false;
    }

    public static void register(EntityPlayer player) {
        player.registerExtendedProperties(EXT_PROP_NAME,
                new ExtendedPlayerSCP(player));
    }

    public static ExtendedPlayerSCP get(EntityPlayer player) {
        return (ExtendedPlayerSCP) player.getExtendedProperties(EXT_PROP_NAME);
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound props = new NBTTagCompound();
        props.setInteger("Time", this.time);
        props.setInteger("Blink", this.player.getDataWatcher().getWatchableObjectInt(BLINK_DATA));
        props.setInteger("BlinkSpeed", this.blinkSpeed);
        props.setBoolean("Seen0096", this.seen0096);
        props.setBoolean("Heard0513", this.heard0513);
        compound.setTag(EXT_PROP_NAME, props);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound props = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
        this.time = props.getInteger("Time");
        this.player.getDataWatcher().updateObject(BLINK_DATA, props.getInteger("Blink"));
        this.blinkSpeed = props.getInteger("BlinkSpeed");
        this.seen0096 = props.getBoolean("Seen0096");
        this.heard0513 = props.getBoolean("Heard0513");
    }

    @Override
    public void init(Entity entity, World world) {

    }

    public int getBlink() {
        return this.player.getDataWatcher().getWatchableObjectInt(BLINK_DATA);
    }

    public void setBlink(int i) {
        this.player.getDataWatcher().updateObject(BLINK_DATA, i);
    }

    public void decreaseBlink(int i) {
        this.player.getDataWatcher().updateObject(BLINK_DATA, -i);
    }

    public int getBlinkSpeed() {
        return this.blinkSpeed;
    }

    public void setBlinkSpeed(int i) {
        this.blinkSpeed = i;
    }

    public boolean hasHeard0513() {
        return this.heard0513;
    }

    public void hear0513(boolean bool) {
        this.heard0513 = bool;
    }

    public boolean hasSeen0096() {
        return this.seen0096;
    }

    public void see0096(boolean bool) {
        this.seen0096 = bool;
    }
}
