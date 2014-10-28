package com.pratilipi.pagecontent.author;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.claymus.commons.server.ClaymusHelper;
import com.claymus.commons.server.SerializationUtil;
import com.claymus.commons.shared.exception.UnexpectedServerException;
import com.claymus.data.access.DataListCursorTuple;
import com.claymus.pagecontent.PageContentProcessor;
import com.pratilipi.commons.server.PratilipiHelper;
import com.pratilipi.commons.shared.PratilipiFilter;
import com.pratilipi.commons.shared.PratilipiState;
import com.pratilipi.commons.shared.PratilipiType;
import com.pratilipi.data.access.DataAccessor;
import com.pratilipi.data.access.DataAccessorFactory;
import com.pratilipi.data.transfer.Author;
import com.pratilipi.data.transfer.Pratilipi;
import com.pratilipi.service.shared.data.AuthorData;
import com.pratilipi.service.shared.data.PratilipiData;

public class AuthorContentProcessor extends PageContentProcessor<AuthorContent> {

	@Override
	public String generateTitle( AuthorContent authorContent, HttpServletRequest request ) {
		AuthorData authorData = PratilipiHelper.get( request ).createAuthorData( authorContent.getId() );
		return authorData.getFullName() + " (" + authorData.getFullNameEn() + ")";
	}
	
	@Override
	public String generateHtml(
			AuthorContent authorContent,
			HttpServletRequest request ) throws UnexpectedServerException {
		
		PratilipiHelper pratilipiHelper = PratilipiHelper.get( request );
		DataAccessor dataAccessor = DataAccessorFactory.getDataAccessor( request );

		Long authorId = authorContent.getId();
		Author author = dataAccessor.getAuthor( authorId );

		boolean showEditOption = AuthorContentHelper
				.hasRequestAccessToUpdateAuthorData( request, author );

		PratilipiFilter pratilipiFilter = new PratilipiFilter();
		pratilipiFilter.setAuthorId( authorId );

		pratilipiFilter.setState( PratilipiState.DRAFTED );
		DataListCursorTuple<Pratilipi> draftedPratilipiListCursorTuple = showEditOption
				? (DataListCursorTuple<Pratilipi>) dataAccessor.getPratilipiList( pratilipiFilter, null, 1000 )
				: new DataListCursorTuple<Pratilipi>( new ArrayList<Pratilipi>(0), null );

		pratilipiFilter.setState( PratilipiState.PUBLISHED );

		pratilipiFilter.setType( PratilipiType.BOOK );
		DataListCursorTuple<Pratilipi> bookListCursorTuple =
				dataAccessor.getPratilipiList( pratilipiFilter, null, 1000 );  

		pratilipiFilter.setType( PratilipiType.POEM );
		DataListCursorTuple<Pratilipi> poemListCursorTuple =
				dataAccessor.getPratilipiList( pratilipiFilter, null, 1000 );  

		pratilipiFilter.setType( PratilipiType.STORY );
		DataListCursorTuple<Pratilipi> storyListCursorTuple =
				dataAccessor.getPratilipiList( pratilipiFilter, null, 1000 );  

		pratilipiFilter.setType( PratilipiType.ARTICLE );
		DataListCursorTuple<Pratilipi> articleListCursorTuple =
				dataAccessor.getPratilipiList( pratilipiFilter, null, 1000 );  

		
		AuthorData authorData = pratilipiHelper.createAuthorData( author.getId() );

		List<PratilipiData> draftedPratilipiDataList =
				new ArrayList<>( draftedPratilipiListCursorTuple.getDataList().size() );
		for( Pratilipi pratilipi : draftedPratilipiListCursorTuple.getDataList() )
			draftedPratilipiDataList.add(
					pratilipiHelper.createPratilipiData( pratilipi, null, null, null ) );
		
		List<PratilipiData> bookDataList =
				new ArrayList<>( bookListCursorTuple.getDataList().size() );
		for( Pratilipi pratilipi : bookListCursorTuple.getDataList() )
			bookDataList.add(
					pratilipiHelper.createPratilipiData( pratilipi, null, null, null ) );
		
		List<PratilipiData> poemDataList =
				new ArrayList<>( poemListCursorTuple.getDataList().size() );
		for( Pratilipi pratilipi : poemListCursorTuple.getDataList() )
			poemDataList.add(
					pratilipiHelper.createPratilipiData( pratilipi, null, null, null ) );
		
		List<PratilipiData> storyDataList =
				new ArrayList<>( storyListCursorTuple.getDataList().size() );
		for( Pratilipi pratilipi : storyListCursorTuple.getDataList() )
			storyDataList.add(
					pratilipiHelper.createPratilipiData( pratilipi, null, null, null ) );

		List<PratilipiData> articleDataList =
				new ArrayList<>( articleListCursorTuple.getDataList().size() );
		for( Pratilipi pratilipi : articleListCursorTuple.getDataList() )
			articleDataList.add(
					pratilipiHelper.createPratilipiData( pratilipi, null, null, null ) );

		
		// Creating data model required for template processing
		Map<String, Object> dataModel = new HashMap<>();
		dataModel.put( "timeZone", pratilipiHelper.getCurrentUserTimeZone() );
		dataModel.put( "authorData", authorData );
		dataModel.put( "authorDataEncodedStr", SerializationUtil.encode( authorData ) );
		dataModel.put( "draftedPratilipiDataList", draftedPratilipiDataList );
		dataModel.put( "bookDataList", bookDataList );
		dataModel.put( "poemDataList", poemDataList );
		dataModel.put( "storyDataList", storyDataList );
		dataModel.put( "articleDataList", articleDataList );
		dataModel.put( "domain", ClaymusHelper.getSystemProperty( "domain" ) );
		dataModel.put( "showEditOption", showEditOption );
		
		
		// Processing template
		return super.processTemplate( dataModel, getTemplateName() );
	}
	
}
