package com.pratilipi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pratilipi.commons.server.PratilipiHelper;
import com.pratilipi.data.access.DataAccessor;
import com.pratilipi.data.access.DataAccessorFactory;
import com.pratilipi.data.transfer.Author;

public class PratilipiFilter implements Filter {

	@Override
	public void init( FilterConfig config ) throws ServletException { }

	@Override
	public void destroy() { }

	@Override
	public void doFilter(
			ServletRequest req, ServletResponse resp, FilterChain chain )
					throws IOException, ServletException {

		HttpServletRequest request = ( HttpServletRequest ) req;
		HttpServletResponse response = ( HttpServletResponse ) resp;
		
		String host = request.getServerName();
		String requestUri = request.getRequestURI();
		String action = request.getParameter( "action" );

		if( !host.equals( "www.pratilipi.com" )
				&& !host.equals( "devo.pratilipi.com" )
				&& !host.endsWith( ".appspot.com" )
				&& !host.equals( "localhost" )
				&& !host.equals( "127.0.0.1" ) ) { // Redirecting to www.pratilipi.com
			
			response.sendRedirect(
					"http://www.pratilipi.com" + requestUri );

			PrintWriter out = response.getWriter();
			out.println( "Redirecting to www.pratilipi.com ..." );
			out.close();
			
		} else if( requestUri.length() > 1 && requestUri.endsWith( "/" ) ) { // Removing trailing "/"
			requestUri = requestUri.substring( 0, requestUri.length() -1 );
			response.sendRedirect( requestUri );
			
			PrintWriter out = response.getWriter();
			out.println( "Redirecting to www.pratilipi.com" + requestUri + " ..." );
			out.close();

			
		} else if ( action != null && action.equals( "login" ) ) { // Redirecting to profile page on login
			
			PratilipiHelper pratilipiHelper = PratilipiHelper.get( request ); 
			DataAccessor dataAccessor = DataAccessorFactory.getDataAccessor();

			Long currentUserId = pratilipiHelper.getCurrentUserId();
			Author author = dataAccessor.getAuthorByUserId( currentUserId );
			
			if( author != null )
				response.sendRedirect( PratilipiHelper.getAuthorPageUrl( author.getId() ) );
			else
				chain.doFilter( request, response ); 
		
		} else {
			chain.doFilter( request, response );
		}
		
	}

}