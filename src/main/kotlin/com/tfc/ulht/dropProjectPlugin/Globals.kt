/*-
 * Plugin Drop Project
 * 
 * Copyright (C) 2019 Yash Jahit & Bernardo Baltazar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tfc.ulht.dropProjectPlugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.WindowManager
import com.tfc.ulht.dropProjectPlugin.assignmentComponents.TableLine
import com.tfc.ulht.dropProjectPlugin.settings.SettingsState
import com.tfc.ulht.dropProjectPlugin.statusBarWidget.PluginStatusWidget
import com.tfc.ulht.dropProjectPlugin.toolWindow.DropProjectToolWindow
import data.FullBuildReport

class Globals(private val project: Project, private val toolWindow: DropProjectToolWindow) {

    val REQUEST_URL = SettingsState.getInstance().serverURL
    var USERNAME = SettingsState.getInstance().username
    val USERNUMBER = SettingsState.getInstance().usernumber
    val TOKEN = SettingsState.getInstance().token

    var statusWidgetId = "DropProjectStatusWidget${PluginStatusWidget.idCount}"
    var selectedAssignmentID: String = ""
        set(value) {
            field = value
            val statusWidget: PluginStatusWidget? = WindowManager.getInstance().getStatusBar(project)
                .getWidget(statusWidgetId) as PluginStatusWidget

            if (statusWidget != null) {
                statusWidget.selectedAssignmentID = selectedAssignmentID
            }
        }

    var selectedLine: TableLine? = null
    var lastBuildReport: FullBuildReport? = null
        set(value) {
            field = value
            if (value != null) {
                toolWindow.toolbarPanel!!.buildReportAvailable()
            } else {
                toolWindow.toolbarPanel!!.toggleOpenBuildReportAction()
            }
        }

}