package com.accountapi.service.processor.email;

import com.accountapi.dto.email.UpdateEmailRequest;
import com.accountapi.model.UpdateUserOperationType;
import com.accountapi.model.user.User;
import com.accountapi.service.strategy.email.EmailUpdateStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmailUpdateProcessorImpl implements EmailUpdateProcessor {
    private final Map<UpdateUserOperationType, EmailUpdateStrategy> strategies;

    @Autowired
    public EmailUpdateProcessorImpl(List<EmailUpdateStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(EmailUpdateStrategy::getOperationType, Function.identity()));
    }

    @Override
    @Transactional
    public void process(User user, UpdateEmailRequest request) {
        EmailUpdateStrategy strategy = strategies.get(request.getOperationType());
        strategy.execute(user, request);
    }
}
