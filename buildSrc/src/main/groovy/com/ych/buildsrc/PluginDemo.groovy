package com.ych.buildsrc

import org.gradle.api.Plugin
import org.gradle.api.Project;

/**
 * Created by ych on 2020/11/22.
 * Description:
 */
public class PluginDemo implements Plugin<Project>{
    @Override
    void apply(Project project) {
        def extensions = project.extensions.create("ych",ExtensionsDemo)
        project.afterEvaluate {
            println "applicationId的值：${extensions.applicationId}"
        }
    }
}
