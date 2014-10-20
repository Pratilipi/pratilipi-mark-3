<#setting time_zone="${ timeZone }">
<#import "../../../../com/claymus/commons/client/ui/Social.ftl" as social>
<#import "../../../../com/pratilipi/commons/client/PratilipiView.ftl" as pratilipiView>

<!-- PageContent :: Author :: Start -->

<#assign shareUrl="http://${ domain }${ authorData.getPageUrl() }">

<div class="container">
	<div class="row">

		<#-- Cover image and edit options -->
		<div class="col-lg-offset-0 col-lg-2 col-md-offset-0 col-md-3 col-sm-offset-0 col-sm-3 col-xs-offset-3 col-xs-6" style="margin-top:25px; margin-bottom:15px;">
			<div class="on-hover">
				<img class="img-responsive" src="${ authorData.getAuthorImageUrl() }">
				<#if showEditOption>
					<div id="PageContent-Author-Image-EditOptions"></div>
				</#if>
			</div>
			<div style="margin-top:10px; margin-bottom:10px; text-align:center">
				<@social.vToolbar shareUrl=shareUrl/>
			</div>
		</div>
			
		<#-- Author data and edit options -->
		<div class="col-lg-7 col-md-6 col-sm-9 col-xs-12" style="margin-bottom:15px;">
			<h1 id="PageContent-Author-Name" style="text-align:center;">${ authorData.getFullName() }</h1>
			<h3 id="PageContent-Author-NameEn" style="text-align:center; margin-top:10px;">${ authorData.getFullNameEn() }</h3>

			<div id="PageContent-Author-Summary" style="margin-top:20px; margin-bottom:10px;">
				${ authorData.getSummary()! }
			</div>
			<#if showEditOption>
				<div id="PageContent-Author-Summary-EditOptions" style="text-align:right;"></div>
			</#if>
		</div>
		
		<#-- Author published work metric -->
		<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12" style="margin-top:25px; margin-bottom:15px;">
			<table class="table table-bordered">
				<tr>
					<th colspan="2" style="text-align:center;">
						<h3 style="margin-top:5px; margin-bottom:5px;">Published Works</h3>
					</th>
				</tr>
				<tr>
					<td style="text-align:center;">Books</td>
					<td style="text-align:center";>${ bookDataList?size }</td>
				</tr>
				<tr>
					<td style="text-align:center;">Poems</td>
					<td style="text-align:center;">${ poemDataList?size }</td>
				</tr>
				<tr>
					<td style="text-align:center;">Stories</td>
					<td style="text-align:center;">${ storyDataList?size }</td>
				</tr>
				<tr>
					<td style="text-align:center;">Article</td>
					<td style="text-align:center;">${ articleDataList?size }</td>
				</tr>
			</table>
		</div>
		
	</div>
</div>


<#if bookDataList?has_content>
	<div class="container">
		<h3 class="hr-below">Published Books</h3>
		<div class="row">
			<#list bookDataList as bookData>
				<@pratilipiView.thumbnail pratilipiData=bookData />
			</#list>
		</div>
	</div>
</#if>


<#if poemDataList?has_content>
	<div class="container">
		<h3 class="hr-below">Published Poems</h3>
		<div class="row">
			<#list poemDataList as poemData>
				<@pratilipiView.thumbnail pratilipiData=poemData />
			</#list>
		</div>
	</div>
</#if>


<#if storyDataList?has_content>
	<div class="container">
		<h3 class="hr-below">Published Stories</h3>
		<div class="row">
			<#list storyDataList as storyData>
				<@pratilipiView.thumbnail pratilipiData=storyData />
			</#list>
		</div>
	</div>
</#if>


<#if articleDataList?has_content>
	<div class="container">
		<h3 class="hr-below">Published Articles</h3>
		<div class="row">
			<#list articleDataList as articleData>
				<@pratilipiView.thumbnail pratilipiData=articleData />
			</#list>
		</div>
	</div>
</#if>


<#if showEditOption>
	<div id="PageContent-Author-EncodedData" style="display:none;">${ authorDataEncodedStr }</div>
	<script type="text/javascript" language="javascript" src="/pagecontent.author.witheditoptions/pagecontent.author.witheditoptions.nocache.js" defer></script>
<#else>
	<script type="text/javascript" language="javascript" src="/pagecontent.author/pagecontent.author.nocache.js" defer></script>
</#if>

<!-- PageContent :: Author :: End -->