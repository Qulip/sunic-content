package com.sunic.content.spec.content.facade;

import com.sunic.content.spec.content.facade.sdo.ContentCreateSdo;
import com.sunic.content.spec.content.facade.sdo.ContentRdo;
import com.sunic.content.spec.content.facade.sdo.ContentUpdateSdo;

import java.util.List;

/**
 * Facade interface for Content operations following CQRS pattern.
 */
public interface ContentFacade {
    
    String registerContent(ContentCreateSdo createSdo);
    
    ContentRdo retrieveContent(Integer id);
    
    List<ContentRdo> retrieveAllContents();
    
    void modifyContent(Integer id, ContentUpdateSdo updateSdo);
    
    void deleteContent(Integer id);
}