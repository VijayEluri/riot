/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * 
 * The Original Code is Riot.
 * 
 * The Initial Developer of the Original Code is
 * Neteye GmbH.
 * Portions created by the Initial Developer are Copyright (C) 2007
 * the Initial Developer. All Rights Reserved.
 * 
 * Contributor(s):
 *   Felix Gnass [fgnass at neteye dot de]
 * 
 * ***** END LICENSE BLOCK ***** */
package org.riotfamily.website.generic.model.hibernate;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.EntityMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.riotfamily.cachius.TaggingContext;

/**
 * @author Felix Gnass [fgnass at neteye dot de]
 * @since 6.5
 */
public class HqlModelBuilder extends AbstractHqlModelBuilder {

	protected Object getResult(Query query) {
		query.setMaxResults(1);
		return query.uniqueResult();
	}
	
	protected void tagResult(Query query, Object item, 
			HttpServletRequest request) {
		
		if (item != null) {
			Class clazz = Hibernate.getClass(item);
			Serializable id = getSessionFactory()
					.getClassMetadata(clazz).getIdentifier(
					item, EntityMode.POJO);

			TaggingContext.tag(request, clazz.getName() + "#" + id);
		}
		else {
			Class clazz = query.getReturnTypes()[0].getReturnedClass();
			TaggingContext.tag(request, clazz.getName());
		}
	}
}
