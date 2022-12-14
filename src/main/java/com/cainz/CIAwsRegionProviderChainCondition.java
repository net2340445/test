package com.cainz;

import io.micronaut.context.condition.Condition;
import io.micronaut.context.condition.ConditionContext;
import java.io.File;

/**
 * @see <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/region-selection.html">Default region provider chain</a>
 */
public class CIAwsRegionProviderChainCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context) {
        if (System.getenv("CI") == null) {
            return true;
        }
        if (System.getProperty("aws.region") != null) {
            return true;
        }
        if (System.getenv("AWS_REGION") != null) {
            return true;
        }
        boolean result = System.getenv("HOME") != null && new File(System.getenv("HOME") + "/.aws/config").exists();
        return result;
    }
}
