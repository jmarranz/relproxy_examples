package com.example.relproxy_ex_vaadin;

import java.io.Serializable;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class VaadinUIDelegateImpl implements VaadinUIDelegate,Serializable
{
	protected Relproxy_ex_vaadinUI parent;
	
	public VaadinUIDelegateImpl()
	{
	}	
	
	public VaadinUIDelegateImpl(Relproxy_ex_vaadinUI parent)
	{
		this.parent = parent;
	}
	
	@Override
	public void init(VaadinRequest request) {
						
		final WrappedSession session = request.getWrappedSession();
		
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		parent.setContent(layout);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {

			public void buttonClick(ClickEvent event) {
				
				Integer counter = (Integer)session.getAttribute("counter");		
				if (counter == null) { counter = 0; }
				counter++;
				session.setAttribute("counter", counter);		
				
				layout.addComponent(new Label("Thank you for clicking, counter:" + counter));			
			}
		});

		layout.addComponent(button);			
	}

}
