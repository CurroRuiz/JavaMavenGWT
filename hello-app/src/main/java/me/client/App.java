package me.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.intendia.rxgwt2.elemento.RxElemento;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLInputElement;
import elemental2.dom.MouseEvent;
import elemental2.dom.TouchEvent;
import io.reactivex.Observable;
import org.jboss.gwt.elemento.core.Elements;

import static elemental2.dom.DomGlobal.console;
import static elemental2.dom.DomGlobal.document;
import static org.jboss.gwt.elemento.core.EventType.*;

public class App implements EntryPoint {

    @Override
    public void onModuleLoad() {
        HTMLElement el = Elements.span().style("font-weight: bold;").get();
        Elements.body().add("mouse position: ").add(el).add(Elements.br().get());
        HTMLElement selector = Elements.input("checkbox").id("selector").get();
        Elements.body().add("Activate coordinates:").add(selector);
        Observable toque = RxElemento.fromEvent(document,touchmove);
      /*  RxElemento.fromEvent(document, mousemove).mergeWith(toque)
            .subscribe(ev2 -> {
                    if (((HTMLInputElement) selector).checked) {
                        if(ev2.toString().equalsIgnoreCase("[object MouseEvent]")){
                            MouseEvent raton = (MouseEvent) ev2;
                            el.textContent=raton.clientX + "," + raton.clientY;
                        }else {
                            TouchEvent pantalla = (TouchEvent) ev2;
                            el.textContent=pantalla.targetTouches.item(0).clientX + ",";
                        }
                    }
            });*/
        RxElemento.fromEvent(document, mousemove)
                .subscribe(ev2 -> {
                    if (((HTMLInputElement) selector).checked) {
                        el.textContent=ev2.clientX + "," + ev2.clientY;
                    }
                });

        RxElemento.fromEvent(document, touchmove)
                .subscribe(ev2 -> {
                    if (((HTMLInputElement) selector).checked) {
                        el.textContent=ev2.targetTouches.item(0).clientX + "," + ev2.targetTouches.item(0).clientY;
                    }
                });


    }



}