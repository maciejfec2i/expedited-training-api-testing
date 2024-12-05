package com.bestbuy.utils.junit.extensions;

import com.bestbuy.utils.string.Humanise;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.commons.text.WordUtils;
import org.junit.jupiter.api.extension.*;

public class ActorInjector implements BeforeEachCallback, ParameterResolver {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        OnStage.setTheStage(OnlineCast.whereEveryoneCan(CallAnApi.at("http://localhost:3030")));
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        System.out.println(parameterContext);
        return parameterContext.getParameter().getType().equals(Actor.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        String actorName = parameterContext.getParameter().getName();
        return OnStage.theActorCalled(WordUtils.capitalize(Humanise.theString(actorName)));
    }
}
