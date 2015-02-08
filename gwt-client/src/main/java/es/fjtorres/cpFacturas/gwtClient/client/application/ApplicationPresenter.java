package es.fjtorres.cpFacturas.gwtClient.client.application;

import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

import es.fjtorres.cpFacturas.gwtClient.client.application.general.MenuPresenter;
import es.fjtorres.cpFacturas.gwtClient.client.application.general.MessagesPresenter;

public class ApplicationPresenter extends
        Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {

    public interface MyView extends View {

    }

    @ProxyStandard
    @NoGatekeeper
    public interface MyProxy extends Proxy<ApplicationPresenter> {

    }

    /**
     * Use this in leaf presenters, inside their {@link #revealInParent} method.
     */
    @ContentSlot
    public static final GwtEvent.Type<RevealContentHandler<?>> SLOT_MAIN_CONTENT = new GwtEvent.Type<RevealContentHandler<?>>();
    
    public static final Object SLOT_MESSAGES_CONTENT = new Object();
    public static final Object SLOT_MENU_CONTENT = new Object();

    private final MessagesPresenter messagesPresenter;
    
    private final MenuPresenter menuPresenter;

    @Inject
    public ApplicationPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
            final MessagesPresenter pMessagesPresenter, final MenuPresenter pMenuPresenter) {
        super(eventBus, view, proxy, RevealType.RootLayout);

        this.messagesPresenter = pMessagesPresenter;
        this.menuPresenter = pMenuPresenter;
    }
    
    @Override
    protected void onBind() {
        setInSlot(SLOT_MESSAGES_CONTENT, messagesPresenter);
        setInSlot(SLOT_MENU_CONTENT, menuPresenter);
    }
}
