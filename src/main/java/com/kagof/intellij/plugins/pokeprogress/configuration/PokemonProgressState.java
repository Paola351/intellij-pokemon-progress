package com.kagof.intellij.plugins.pokeprogress.configuration;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableMap;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.kagof.intellij.plugins.pokeprogress.Pokemon;

@State(
    name = "com.kagof.intellij.plugins.pokeprogress.configuration.PokemonProgressState",
    storages = {@Storage("PokemonProgress.xml")}
)
public class PokemonProgressState implements PersistentStateComponent<PokemonProgressState> {

    public Map<String, Boolean> pokemonNumbersEnabled = ImmutableMap
        .copyOf(Pokemon.DEFAULT_POKEMON.keySet().stream().collect(Collectors.toMap(Function.identity(), p -> true)));
    public boolean drawSprites = true;
    public boolean addToolTips = true;

    public static PokemonProgressState getInstance() {
        try {
            return ServiceManager.getService(PokemonProgressState.class);
        }catch (Exception e){
            return new PokemonProgressState();
        }
    }

    @Override
    public PokemonProgressState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull final PokemonProgressState state) {
        XmlSerializerUtil.copyBean(state, this);
    }

}
