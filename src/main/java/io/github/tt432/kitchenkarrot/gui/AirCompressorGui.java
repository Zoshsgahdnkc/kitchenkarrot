package io.github.tt432.kitchenkarrot.gui;

import io.github.tt432.kitchenkarrot.Kitchenkarrot;
import io.github.tt432.kitchenkarrot.blockentity.AirCompressorBlockEntity;
import io.github.tt432.kitchenkarrot.gui.base.KKGui;
import io.github.tt432.kitchenkarrot.gui.widget.ProgressWidget;
import io.github.tt432.kitchenkarrot.menu.AirCompressorMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * @author DustW
 **/
public class AirCompressorGui extends KKGui<AirCompressorMenu> {

    private static final ResourceLocation GUI =
            ResourceLocation.fromNamespaceAndPath(
                    Kitchenkarrot.MOD_ID, "textures/gui/air_compressor.png");

    AirCompressorBlockEntity be;

    public AirCompressorGui(AirCompressorMenu container, Inventory inv, Component name) {
        super(container, inv, name, GUI);
        be = container.blockEntity;
    }

    @Override
    protected void init() {
        super.init();

        addRenderableWidget(
                new ProgressWidget(
                        this,
                        GUI,
                        leftPos + 105,
                        topPos + 50,
                        184,
                        0,
                        20,
                        19,
                        true,
                        () -> be.getMaxProgress(),
                        be::getProgress));
        addRenderableWidget(
                new ProgressWidget(
                        this,
                        GUI,
                        leftPos + 49,
                        topPos + 8,
                        176,
                        0,
                        8,
                        60,
                        true,
                        () -> {
                            if (be.getAtomicEnergy() == 0)
                                return Component.translatable(
                                        "info.kitchenkarrot.air_compressor.no_energy");
                            return Component.literal(be.getAtomicEnergy() + " / 12");
                        },
                        true,
                        () -> 12,
                        be::getAtomicEnergy));
    }

    @Override
    public void render(GuiGraphics p_283479_, int p_283661_, int p_281248_, float p_281886_) {
        super.render(p_283479_, p_283661_, p_281248_, p_281886_);
    }

    //    @Override
    //    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    //        super.render(matrixStack, mouseX, mouseY, partialTicks);
    //    }
}
