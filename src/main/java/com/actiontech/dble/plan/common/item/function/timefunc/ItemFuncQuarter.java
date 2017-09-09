package com.actiontech.dble.plan.common.item.function.timefunc;

import com.actiontech.dble.plan.common.item.Item;
import com.actiontech.dble.plan.common.item.function.ItemFunc;
import com.actiontech.dble.plan.common.item.function.primary.ItemIntFunc;
import com.actiontech.dble.plan.common.time.MySQLTime;
import com.actiontech.dble.plan.common.time.MyTime;

import java.math.BigInteger;
import java.util.List;

public class ItemFuncQuarter extends ItemIntFunc {

    public ItemFuncQuarter(List<Item> args) {
        super(args);
    }

    @Override
    public final String funcName() {
        return "quarter";
    }

    @Override
    public BigInteger valInt() {
        MySQLTime ltime = new MySQLTime();
        if (getArg0Date(ltime, MyTime.TIME_FUZZY_DATE))
            return BigInteger.ZERO;
        return BigInteger.valueOf((ltime.getMonth() + 2) / 3);
    }

    @Override
    public void fixLengthAndDec() {
        fixCharLength(1); /* 1..4 */
        maybeNull = true;
    }

    @Override
    public ItemFunc nativeConstruct(List<Item> realArgs) {
        return new ItemFuncQuarter(realArgs);
    }
}