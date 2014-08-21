package com.pratilipi.data.transfer;

import java.util.Date;
import com.pratilipi.shared.UserReviewState;

public interface UserBook {

	Long getUserId();
	
	void setUserId( Long userId );
	
	Long getBookId();
	
	void setBookId( Long bookId );
	
	Long getRating();
	
	void setRating( Long rating );
	
	String getReview();
	
	void setReview( String review );
	
	UserReviewState getReviewState();
	
	void setReviewState( UserReviewState reviewState );
	
	Date getReviewDate();
	
	void setReviewDate( Date reviewDate );

}
