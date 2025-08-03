package com.sunic.content.aggregate.content.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sunic.content.aggregate.content.store.ContentStore;
import com.sunic.content.aggregate.proxy.UserProxy;
import com.sunic.content.spec.common.exception.AdminPermissionException;
import com.sunic.content.spec.content.entity.Content;
import com.sunic.content.spec.content.facade.ContentFacade;
import com.sunic.content.spec.content.facade.sdo.ContentCdo;
import com.sunic.content.spec.content.facade.sdo.ContentRdo;
import com.sunic.content.spec.content.facade.sdo.ContentUdo;

import lombok.RequiredArgsConstructor;

/**
 * Business logic implementation for Content operations.
 * This follows the modularization-strategy.md pattern for Logic classes implementing CQRS.
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentLogic implements ContentFacade {

	private final ContentStore contentStore;
	private final UserProxy userProxy;

	@Override
	@Transactional
	public String registerContent(ContentCdo contentCdo) {
		if (!userProxy.checkUserIsAdmin(contentCdo.getRegistrant())) {
			throw new AdminPermissionException(contentCdo.getRegistrant());
		}

		Content content = Content.create(contentCdo);
		Content saved = contentStore.save(content);
		return saved.getId().toString();
	}

	@Override
	public ContentRdo retrieveContent(Integer id) {
		Content content = contentStore.findById(id);
		return convertToContentRdo(content);
	}

	@Override
	public List<ContentRdo> retrieveAllContents() {
		return contentStore.findAll().stream()
			.map(this::convertToContentRdo)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void modifyContent(Integer id, ContentUdo updateSdo) {
		if (!userProxy.checkUserIsAdmin(updateSdo.getModifier())) {
			throw new AdminPermissionException(updateSdo.getModifier());
		}

		Content existingContent = contentStore.findById(id);
		existingContent.modify(updateSdo);
		contentStore.save(existingContent);
	}

	@Override
	@Transactional
	public void deleteContent(Integer id, Integer userId) {
		if (!userProxy.checkUserIsAdmin(userId)) {
			throw new AdminPermissionException(userId);
		}

		Content content = contentStore.findById(id);
		content.delete();
		contentStore.save(content);
	}

	private ContentRdo convertToContentRdo(Content content) {
		return content.toRdo();
	}
}