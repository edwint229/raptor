package com.ppdai.framework.raptor.codegen.core.message2pojos;

import com.ppdai.framework.raptor.codegen.core.CodegenConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.maven.project.MavenProject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class PojosGeneratorTest {

    private static final String DEST_PATH = "./target/generated-proto/";
    private static final String SRC_PATH = "./src/test/resources/";

    private CodegenConfiguration basicConfig = new CodegenConfiguration();

    @Before
    public void before() throws Exception {
        cleanDestFile();
        setConfiguration();
    }

    private void setConfiguration() {
        File[] inputFileDirectories = new File[1];
        inputFileDirectories[0] = new File(SRC_PATH);
        MavenProject mavenProject = new MavenProject();

        basicConfig.setInputDirectories(inputFileDirectories);
        basicConfig.setIncludeStdTypes(true);
        basicConfig.setOutputDirectory(new File(DEST_PATH));
        basicConfig.setType("java");
        basicConfig.setProject(mavenProject);
        basicConfig.setExtension(".proto");
    }

    private void cleanDestFile() throws Exception {
        File destDir = new File(DEST_PATH);
        FileUtils.deleteDirectory(destDir);
    }

    @Test
    public void testGeneratePojos() throws Exception {
        PojosGenerator pojosGenerator = new PojosGenerator();
        pojosGenerator.codegenConfigure(basicConfig);
        pojosGenerator.generate();

        File simplePojo = new File(DEST_PATH + "com/ppdai/framework/raptor/codegen/proto/Helloworld.java");
        Assert.assertTrue(simplePojo.exists());
    }

}
