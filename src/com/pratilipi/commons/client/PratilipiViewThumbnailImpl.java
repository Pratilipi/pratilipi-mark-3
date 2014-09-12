package com.pratilipi.commons.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.pratilipi.commons.shared.PratilipiHelper;
import com.pratilipi.commons.shared.PratilipiType;
import com.pratilipi.service.shared.data.PratilipiData;

public class PratilipiViewThumbnailImpl extends PratilipiView {

	private final Panel colPanel = new SimplePanel();
	private final FocusPanel focusPanel = new FocusPanel();
	private final Panel panel = new FlowPanel();
	private final Panel thumbnailPanel = new FlowPanel();
	private final Panel infoPanel = new FlowPanel();

	private final Anchor coverImageAnchor = new Anchor();
	private final Image coverImage = new Image();
	
	private final Anchor titleAnchor = new Anchor();
	private final Anchor authorAnchor = new Anchor();
	private final Anchor editAnchor = new Anchor();
	
	
	private PratilipiData pratilipiData;
	
	
	public PratilipiViewThumbnailImpl() {
		editAnchor.setText( "Edit" );
		editAnchor.setVisible( false );

		
		// Composing the widget
		colPanel.add( focusPanel );
		focusPanel.add( panel );
		
		panel.add( thumbnailPanel );
		panel.add( infoPanel );

		thumbnailPanel.add( coverImageAnchor );
		coverImageAnchor.getElement().appendChild( coverImage.getElement() );

		infoPanel.add( titleAnchor );
		infoPanel.add( authorAnchor );
		
		
		// Setting required style classes
		colPanel.setStyleName( "col-lg-2 col-md-3 col-sm-4 col-xs-6" );
		focusPanel.getElement().setAttribute( "style", "margin:15px;" );
		panel.getElement().setAttribute( "style", "width:200px; height:200px; overflow: hidden;" );
		coverImage.setStyleName( "img-thumbnail" );
		infoPanel.getElement().setAttribute( "style",
				"position:absolute; width:200px; height:55px; padding-left:10px; bottom:0px;"
				+ " background-color:#FFF; opacity:0.8;" );
		titleAnchor.getElement().setAttribute( "style", "display:block;");
		authorAnchor.getElement().setAttribute( "style", "display:block;");
		
		initWidget( colPanel );
	}
	
	@Override
	public HandlerRegistration addEditHyperlinkClickHandler( ClickHandler clickHandler ) {
		editAnchor.setVisible( true );
		return editAnchor.addClickHandler( clickHandler );
	}
	
	@Override
	public PratilipiData getPratilipiData() {
		return pratilipiData;
	}
	
	@Override
	public void setPratilipiData( PratilipiData pratilipiData ) {
		this.pratilipiData = pratilipiData;
		
		PratilipiType pratilipiType = pratilipiData.getType();
		
		coverImageAnchor.setHref( pratilipiType.getPageUrl() + pratilipiData.getId() );
		coverImage.setUrl( pratilipiType.getCoverImageUrl() + pratilipiData.getId() );

		titleAnchor.setText( pratilipiData.getTitle() );
		titleAnchor.setHref( pratilipiType.getPageUrl() + pratilipiData.getId() );

		authorAnchor.setText( pratilipiData.getAuthorName() );
		authorAnchor.setHref( PratilipiHelper.URL_AUTHOR_PAGE + pratilipiData.getAuthorId() );
	}

	@Override
	public void focus() {
		focusPanel.setFocus( true );
	}
	
}