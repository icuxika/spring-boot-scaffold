package com.icuxika.scaffold.registrar;

import com.icuxika.scaffold.annotation.SimpleFeignClient;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.util.Map;
import java.util.Set;

public class SimpleFeignClientsRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * @param importingClassMetadata 当前类的注解信息
     * @param registry               注册类
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ImportBeanDefinitionRegistrar.super.registerBeanDefinitions(importingClassMetadata, registry);
        String basePackage = ClassUtils.getPackageName(importingClassMetadata.getClassName());
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return true;
            }
        };
        scanner.addIncludeFilter(new AnnotationTypeFilter(SimpleFeignClient.class));
        Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(basePackage);
        candidateComponents.forEach(candidateComponent -> {
            if (candidateComponent instanceof AnnotatedBeanDefinition beanDefinition) {
                AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
                System.out.println("检查是接口吗？ " + annotationMetadata.isInterface());

                Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(SimpleFeignClient.class.getCanonicalName());
                if (attributes != null) {
                    String className = annotationMetadata.getClassName();
                    Class<?> clazz = ClassUtils.resolveClassName(className, null);
                    String name = (String) attributes.get("name");
                    String baseUrl = (String) attributes.get("baseUrl");

                    BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SimpleFeignClientFactoryBean.class);
                    definitionBuilder.addConstructorArgValue(clazz);
                    definitionBuilder.addConstructorArgValue(baseUrl);

                    AbstractBeanDefinition handleDefinition = definitionBuilder.getBeanDefinition();

                    BeanDefinitionHolder holder = new BeanDefinitionHolder(handleDefinition, name, new String[]{name});
                    BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
                }
            }
        });
    }
}
