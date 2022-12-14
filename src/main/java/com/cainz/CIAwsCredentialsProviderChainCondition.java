package com.cainz;

import io.micronaut.context.condition.Condition;
import io.micronaut.context.condition.ConditionContext;
import java.io.File;

/**
 * @see <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/ec2-iam-roles.html">AWS Default credentials provider chain</a>
 */
public class CIAwsCredentialsProviderChainCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context) {
        if (System.getenv("CI") == null) {
            return true;
        }
        if (System.getProperty("aws.accessKeyId") != null && System.getProperty("aws.secretAccessKey") !=null ) {
            return true;
        }
        if (System.getenv("AWS_ACCESS_KEY_ID") != null && System.getenv("AWS_SECRET_ACCESS_KEY") !=null ) {
            return true;
        }
        if (System.getenv("AWS_CONTAINER_CREDENTIALS_RELATIVE_URI") != null) {
            return true;
        }
        boolean result = System.getenv("HOME") != null && new File(System.getenv("HOME") + "/.aws/credentials").exists();
        return result;
    }
}
