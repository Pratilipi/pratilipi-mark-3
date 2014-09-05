package com.pratilipi.data.access.gae;

import java.util.Date;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;
import com.pratilipi.commons.shared.PratilipiType;
import com.pratilipi.data.transfer.Pratilipi;

@PersistenceCapable( table = "PRATILIPI" )
@Discriminator( column = "_TYPE", strategy = DiscriminatorStrategy.CLASS_NAME )
public abstract class PratilipiEntity implements Pratilipi {

	@PrimaryKey
	@Persistent( column = "PRATILIPI_ID", valueStrategy = IdGeneratorStrategy.IDENTITY )
	private Long id;
	
	@Persistent( column = "PRATILIPI_TYPE" )
	private PratilipiType type;
	
	@Persistent( column = "TITLE" )
	private String title;
	
	@Persistent( column = "LANGUAGE_ID" )
	private Long languageId;

	
	@Persistent( column = "AUTHOR_ID" )
	private Long authorId;
	
	@Persistent( column = "PUBLICATION_YEAR" )
	private Long publicationYear;

	@Persistent( column = "LISTING_DATE" )
	private Date listingDate;

	
	@Persistent( column = "SUMMARY" )
	private Text summary;

	@Persistent( column = "WORD_COUNT" )
	private Long wordCount;

	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public PratilipiType getType() {
		return type;
	}
	
	@Override
	public void setType( PratilipiType pratilipiType ) {
		this.type = pratilipiType;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle( String title ) {
		this.title = title;
	}

	@Override
	public Long getLanguageId() {
		return languageId;
	}

	@Override
	public void setLanguageId( Long languageId ) {
		this.languageId = languageId;
	}

	@Override
	public Long getAuthorId() {
		return authorId;
	}

	@Override
	public void setAuthorId( Long authorId ) {
		this.authorId = authorId;
	}

	@Override
	public Long getPublicationYear() {
		return publicationYear;
	}

	@Override
	public void setPublicationYear( Long publicationYear ) {
		this.publicationYear = publicationYear;
	}

	@Override
	public Date getListingDate() {
		return listingDate;
	}

	@Override
	public void setListingDate( Date listingDate ) {
		this.listingDate = listingDate;
	}

	@Override
	public String getSummary() {
		return summary == null ? null : summary.getValue();
	}

	@Override
	public void setSummary( String summary ) {
		this.summary = summary == null ? null : new Text( summary );
	}
	
	@Override
	public Long getWordCount() {
		return wordCount;
	}

	@Override
	public void setWordCount( Long wordCount ) {
		this.wordCount = wordCount;
	}
	
}