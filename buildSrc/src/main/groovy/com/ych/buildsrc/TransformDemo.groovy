package com.ych.buildsrc

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils


/**
 * 当我们实现了Transform也配制了getInputTypes和getScopes
 * Created by ych on 2020/11/23.
 * Description: 作用:转我们的资源文件啊
 */
public class TransformDemo extends Transform{
    @Override
    String getName() {
        return "HuiZaierr"      //这里就会在Task里生成一个名叫assembleHuiZaierr任务
    }

    /**
     * 想修改整个项目里边的所有的字节码文件.class，可以为jar等等
     * @return
     */
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }


    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        def inputs = transformInvocation.inputs
        def outputProvider = transformInvocation.outputProvider

        inputs.each {
            it.jarInputs.each {
                println "file:${it.file}"
                File dest = outputProvider.getContentLocation(it.name,it.contentTypes,it.scopes,Format.JAR)
                FileUtils.copyFile(it.file,dest)
            }

            it.directoryInputs.each {
                println "file:${it.file}"
                File dest = outputProvider.getContentLocation(it.name,it.contentTypes,it.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(it.file,dest)
            }
        }
    }
}
