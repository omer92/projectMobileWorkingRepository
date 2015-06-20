package com.ibu.edu.ba.TweenAccessors;

/**
 * Created by Omer on 14.6.2015.
 */
import aurelienribon.tweenengine.TweenAccessor;

public class ValueAccessor implements TweenAccessor<Value> {

    @Override
    public int getValues(Value target, int tweenType, float[] returnValues) {
        returnValues[0] = target.getValue();
        return 1;
    }

    @Override
    public void setValues(Value target, int tweenType, float[] newValues) {
        target.setValue(newValues[0]);
    }

}
