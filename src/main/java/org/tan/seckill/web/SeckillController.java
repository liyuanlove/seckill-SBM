package org.tan.seckill.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tan.seckill.dto.Exposer;
import org.tan.seckill.dto.SeckillExecution;
import org.tan.seckill.enums.SeckillStateEnum;
import org.tan.seckill.exception.RepeatKillException;
import org.tan.seckill.exception.SeckillClosedException;
import org.tan.seckill.po.Seckill;
import org.tan.seckill.service.ISeckillService;
import org.tan.seckill.vo.GlobalResult;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Author: Redinw
 * Description:
 */
@Controller
@RequestMapping("/seckill/")
@Slf4j
public class SeckillController {

    @Resource
    private ISeckillService seckillService;

    /**
     * 秒杀列表
     *
     * @param model
     * @return
     */
    @GetMapping(value = "list")
    public String list(Model model) {
        List<Seckill> list = seckillService.getListByPage(0, 5);
        model.addAttribute("list", list);
        return "list";
    }

    /**
     * 秒杀详情
     *
     * @param seckillId
     * @param model
     * @return
     */
    @GetMapping(value = "{seckillId}/detail")
    public String detial(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    /**
     * 秒杀地址暴露
     *
     * @param seckillId
     * @return
     */
    @PostMapping(value = "{seckillId}/exposer",
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public GlobalResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        GlobalResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exoportSeckillUrl(seckillId);
            result = GlobalResult.ok(exposer);
        } catch (Exception e) {
            result = GlobalResult.error(e.getMessage());
        }
        return result;
    }


    @PostMapping(value = "{seckillId}/{md5}/execute",
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public GlobalResult<SeckillExecution> exec(@PathVariable("seckillId")
                                                        Long seckillId, @PathVariable("md5") String md5,
                                               @CookieValue(value = "userPhone", required = false) Long phone) {
        if (phone == null) {
            return GlobalResult.error("do not login");
        }
        SeckillExecution seckillExcution = null;
        try {
            seckillExcution = seckillService.executeSeckill(seckillId, phone, md5);
        } catch (RepeatKillException e) {
            seckillExcution = new SeckillExecution(seckillId,
                    SeckillStateEnum.REPEAT_KILL);
        } catch (SeckillClosedException e) {
            seckillExcution = new SeckillExecution(seckillId, SeckillStateEnum.END);
        } catch (Exception e) {
            log.error(e.getMessage());
            seckillExcution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
        }
        return GlobalResult.ok(seckillExcution);
    }

    /**
     * 执行秒杀
     *
     * @param seckillId
     * @param md5
     * @param phone
     * @return
     */
    @PostMapping(value = "{seckillId}/{md5}/execution",
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public GlobalResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                  @PathVariable("md5") String md5,
                                                  @CookieValue(value = "killPhone", required = false) Long phone) {
        if (phone == null) {
            return GlobalResult.error("未注册");
        }
        SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
        return GlobalResult.ok(execution);
    }

    @GetMapping(value = "time/now")
    @ResponseBody
    public GlobalResult<Long> now() {
        Date date = new Date();
        return GlobalResult.ok(date.getTime());
    }
}
