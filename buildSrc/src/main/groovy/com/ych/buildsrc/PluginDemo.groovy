package com.ych.buildsrc

import com.android.build.gradle.BaseExtension
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

        def transform = new TransformDemo()
        def baseExtension = project.extensions.getByType(BaseExtension)
        println "bootClassPath:${baseExtension.bootClasspath}"
        baseExtension.registerTransform(transform)    //注册成功，TransformDemo就会被执行了
    }
}
