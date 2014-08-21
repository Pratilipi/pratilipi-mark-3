package com.pratilipi.data.access.gae;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;
import com.pratilipi.data.transfer.UserBook;
import com.pratilipi.shared.UserReviewState;

@PersistenceCapable( table = "USER_BOOK" )
public class UserBookEntity implements UserBook {

	@PrimaryKey
	@Persistent( column = "USER_BOOK_ID" )
	private String id;
	
	@Persistent( column = "USER_ID" )
	private Long userId;
	
	@Persistent( column = "BOOK_ID" )
	private Long bookId;
	
	@Persistent( column = "RATING" )
	private Long rating;
	
	@Persistent( column = "REVIEW" )
	private Text review;
	
	@Persistent( column = "REVIEW_STATE" )
	private UserReviewState reviewState;
	
	@Persistent( column = "REVIEW_DATE" )
	private Date reviewDate;

	
	public void setId( String id ) {
		this.id = id;
	}
	
	@Override
	public Long getUserId() {
		return userId;
	}
	
	@Override
	public void setUserId( Long userId ) {
		this.userId = userId;
	}
	
	@Override
	public Long getBookId() {
		return bookId;
	}
	
	@Override
	public void setBookId( Long bookId ) {
		this.bookId = bookId;
	}
	
	@Override
	public Long getRating() {
		return rating;
	}
	
	@Override
	public void setRating( Long rating ) {
		this.rating = rating;
	}
	
	@Override
	public String getReview() {
		return review == null ? null : review.getValue();
	}

	@Override
	public void setReview( String review ) {
		this.review = new Text( review );
	}
	
	@Override
	public UserReviewState getReviewState() {
		return reviewState;
	}
	
	@Override
	public void setReviewState( UserReviewState reviewState ) {
		this.reviewState = reviewState;
	}

	@Override
	public Date getReviewDate() {
		return reviewDate;
	}
	
	@Override
	public void setReviewDate( Date reviewDate ) {
		this.reviewDate = reviewDate;
	}

}
