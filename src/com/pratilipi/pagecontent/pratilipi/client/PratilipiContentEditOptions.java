package com.pratilipi.pagecontent.pratilipi.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.pratilipi.commons.shared.PratilipiType;
import com.pratilipi.service.client.PratilipiService;
import com.pratilipi.service.client.PratilipiServiceAsync;
import com.pratilipi.service.shared.SavePratilipiRequest;
import com.pratilipi.service.shared.SavePratilipiResponse;
import com.pratilipi.service.shared.data.PratilipiData;

public class PratilipiContentEditOptions implements EntryPoint, ClickHandler {

	private static final PratilipiServiceAsync pratilipiService =
			GWT.create( PratilipiService.class );
	

	// Cover image edit options widgets
	private final FileUpload coverImageUpload = new FileUpload();
	
	
	// Word content edit options widgets
	private final FileUpload wordContentUpload = new FileUpload();

	
	// Html content edit options widgets
	private final FileUpload htmlContentUpload = new FileUpload();

	
	// Summary edit options widgets
	private final Anchor editSummaryAnchor = new Anchor( "Edit Summary" );
	private final Anchor saveSummaryAnchor = new Anchor( "Save Summary" );
	private final Label savingSummaryLabel = new Label( "Saving Summary ..." );

	
	// Content edit options widgets
	private final Anchor editContentAnchor = new Anchor( "Edit Content" );
	private final Anchor saveContentAnchor = new Anchor( "Save Content" );
	private final Label savingContentLabel = new Label( "Saving Content ..." );

	
	private String url = Window.Location.getPath();
	private PratilipiType pratilipiType;

	
	public void onModuleLoad() {
		for( PratilipiType pratilipiType : PratilipiType.values() )
			if( url.startsWith( pratilipiType.getPageUrl() ) )
				this.pratilipiType = pratilipiType;

		if( pratilipiType == null )
			return;
	
		
		String pratilipiIdStr = url.substring( pratilipiType.getPageUrl().length() );
		
		
		// Cover image edit options
		RootPanel rootPanel = RootPanel.get( "PageContent-Pratilipi-CoverImage-EditOptions" );
		if( rootPanel != null ) {
			String uploadUrl = pratilipiType.getCoverImageUrl() + pratilipiIdStr;
			coverImageUpload.getElement().setAttribute( "data-url", uploadUrl );
			loadFileUploader( coverImageUpload.getElement() );

			rootPanel.add( coverImageUpload );
		}
		
		
		// Html content edit options
		rootPanel = RootPanel.get( "PageContent-Pratilipi-HtmlContent-EditOptions" );
		if( rootPanel != null ) {
			String uploadUrl = pratilipiType.getContentHtmlUrl() + pratilipiIdStr;
			htmlContentUpload.getElement().setAttribute( "data-url", uploadUrl );
			loadFileUploader( htmlContentUpload.getElement() );

			rootPanel.add( htmlContentUpload );
		}
		
		
		// Word content edit options
		rootPanel = RootPanel.get( "PageContent-Pratilipi-WordContent-EditOptions" );
		if( rootPanel != null ) {
			String uploadUrl = pratilipiType.getContentWordUrl() + pratilipiIdStr;
			wordContentUpload.getElement().setAttribute( "data-url", uploadUrl );
			loadFileUploader( wordContentUpload.getElement() );

			rootPanel.add( wordContentUpload );
		}
		
		
		// Summary edit options
		rootPanel = RootPanel.get( "PageContent-Pratilipi-Summary-EditOptions" );
		if( rootPanel != null ) {
			editSummaryAnchor.addClickHandler( this );
			saveSummaryAnchor.addClickHandler( this );
			saveSummaryAnchor.setVisible( false );
			savingSummaryLabel.setVisible( false );
	
			rootPanel.add( editSummaryAnchor );
			rootPanel.add( saveSummaryAnchor );
			rootPanel.add( savingSummaryLabel );
		}

		
		// Content edit options
		rootPanel = RootPanel.get( "PageContent-Pratilipi-Content-EditOptions" );
		if( rootPanel != null ) {
			editContentAnchor.addClickHandler( this );
			saveContentAnchor.addClickHandler( this );
			saveContentAnchor.setVisible( false );
			savingContentLabel.setVisible( false );
	
			rootPanel.add( editContentAnchor );
			rootPanel.add( saveContentAnchor );
			rootPanel.add( savingContentLabel );
		}

	}

	@Override
	public void onClick( ClickEvent event ) {
		
		if( event.getSource() == editSummaryAnchor ) {
			editSummaryAnchor.setVisible( false );
			saveSummaryAnchor.setVisible( true );
			loadEditor( RootPanel.get( "PageContent-Pratilipi-Summary" ).getElement() );
			
		} else if( event.getSource() == saveSummaryAnchor ) {
			saveSummaryAnchor.setVisible( false );
			savingSummaryLabel.setVisible( true );
			
			String pratilipiIdStr = url.substring( pratilipiType.getPageUrl().length() );
			Long pratilipiId = Long.parseLong( pratilipiIdStr );

			PratilipiData pratilipiData = pratilipiType.newPratilipiData();
			pratilipiData.setId( pratilipiId );
			pratilipiData.setSummary( getHtmlFromEditor( "PageContent-Pratilipi-Summary" ) );
			
			pratilipiService.savePratilipi(
					new SavePratilipiRequest( pratilipiData ),
					new AsyncCallback<SavePratilipiResponse>() {
				
				@Override
				public void onSuccess( SavePratilipiResponse result ) {
					Window.Location.reload();
				}
				
				@Override
				public void onFailure( Throwable caught ) {
					Window.alert( caught.getMessage() );
					savingSummaryLabel.setVisible( false );
					saveSummaryAnchor.setVisible( true );
				}
				
			});
		
		} else if( event.getSource() == editContentAnchor ) {
			editContentAnchor.setVisible( false );
			saveContentAnchor.setVisible( true );
			loadEditor( RootPanel.get( "PageContent-Pratilipi-Content" ).getElement() );
			
		} else if( event.getSource() == saveContentAnchor ) {
			saveContentAnchor.setVisible( false );
			savingContentLabel.setVisible( true );
			
			String pratilipiIdStr = url.substring( pratilipiType.getPageUrl().length() );
			Long pratilipiId = Long.parseLong( pratilipiIdStr );

			PratilipiData pratilipiData = pratilipiType.newPratilipiData();
			pratilipiData.setId( pratilipiId );
			pratilipiData.setContent( getHtmlFromEditor( "PageContent-Pratilipi-Content" ) );
			
			pratilipiService.savePratilipi(
					new SavePratilipiRequest( pratilipiData ),
					new AsyncCallback<SavePratilipiResponse>() {
				
				@Override
				public void onSuccess( SavePratilipiResponse result ) {
					Window.Location.reload();
				}
				
				@Override
				public void onFailure( Throwable caught ) {
					Window.alert( caught.getMessage() );
					savingContentLabel.setVisible( false );
					saveContentAnchor.setVisible( true );
				}
				
			});
		}

		
	}
	
	private native void loadFileUploader( Element element ) /*-{
		$wnd.jQuery( element ).fileupload({
			dataType: 'html',
			done: function( e, data ) {
				$wnd.document.location.reload();
			}
		});
	}-*/;

	private native void loadEditor( Element element ) /*-{
		$wnd.CKEDITOR.replace( element );
	}-*/;
	
	private native String getHtmlFromEditor( String editorName ) /*-{
		return $wnd.CKEDITOR.instances[ editorName ].getData();
	}-*/;

}
