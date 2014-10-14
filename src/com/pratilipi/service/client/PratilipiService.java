package com.pratilipi.service.client;

import com.claymus.commons.shared.exception.IllegalArgumentException;
import com.claymus.commons.shared.exception.InsufficientAccessException;
import com.claymus.commons.shared.exception.UnexpectedServerException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.pratilipi.service.shared.AddAuthorRequest;
import com.pratilipi.service.shared.AddAuthorResponse;
import com.pratilipi.service.shared.AddLanguageRequest;
import com.pratilipi.service.shared.AddLanguageResponse;
import com.pratilipi.service.shared.AddPratilipiGenreRequest;
import com.pratilipi.service.shared.AddPratilipiGenreResponse;
import com.pratilipi.service.shared.AddPublisherRequest;
import com.pratilipi.service.shared.AddPublisherResponse;
import com.pratilipi.service.shared.AddUserPratilipiRequest;
import com.pratilipi.service.shared.AddUserPratilipiResponse;
import com.pratilipi.service.shared.DeletePratilipiGenreRequest;
import com.pratilipi.service.shared.DeletePratilipiGenreResponse;
import com.pratilipi.service.shared.GetAuthorListRequest;
import com.pratilipi.service.shared.GetAuthorListResponse;
import com.pratilipi.service.shared.GetGenreListRequest;
import com.pratilipi.service.shared.GetGenreListResponse;
import com.pratilipi.service.shared.GetLanguageListRequest;
import com.pratilipi.service.shared.GetLanguageListResponse;
import com.pratilipi.service.shared.GetPratilipiListRequest;
import com.pratilipi.service.shared.GetPratilipiListResponse;
import com.pratilipi.service.shared.GetPublisherListRequest;
import com.pratilipi.service.shared.GetPublisherListResponse;
import com.pratilipi.service.shared.GetUserPratilipiListRequest;
import com.pratilipi.service.shared.GetUserPratilipiListResponse;
import com.pratilipi.service.shared.GetUserPratilipiRequest;
import com.pratilipi.service.shared.GetUserPratilipiResponse;
import com.pratilipi.service.shared.SaveAuthorRequest;
import com.pratilipi.service.shared.SaveAuthorResponse;
import com.pratilipi.service.shared.SaveGenreRequest;
import com.pratilipi.service.shared.SaveGenreResponse;
import com.pratilipi.service.shared.SavePratilipiContentRequest;
import com.pratilipi.service.shared.SavePratilipiContentResponse;
import com.pratilipi.service.shared.SavePratilipiRequest;
import com.pratilipi.service.shared.SavePratilipiResponse;

@RemoteServiceRelativePath("../service.pratilipi")
public interface PratilipiService extends RemoteService {
	
	SavePratilipiResponse savePratilipi(
			SavePratilipiRequest request )
			throws IllegalArgumentException, InsufficientAccessException,
					UnexpectedServerException;

	GetPratilipiListResponse getPratilipiList(
			GetPratilipiListRequest request );
	

	SavePratilipiContentResponse savePratilipiContent(
			SavePratilipiContentRequest request )
			throws IllegalArgumentException, InsufficientAccessException,
					UnexpectedServerException;


	AddLanguageResponse addLanguage( AddLanguageRequest request )
			throws IllegalArgumentException, InsufficientAccessException;

	GetLanguageListResponse getLanguageList( GetLanguageListRequest request )
			throws InsufficientAccessException;


	AddAuthorResponse addAuthor( AddAuthorRequest request )
			throws IllegalArgumentException, InsufficientAccessException;
	
	SaveAuthorResponse saveAuthor( SaveAuthorRequest request )
			throws IllegalArgumentException, InsufficientAccessException;

	GetAuthorListResponse getAuthorList( GetAuthorListRequest request )
			throws InsufficientAccessException;
	
	
	AddPublisherResponse addPublisher( AddPublisherRequest request )
			throws InsufficientAccessException;

	GetPublisherListResponse getPublisherList( GetPublisherListRequest request );
	

	SaveGenreResponse saveGenre( SaveGenreRequest request )
			throws IllegalArgumentException, InsufficientAccessException;

	GetGenreListResponse getGenreList( GetGenreListRequest request )
			throws IllegalArgumentException, InsufficientAccessException;


	AddPratilipiGenreResponse addPratilipiGenre( AddPratilipiGenreRequest request )
			throws IllegalArgumentException, InsufficientAccessException;

	DeletePratilipiGenreResponse deletePratilipiGenre( DeletePratilipiGenreRequest request )
			throws IllegalArgumentException, InsufficientAccessException;

	
	AddUserPratilipiResponse addUserPratilipi( AddUserPratilipiRequest request )
			throws IllegalArgumentException, InsufficientAccessException;

	GetUserPratilipiResponse getUserPratilipi( GetUserPratilipiRequest request );
	
	GetUserPratilipiListResponse getUserPratilipiList( GetUserPratilipiListRequest request );
	
}
