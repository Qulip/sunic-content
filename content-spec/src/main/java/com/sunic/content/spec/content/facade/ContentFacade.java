package com.sunic.content.spec.content.facade;

import java.util.List;

import com.sunic.content.spec.content.facade.sdo.ContentCdo;
import com.sunic.content.spec.content.facade.sdo.ContentRdo;
import com.sunic.content.spec.content.facade.sdo.ContentUdo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * API documentation interface for Content operations.
 */
@Tag(name = "Content", description = "Content API")
public interface ContentFacade {

	@Operation(summary = "Create Content", description = "Create a new content")
	String registerContent(ContentCdo contentCdo);

	@Operation(summary = "Retrieve Content", description = "Retrieve content")
	ContentRdo retrieveContent(Integer id);

	@Operation(summary = "Retrieve All Content", description = "Retrieve All Of Content")
	List<ContentRdo> retrieveAllContents();

	@Operation(summary = "Modify Content", description = "Modify content")
	void modifyContent(Integer id, ContentUdo updateSdo);

	@Operation(summary = "Delete Content", description = "Delete content")
	void deleteContent(Integer id, Integer userId);
}