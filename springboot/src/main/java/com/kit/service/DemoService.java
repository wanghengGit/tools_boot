package com.kit.service;

import com.kit.mapper.DemoMapper;
import com.kit.vo.DemoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangheng
 * @date 2020/8/19
 */
@Service
public class DemoService {

    @Autowired
    private DemoMapper demoMapper;

    public DemoEntity get(Long id) {
        return demoMapper.selectByPrimaryKey(id);
    }
}
