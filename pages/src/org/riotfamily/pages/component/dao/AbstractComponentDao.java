package org.riotfamily.pages.component.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.riotfamily.pages.component.ComponentList;
import org.riotfamily.pages.component.ComponentRepository;
import org.riotfamily.pages.component.ComponentVersion;
import org.riotfamily.pages.component.VersionContainer;

/**
 * Abstract base class for {@link ComponentDao} implementations that delegates
 * the various CRUD methods to generic load, save, update and delete methods.
 */
public abstract class AbstractComponentDao implements ComponentDao {

	private Log log = LogFactory.getLog(AbstractComponentDao.class);
	
	private ComponentRepository componentRepository;
	
	public AbstractComponentDao(ComponentRepository componentRepository) {
		this.componentRepository = componentRepository;
	}

	public ComponentList loadComponentList(Long id) {
		log.debug("Loading ComponentList " + id);
		return (ComponentList) loadObject(ComponentList.class, id);
	}

	public VersionContainer loadVersionContainer(Long id) {
		log.debug("Loading VersionContainer " + id);
		return (VersionContainer) loadObject(VersionContainer.class, id);
	}

	public void saveComponentList(ComponentList list) {
		log.debug("Saving ComponentList");
		saveObject(list);
	}

	public void saveVersionContainer(VersionContainer container) {
		log.debug("Saving ComponentData");
		saveObject(container);
	}

	public void updateComponentList(ComponentList list) {
		if (list.getId() != null) {
			log.debug("Updating ComponentList " + list.getId());
			updateObject(list);
		}
	}

	public void updateVersionContainer(VersionContainer container) {
		if (container.getId() != null) {
			log.debug("Updating VersionContainer " + container.getId());
			updateObject(container);
		}
	}
	
	public void deleteVersionContainer(VersionContainer container) {
		log.debug("Deleting VersionContainer " + container.getId());
		deleteObject(container);
	}

	public void updateComponentVersion(ComponentVersion version) {
		if (version.getId() != null) {
			log.debug("Updating ComponentVersion " + version.getId());
			updateObject(version);
		}
	}
	
	public void deleteComponentVersion(ComponentVersion version) {
		log.debug("Deleting ComponentVersion " + version.getId());
		deleteObject(version);
	}

	public void copyComponentLists(String oldPath, String newPath) {
		List lists = findComponentLists(oldPath);
		if (lists != null) {
			Iterator it = lists.iterator();
			while (it.hasNext()) {
				ComponentList list = (ComponentList) it.next();
				ComponentList copy = list.copy(newPath, componentRepository);
				saveComponentList(copy);		
			}
		}
	}
	
	protected abstract Object loadObject(Class clazz, Long id);
	
	protected abstract void saveObject(Object object);
	
	protected abstract void updateObject(Object object);
	
	protected abstract void deleteObject(Object object);

}
