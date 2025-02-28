//
// MIT License
//
// Copyright (c) 2024 Incendo
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//
package org.incendo.cloud.examples.bukkit.annotations;

import java.util.Arrays;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.annotations.AnnotationParser;
import org.incendo.cloud.examples.bukkit.ExamplePlugin;
import org.incendo.cloud.examples.bukkit.annotations.feature.BuilderModifierExample;
import org.incendo.cloud.examples.bukkit.annotations.feature.CommandContainerExample;
import org.incendo.cloud.examples.bukkit.annotations.feature.EitherExample;
import org.incendo.cloud.examples.bukkit.annotations.feature.EnumExample;
import org.incendo.cloud.examples.bukkit.annotations.feature.FlagExample;
import org.incendo.cloud.examples.bukkit.annotations.feature.HelpExample;
import org.incendo.cloud.examples.bukkit.annotations.feature.InjectionExample;
import org.incendo.cloud.examples.bukkit.annotations.feature.PermissionExample;
import org.incendo.cloud.examples.bukkit.annotations.feature.RegexExample;
import org.incendo.cloud.examples.bukkit.annotations.feature.RootCommandDeletionExample;
import org.incendo.cloud.examples.bukkit.annotations.feature.StringArrayExample;
import org.incendo.cloud.examples.bukkit.annotations.feature.minecraft.LocationExample;
import org.incendo.cloud.examples.bukkit.annotations.feature.minecraft.NamespacedKeyExample;
import org.incendo.cloud.examples.bukkit.annotations.feature.minecraft.SelectorExample;

/**
 * Main entrypoint for all annotation related examples. This class is responsible for creating and configuring
 * the annotation parser. It then registers a bunch of {@link #FEATURES features} that showcase specific cloud concepts.
 */
public final class AnnotationParserExample {

    private static final List<AnnotationFeature> FEATURES = Arrays.asList(
            new BuilderModifierExample(),
            new CommandContainerExample(),
            new EitherExample(),
            new EnumExample(),
            new FlagExample(),
            new HelpExample(),
            new InjectionExample(),
            new PermissionExample(),
            new RegexExample(),
            new RootCommandDeletionExample(),
            new StringArrayExample(),
            // Minecraft-specific features
            new LocationExample(),
            new NamespacedKeyExample(),
            new SelectorExample()
    );

    private final ExamplePlugin examplePlugin;
    private final AnnotationParser<CommandSender> annotationParser;

    public AnnotationParserExample(
            final @NonNull ExamplePlugin examplePlugin,
            final @NonNull CommandManager<CommandSender> manager
    ) {
        this.examplePlugin = examplePlugin;
        this.annotationParser = new AnnotationParser<>(manager, CommandSender.class);

        // Set up the example modules.
        this.setupExamples();
    }

    private void setupExamples() {
        FEATURES.forEach(feature -> feature.registerFeature(this.examplePlugin, this.annotationParser));
    }
}
