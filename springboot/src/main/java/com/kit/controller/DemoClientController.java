package com.kit.controller;

import com.kit.service.DemoService;
import com.kit.vo.DemoEntity;
import com.mcep.commons.request.validate.Validate;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kit
 * @date 20200819
 */
@RestController
@RequestMapping("/demo/client")
public class DemoClientController {

    @Autowired
    private DemoService demoService;

    @ApiOperation(value = "根据id查Demo", notes = "注意事项：无")
    @GetMapping("/get")
    @ApiImplicitParam(name = "id", value = "id标识", dataType = "Long", paramType = "query", required = true)
    public DemoEntity get(@Validate(propName = "id", required = true) Long id) {
        return demoService.get(id);
    }

}
