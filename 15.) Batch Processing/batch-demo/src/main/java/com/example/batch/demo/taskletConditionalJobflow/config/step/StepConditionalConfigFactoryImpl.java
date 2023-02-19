package com.example.batch.demo.taskletConditionalJobflow.config.step;

import com.example.batch.demo.configs.StepConfig;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class StepConditionalConfigFactoryImpl implements StepConditionalConfigFactory {
    private final BeanFactory beanFactory;

    public StepConditionalConfigFactoryImpl(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public StepConfig createStepConditionalConfig1() {
        return beanFactory.getBean(StepConditionalConfigImpl1.class.getSimpleName(), StepConfig.class);
    }

    @Override
    public StepConfig createStepConditionalConfig2() {
        return beanFactory.getBean(StepConditionalConfigImpl2.class.getSimpleName(), StepConfig.class);
    }

    @Override
    public StepConfig createStepConditionalConfig3() {
        return beanFactory.getBean(StepConditionalConfigImpl3.class.getSimpleName(), StepConfig.class);
    }
}
