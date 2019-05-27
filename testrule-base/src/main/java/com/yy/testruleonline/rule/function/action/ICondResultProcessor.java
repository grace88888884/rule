package com.yy.testruleonline.rule.function.action;

import com.yy.testruleonline.bo.CondBo;

public interface ICondResultProcessor {
     void processCondDeclineAction(Object context, CondBo condBo);
     void processCondPassAction(Object context, CondBo condBo);
}
