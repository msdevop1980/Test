package org.sample.ft;

import cucumber.runtime.Runtime;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.sample.ft.util.ProtocolEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public final class WithOptions {

    private static final String OPTION_MONOCHROME="--monochrome";
    private static final String OPTION_PLUGIN="--plugin";
    private static final String OPTION_TAGS="--tags";
    private static final String OPTION_GLUE="--glue";
    private static final String CUCUMBER_RESULT_JSON_FILE="/result.json";

    private WithOptions(){
    }

    public static void main(String[] args){
        String buildDir=System.getProperty("project.build.directory","target");
        String tags=System.getProperty("tagArgs","not @ignore");
        Assertions.assertThat(args).hasSize(2);
        String glueClasses=args[0];
        String featureFilePath=args[1];
        String cucumberRepoDir=buildDir + "/cucumber-report";
        String jsonReportPath= StringUtils.join(new String[]{"json:",cucumberRepoDir, ProtocolEnum.identify().toString(),"/result/json"});
        List<String> cucumberOptions=new ArrayList<>(Arrays.asList("--monochrome", "--plugin", "pretty", "--tags", tags, "--plugin", jsonReportPath));
        Arrays.stream(StringUtils.split(glueClasses,",")).peek((pkg)->{
            cucumberOptions.add(OPTION_GLUE);
        }).forEach((pkg)->{
            cucumberOptions.add(pkg);
        });
        cucumberOptions.add(featureFilePath);
        Runtime runtime=Runtime.builder().withArgs(cucumberOptions).withClassLoader(Thread.currentThread().getContextClassLoader()).build();
        runtime.run();
        if(runtime.exitStatus()!=0){
            throw new FeatureTestFailureException("Feature test "+ featureFilePath + "failed");
        }
    }
}
