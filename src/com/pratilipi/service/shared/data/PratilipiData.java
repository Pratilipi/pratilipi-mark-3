package com.pratilipi.service.shared.data;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.pratilipi.commons.shared.PratilipiType;

public abstract class PratilipiData implements IsSerializable {

	private Long id;
	
	private PratilipiType type;
	
	private boolean isPublicDomain;
	private boolean hasPublicDomain;
	
	private String title;
	private boolean hasTitle;
	
	private Long languageId;
	private boolean hasLanguageId;

	private String languageName;
	
	
	private Long authorId;
	private boolean hasAuthorId;
	
	private String authorName;
	
	private Long publicationYear;
	private boolean hasPublicationYear;
	
	private Date listingDate;

	
	private String summary;
	private boolean hasSummary;
	
	private String content;
	private boolean hasContent;
	
	private Long wordCount;
	private boolean hasWordCount;
	
	
	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public PratilipiType getType(){
		return this.type;
	}
	
	public void setType( PratilipiType type ){
		this.type = type;
	}
	
	public boolean isPublicDomain() {
		return isPublicDomain;
	}

	public void setPublicDomain( boolean isPublicDomain ) {
		this.isPublicDomain = isPublicDomain;
		this.hasPublicDomain = true;
	}

	public boolean hasPublicDomain() {
		return hasPublicDomain;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle( String title ) {
		this.title = title;
		this.hasTitle = true;
	}
	
	public boolean hasTitle() {
		return hasTitle;
	}

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId( Long languageId ) {
		this.languageId = languageId;
		this.hasLanguageId = true;
	}

	public boolean hasLanguageId() {
		return hasLanguageId;
	}
	
	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName( String languageName ) {
		this.languageName = languageName;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId( Long authorId ) {
		this.authorId = authorId;
		this.hasAuthorId = true;
	}

	public boolean hasAuthorId() {
		return hasAuthorId;
	}
	
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName( String authorName ) {
		this.authorName = authorName;
	}
	
	public Long getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationDate( Long publicationYear ) {
		this.publicationYear = publicationYear;
		this.hasPublicationYear = true;
	}

	public boolean hasPublicationYear() {
		return hasPublicationYear;
	}
	
	public Date getListingDate() {
		return listingDate;
	}

	public void setListingDate( Date listingDate ) {
		this.listingDate = listingDate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary( String summary ) {
		this.summary = summary;
		this.hasSummary = true;
	}

	public boolean hasSummary() {
		return hasSummary;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent( String content ) {
		this.content = content;
		this.hasContent = true;
	}

	public boolean hasContent() {
		return hasContent;
	}
	
	public Long getWordCount() {
		return wordCount;
	}

	public void setWordCount( Long wordCount ) {
		this.wordCount = wordCount;
		this.hasWordCount = true;
	}
	
	public boolean hasWordCount() {
		return hasWordCount;
	}
	
}
