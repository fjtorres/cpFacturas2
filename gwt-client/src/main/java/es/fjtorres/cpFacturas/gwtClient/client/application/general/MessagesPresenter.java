package es.fjtorres.cpFacturas.gwtClient.client.application.general;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;

import es.fjtorres.cpFacturas.gwtClient.client.application.event.DisplayMessageEvent;

public class MessagesPresenter extends PresenterWidget<MessagesPresenter.MyView> implements
        DisplayMessageEvent.DisplayMessageHandler, NavigationHandler {
    
    public interface MyView extends View {
        void addMessage(Message message);
        void clearMessage();
    }

    @Inject
    MessagesPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);
    }

    @Override
    public void onDisplayMessage(DisplayMessageEvent event) {
        getView().addMessage(event.getMessage());
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(DisplayMessageEvent.getType(), this);
    }
    
    @ProxyEvent
    @Override
    public void onNavigation(NavigationEvent pNavigationEvent) {
        
    }
}
