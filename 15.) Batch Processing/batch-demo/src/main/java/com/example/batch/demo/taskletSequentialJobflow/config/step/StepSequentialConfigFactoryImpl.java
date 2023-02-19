package com.example.batch.demo.taskletSequentialJobflow.config.step;

import com.example.batch.demo.configs.StepConfig;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class StepSequentialConfigFactoryImpl implements StepSequentialConfigFactory {
    private final BeanFactory beanFactory;

    public StepSequentialConfigFactoryImpl(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public StepConfig createStepSequentialConfig1() {
        return beanFactory.getBean(StepSequentialConfigImpl1.class.getSimpleName(), StepConfig.class);
    }

    @Override
    public StepConfig createStepSequentialConfig2() {
        return beanFactory.getBean(StepSequentialConfigImpl2.class.getSimpleName(), StepConfig.class);
    }

    @Override
    public StepConfig createStepSequentialConfig3() {
        return beanFactory.getBean(StepSequentialConfigImpl3.class.getSimpleName(), StepConfig.class);
    }
}
