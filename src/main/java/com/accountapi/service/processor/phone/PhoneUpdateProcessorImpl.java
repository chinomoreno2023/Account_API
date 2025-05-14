package com.accountapi.service.processor.phone;

import com.accountapi.dto.phone.UpdatePhoneRequest;
import com.accountapi.model.UpdateUserOperationType;
import com.accountapi.model.user.User;
import com.accountapi.service.strategy.phone.PhoneUpdateStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PhoneUpdateProcessorImpl implements PhoneUpdateProcessor {
    private final Map<UpdateUserOperationType, PhoneUpdateStrategy> strategies;

    @Autowired
    public PhoneUpdateProcessorImpl(List<PhoneUpdateStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(PhoneUpdateStrategy::getOperationType, Function.identity()));
    }

    @Override
    @Transactional
    public void process(User user, UpdatePhoneRequest request) {
        PhoneUpdateStrategy strategy = strategies.get(request.getOperationType());
        strategy.execute(user, request);
    }
}
