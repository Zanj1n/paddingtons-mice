package yalter.mousetweaks.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents;
import yalter.mousetweaks.Main;
import yalter.mousetweaks.MouseButton;

public class MouseTweaksFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Main.initialize();

        ScreenEvents.BEFORE_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            ScreenMouseEvents.allowMouseClick(screen).register((s, mouseX, mouseY, button) -> {
                MouseButton mtButton = MouseButton.fromEventButton(button);
                if (mtButton != null)
                    return !Main.onMouseClicked(s, mouseX, mouseY, mtButton);
                return true;
            });

            ScreenMouseEvents.allowMouseRelease(screen).register((s, mouseX, mouseY, button) -> {
                MouseButton mtButton = MouseButton.fromEventButton(button);
                if (mtButton != null)
                    return !Main.onMouseReleased(s, mouseX, mouseY, mtButton);
                return true;
            });

            ScreenMouseEvents.afterMouseScroll(screen).register((s, mouseX, mouseY, horiz, vert) ->
                    Main.onMouseScrolled(s, mouseX, mouseY, vert));
        });
    }
}
