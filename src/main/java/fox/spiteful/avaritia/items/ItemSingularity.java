package fox.spiteful.avaritia.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import java.util.List;

public class ItemSingularity extends Item {

    public static final String[] types = new String[]{"iron", "gold", "lapis"};
    public static final int[] colors = new int[]{0xEFEFEF, 0xeaee57, 0x5a82e2};
    public static final int[] colors2 = new int[]{0x7F7F7F, 0xdba213, 0x224baf};
    public static IIcon background;
    public static IIcon foreground;

    public ItemSingularity(){
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setUnlocalizedName("avaritia_singularity");
        this.setTextureName("avaritia:singularity");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemstack, int renderpass) {
    	//System.out.println(foreground);
        return renderpass == 0 ? colors2[itemstack.getItemDamage() % colors.length] : colors[itemstack.getItemDamage() % colors2.length];
    	//return 0xFFFFFF;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int i = MathHelper.clamp_int(stack.getItemDamage(), 0, types.length);
        return "item.singularity_" + types[i];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int j = 0; j < types.length; ++j) {
            list.add(new ItemStack(item, 1, j));
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister ir) {
    	foreground = ir.registerIcon("avaritia:singularity");
    	background = ir.registerIcon("avaritia:singularity2");
    }
    
    @Override
    public IIcon getIcon(ItemStack stack, int pass)
    {
    	if (pass == 0) { return background; }
    	return foreground;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
}
