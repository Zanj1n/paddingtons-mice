package yalter.mousetweaks.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.minecraft.client.MouseHandler;
import net.minecraft.client.gui.screens.Screen;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import yalter.mousetweaks.Main;
import yalter.mousetweaks.MouseButton;

@Mixin(MouseHandler.class)
public abstract class MixinMouseHandler {
    @WrapOperation(method = "handleAccumulatedMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;mouseDragged(DDIDD)Z"))
    private boolean onMouseDragged(Screen screen, double mouseX, double mouseY, int button, double dx, double dy, Operation<Boolean> operation) {
        MouseButton mtButton = MouseButton.fromEventButton(button);
        if (mtButton != null) {
            if (Main.onMouseDrag(screen, mouseX, mouseY, mtButton)) {
                return true;
            }
        }

        return operation.call(screen, mouseX, mouseY, button, dx, dy);
    }
}
