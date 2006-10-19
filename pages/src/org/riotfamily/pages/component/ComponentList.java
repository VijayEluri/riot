package org.riotfamily.pages.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * List of components that can be looked up using a path/key combination.
 * Actually the class consists of two lists of  
 * {@link org.riotfamily.pages.component.VersionContainer VersionContainers},
 * the live-list and the preview-list. 
 */
public class ComponentList {

	private Long id;
	
	private String path;
	
	private String key;
	
	private List liveList;
	
	private List previewList;
	
	private boolean dirty;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List getLiveList() {
		return this.liveList;
	}

	public void setLiveList(List list) {
		this.liveList = list;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List getPreviewList() {
		return this.previewList;
	}

	public void setPreviewList(List list) {
		this.previewList = list;
	}

	public boolean isDirty() {
		return this.dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("ComponentList ");
		sb.append(path).append('#').append(key);
		sb.append(" (").append(id).append(')');
		return sb.toString();		
	}
	
	public ComponentList copy(String path, ComponentRepository repository) {
		ComponentList copy = new ComponentList();
		copy.path = path;
		copy.key = key;
		copy.dirty = dirty;
		copy.liveList = copyContainers(liveList, repository);
		copy.previewList = copyContainers(previewList, repository);
		return copy;
	}
	
	private List copyContainers(List source, ComponentRepository repository) {
		if (source == null) {
			return null;
		}
		List dest = new ArrayList(source.size());
		Iterator it = source.iterator();
		while (it.hasNext()) {
			VersionContainer c = (VersionContainer) it.next();
			VersionContainer copy = c.copy(repository);
			dest.add(copy);
		}
		return dest;
	}

}
