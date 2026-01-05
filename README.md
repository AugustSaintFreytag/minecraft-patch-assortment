# Minecraft Patch Assortment

A loose assortment of mixin-based patches to miscellaneous mods and vanilla mechanics for Minecraft 1.20.1 that would be too miniscule to warrant their own mods.

## Changes

### Disables magma block glow
When using connected textures with glowing blocks, the glowing part will always only be on the block itself and not grow or shrink based on texture. This mod disables magma block light emittance so it doesn't render with sharp seams. Glow may be reintroduced with emissive textures.

### Reduced spyglass FOV
Reduces the vanilla spyglass zoom to 0.35x of its default range. Personally, I am absolutely puzzled why Mojang would give their notoriously low render distance game a spyglass with a magnification factor this high. Without *Distant Horizons*, you'd see nothing but fog anyway.

### Disables translucent glow on *Better Nether* fireflies
Translucent blocks have depth ordering problems in Sodium 0.5.* and translucent entities can cause other visual issues. This change disables the translucent part of the model from the renderer side.

### Adds *Immersive Furniture* placement sound
Placing an *Immersive Furniture* custom block entity down does not make a sound. This mod adds a satisfying wood placement sound (as most furniture made with the mod is wooden).

### Disables *Small Ships* trailing ship particles
Bubble particle trails may have mismatching color vs. the water when playing with shader packs. Water interaction is usually already handled by other mods and the bubble trail just seems out of place. This mod disables the trail.

### Fixes *Tense Ambience* daytime detection
Forces *Tense Ambience* to use sky angle for day/night detection instead of assuming a specific hardcoded daytime tick duration. This is a mistake *many* mod developers make. Sky angle is the most reliable approach, this mod overwrites its detection. It also slightly lowers the master volume of all sounds made by *Tense Ambience*.

## License

This project was created by Saint and is licensed under the MIT license. It may be shared, modified, or redistributed as part of mod packs with basic attribution.