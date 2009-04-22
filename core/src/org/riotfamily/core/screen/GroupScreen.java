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
package org.riotfamily.core.screen;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.riotfamily.common.util.ResourceUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class GroupScreen extends AbstractRiotScreen implements Controller {

	private List<RiotScreen> childScreens;

	private String viewName = ResourceUtils.getPath(
			GroupScreen.class, "group.ftl");
	
	public List<RiotScreen> getChildScreens() {
		return childScreens;
	}

	public void setChildScreens(List<RiotScreen> items) {
		this.childScreens = items;
		for (RiotScreen item : items) {
			item.setParentScreen(this);
		}
	}
	
	@Override
	public String getTitle(Object object) {
		if (object != null) {
			return ScreenUtils.getLabel(object, this);
		}
		return getId();
	}
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mv = new ModelAndView(viewName);
		return mv;
	}

}