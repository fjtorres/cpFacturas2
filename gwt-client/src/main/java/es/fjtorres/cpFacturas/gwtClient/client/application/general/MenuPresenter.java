package es.fjtorres.cpFacturas.gwtClient.client.application.general;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class MenuPresenter extends PresenterWidget<MenuPresenter.MyView> {

    public interface MyView extends View {

    }

    @Inject
    public MenuPresenter(final EventBus eventBus, final MyView view) {
        super(eventBus, view);
    }
}
