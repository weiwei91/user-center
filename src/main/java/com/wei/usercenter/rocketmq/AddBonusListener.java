package com.wei.usercenter.rocketmq;

import com.wei.usercenter.dao.bonus.BonusEventLogMapper;
import com.wei.usercenter.dao.user.UserMapper;
import com.wei.usercenter.domain.entity.bonus.BonusEventLog;
import com.wei.usercenter.domain.entity.user.User;
import com.wei.usercenter.domain.entity.user.dto.messaging.UserAddBonusMsgDTO;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RocketMQMessageListener(consumerGroup = "consumer-group",topic = "add-bonus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {

    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;
    @Override
    public void onMessage(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        //当收到消息时候需要处理的业务
        //1. 为用户加积分
        Integer userId = userAddBonusMsgDTO.getUserId();
        Integer bonus = userAddBonusMsgDTO.getBonus();
        User user =  this.userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus() + userAddBonusMsgDTO.getBonus());
        this.userMapper.updateByPrimaryKey(user);

        //2. 记录日志到表 bonus_event_log 表中
        this.bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(bonus)
                        .event("contribute")
                        .createTime(new Date())
                        .description("投稿加积分")
                        .build()
        );

    }
}
